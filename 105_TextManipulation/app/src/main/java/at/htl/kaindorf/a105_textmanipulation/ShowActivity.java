package at.htl.kaindorf.a105_textmanipulation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    private TextView tvShowText;
    private Button btShowOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show);
        tvShowText = findViewById(R.id.id_tvShowText);
        btShowOk = findViewById(R.id.id_btShowOk);
        btShowOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOk(view);
            }
        });

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("data")) {
            String data = intent.getStringExtra("data");
            tvShowText.setText(data);
        }
    }

    private void onOk(View v) {
        finish();
    }
}