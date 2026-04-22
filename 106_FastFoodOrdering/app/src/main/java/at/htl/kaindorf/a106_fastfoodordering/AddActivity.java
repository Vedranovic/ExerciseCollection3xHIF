package at.htl.kaindorf.a106_fastfoodordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import at.htl.kaindorf.a106_fastfoodordering.pojos.FoodItem;

public class AddActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etCalories;
    private EditText etPrice;
    private Button btAddItem;
    private Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        etName = findViewById(R.id.id_etName);
        etCalories = findViewById(R.id.id_etCalories);
        etPrice = findViewById(R.id.id_etPrice);
        btAddItem = findViewById(R.id.id_btAddItem);
        btAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd(view);
            }
        });
        btCancel = findViewById(R.id.id_btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancel(view);
            }
        });
    }

    private void onAdd(View view) {
        String name = etName.getText().toString();
        String calories = etCalories.getText().toString();
        String price = etPrice.getText().toString();

        if (name.isBlank() || !name.matches("[a-zA-Z ]+")) {
            Toast.makeText(this, "Please enter some value for the name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (calories.isBlank() || !name.matches("[a-zA-Z0-9. ]+")) {
            Toast.makeText(this, "Please make sure to enter valid values for price and calories!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (price.isBlank() || !name.matches("[a-zA-Z0-9. ]+")) {
            Toast.makeText(this, "Please make sure to enter valid values for price and calories!", Toast.LENGTH_SHORT).show();
            return;
        }

        FoodItem foodItem = new FoodItem(name, Integer.parseInt(calories), Double.parseDouble(price));
        Intent intent = new Intent();
        intent.putExtra("fooditem", foodItem);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void onCancel(View view) {
        finish();
    }
}