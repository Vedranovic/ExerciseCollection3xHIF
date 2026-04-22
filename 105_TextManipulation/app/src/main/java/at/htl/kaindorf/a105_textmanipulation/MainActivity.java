package at.htl.kaindorf.a105_textmanipulation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvText;
    private Button btAdd;
    private Button btShow;
    private Button btClear;
    private EditText etFrom;
    private EditText etTo;

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.id_tvText);
        etFrom = findViewById(R.id.id_etFrom);
        etTo = findViewById(R.id.id_etTo);
        btAdd = findViewById(R.id.id_btAdd);
        btShow = findViewById(R.id.id_btShow);
        btClear = findViewById(R.id.id_btClear);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd(view);
            }
        });
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShow(view);
            }
        });
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClear(view);
            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                onAddResult(o);
            }
        });
    }

    private void onAdd(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        launcher.launch(intent);
    }

    private void onAddResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent intent = result.getData();

            if (intent != null && intent.hasExtra("data")) {
                tvText.append(intent.getStringExtra("data"));
            }
        }
    }

    private void onClear(View v) {
        tvText.setText("");
    }

    private void onShow(View v) {
        try {
            int from = Integer.parseInt(etFrom.getText().toString());
            int to = Integer.parseInt(etTo.getText().toString());

            if (from < to) {
                Intent intent = new Intent(this, ShowActivity.class);
                String message = (String) tvText.getText().subSequence(from, to + 1);
                intent.putExtra("data", message);
                launcher.launch(intent);
            } else {
                Toast.makeText(this, "Please set a valid value for FROM and TO!", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Please set a valid value for FROM and TO!", Toast.LENGTH_SHORT).show();
        }
    }
}