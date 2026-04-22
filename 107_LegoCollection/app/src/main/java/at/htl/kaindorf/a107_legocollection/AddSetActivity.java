package at.htl.kaindorf.a107_legocollection;

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

public class AddSetActivity extends AppCompatActivity {
    private EditText etSetName;
    private Button btAddSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_set);
        etSetName = findViewById(R.id.id_etSetName);
        btAddSet = findViewById(R.id.id_btAddSet);
        btAddSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateAdd(view);
            }
        });
    }

    private void onCreateAdd(View view) {
        String name = etSetName.getText().toString();

        if (name.isBlank()) {
            Toast.makeText(this, "Enter a valid name!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("legoset", name);
        setResult(RESULT_OK, intent);
        finish();
    }
}