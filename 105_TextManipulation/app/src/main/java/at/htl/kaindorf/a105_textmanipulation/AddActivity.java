package at.htl.kaindorf.a105_textmanipulation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private EditText etAddText;
    private Button btOkAdd;
    private Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        etAddText = findViewById(R.id.id_etAddText);
        btOkAdd = findViewById(R.id.id_btOk);
        btOkAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOk(view);
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

    private void onOk(View v) {
        Intent intent = new Intent();
        intent.putExtra("data", etAddText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void onCancel(View v) {
        finish();
    }
}