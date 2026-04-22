package at.htl.kaindorf.a102_powerconsumption;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsedKwh;
    private EditText etDistanceInKm;
    private Button btCalculate;
    private TextView tvConsumption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etUsedKwh = findViewById(R.id.id_etInputkWh);
        etDistanceInKm = findViewById(R.id.id_etInputKm);
        btCalculate = findViewById(R.id.id_btCalculate);
        tvConsumption = findViewById(R.id.id_tvConsumption);

        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCalcConsumption(view);
            }
        });
    }

    private void onCalcConsumption(View view) {
        if (!(etUsedKwh.getText().toString().isEmpty() || etDistanceInKm.getText().toString().isEmpty())) {
            try {
                double kwh = Double.parseDouble(etUsedKwh.getText().toString());
                int km = Integer.parseInt(etDistanceInKm.getText().toString());
                double consumption = (kwh / km) * 100;

                tvConsumption.setText(String.format("Consumption: %.2f kWh / 100 km", consumption));
                tvConsumption.setTextColor(Color.rgb(255, 165, 0));

                if (consumption < 10) {
                    tvConsumption.setTextColor(Color.rgb(0, 100, 0));
                }
            } catch (NumberFormatException nfe) {
                tvConsumption.setText("Illegal Input");
                tvConsumption.setTextColor(Color.RED);
            }
        }
    }
}