package at.htl.kaindorf.a107_legocollection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

import at.htl.kaindorf.a107_legocollection.controller.DataController;
import at.htl.kaindorf.a107_legocollection.pojos.LegoSet;

public class MainActivity extends AppCompatActivity {
    private Button btCreate;
    private ListView lvLegoSets;
    private DataController dataController;
    private ActivityResultLauncher<Intent> launcher;
    private ArrayAdapter<LegoSet> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btCreate = findViewById(R.id.id_btCreate);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddSet(view);
            }
        });
        lvLegoSets = findViewById(R.id.id_lvLegoSets);
        lvLegoSets.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                onRemoveSet(i);
                return true;
            }
        });
        dataController = new DataController();
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                onAddSetResult(o);
            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataController.getLegoSets());
        lvLegoSets.setAdapter(adapter);
    }

    private void onRemoveSet(int position) {
        new AlertDialog.Builder(this)
                .setTitle("DELETE?")
                .setMessage(String.format(Locale.GERMAN, "Do you really want to delete the set %s?", dataController.getLegoSetAtPosition(position).getName()))
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataController.deleteSet(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }

    private void onAddSet(View view) {
        Intent intent = new Intent(this, AddSetActivity.class);
        launcher.launch(intent);
    }

    private void onAddSetResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent intent = result.getData();

            if (intent != null && intent.hasExtra("legoset")) {
                try {
                    String name = intent.getStringExtra("legoset");
                    dataController.addLegoSet(name);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}