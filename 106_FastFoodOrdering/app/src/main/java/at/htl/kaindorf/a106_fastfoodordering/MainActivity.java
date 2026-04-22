package at.htl.kaindorf.a106_fastfoodordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

import at.htl.kaindorf.a106_fastfoodordering.controller.DataController;
import at.htl.kaindorf.a106_fastfoodordering.pojos.FoodItem;

public class MainActivity extends AppCompatActivity {
    private ListView lvData;
    private RadioButton rbASC;
    private RadioButton rbDESC;
    private Button btCalcClear;
    private Button btAdd;
    private DataController controller;
    private ActivityResultLauncher<Intent> launcher;
    private ArrayAdapter<FoodItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lvData = findViewById(R.id.id_lvData);
        rbASC = findViewById(R.id.id_rbASC);
        rbASC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                onSort();
            }
        });
        rbDESC = findViewById(R.id.id_rbDESC);
        rbDESC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                onSort();
            }
        });
        btCalcClear = findViewById(R.id.id_btCalcClear);
        btCalcClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCalc(view);
            }
        });
        btAdd = findViewById(R.id.id_btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd(view);
            }
        });
        controller = new DataController();
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                onAddResult(o);
            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, controller.getItems());
        lvData.setAdapter(adapter);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                increaseAmount(i);
            }
        });
    }

    private void onAdd(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        launcher.launch(intent);
    }

    private void onAddResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent intent = result.getData();

            if (intent != null && intent.hasExtra("fooditem")) {
                FoodItem foodItem = intent.getParcelableExtra("fooditem", FoodItem.class);
                controller.addItem(foodItem);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void onCalc(View view) {
        Toast.makeText(this, String.format(Locale.GERMAN, "You have to pay %.2f €", controller.getFullPrice()), Toast.LENGTH_SHORT).show();
        controller.getItems().clear();
        adapter.notifyDataSetChanged();
    }

    private void increaseAmount(int position) {
        controller.getItems().get(position).increaseAmount();
        adapter.notifyDataSetChanged();
    }

    private void onSort() {
        if (rbASC.isChecked()) {
            controller.sort("ASC");
            adapter.notifyDataSetChanged();
        }

        if (rbDESC.isChecked()) {
            controller.sort("DESC");
            adapter.notifyDataSetChanged();
        }
    }
}