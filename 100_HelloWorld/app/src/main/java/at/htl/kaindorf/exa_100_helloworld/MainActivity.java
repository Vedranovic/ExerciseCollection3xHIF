package at.htl.kaindorf.exa_100_helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstLastName;
    private Button btContinue;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etFirstLastName = findViewById(R.id.id_etFirstLastName);
        btContinue = findViewById(R.id.id_btContinue);
        tvName = findViewById(R.id.id_tvName);

        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onContinue(view);
            }
        });
    }

    private void onContinue(View view) {
        String firstLastName = etFirstLastName.getText().toString();

        if (firstLastName.isBlank()) {
            Toast.makeText(this, "Please enter some text!", Toast.LENGTH_SHORT).show();
        } else {
            etFirstLastName.setVisibility(View.INVISIBLE);
            tvName.setText(String.format("Hello %s - nice to meet you!", firstLastName));
        }
    }
}