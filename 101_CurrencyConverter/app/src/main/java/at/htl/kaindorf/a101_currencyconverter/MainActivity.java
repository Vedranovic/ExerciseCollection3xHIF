package at.htl.kaindorf.a101_currencyconverter;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etConverterInput;
    private ImageButton btConvert;
    private TextView tvConvertDisplay;
    private TextView tvLeft;
    private TextView tvRight;

    private boolean isEUR = true;
    private static final double RATEEURTOUSD = 1.17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etConverterInput = findViewById(R.id.id_etConvertInput);
        btConvert = findViewById(R.id.id_btConvert);
        tvConvertDisplay = findViewById(R.id.id_tvConvertDisplay);
        tvLeft = findViewById(R.id.id_tvLeft);
        tvRight = findViewById(R.id.id_tvRight);

        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangeCurrency(view);
            }
        });

        etConverterInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                convertAndUpdate();
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });

        tvConvertDisplay.setText("1.00 € are 1.17 $");
    }

    private void onChangeCurrency(View view) {
        isEUR = !isEUR;

        tvLeft.setText(isEUR ? "EUR" : "USD");
        tvRight.setText(isEUR ? "USD" : "EUR");
        etConverterInput.setHint(isEUR ? "1 Euro" : "1 US-Dollar");
        tvConvertDisplay.setText(isEUR ?
                String.format("%.2f € are %.2f $", 1f, 1 * RATEEURTOUSD) :
                String.format("%.2f $ are %.2f €", 1f, 1 / RATEEURTOUSD));
    }

    private void convertAndUpdate() {
        try {
            double eur = 0.0;
            double usd = 0.0;

            tvConvertDisplay.setTextColor(Color.BLACK);

            if (isEUR) {
                eur = Double.parseDouble(etConverterInput.getText().toString());
                usd = eur * 1.17;

                String display = String.format("%.2f € are %.2f $", eur, usd);
                tvConvertDisplay.setText(display);
            } else {
                usd = Double.parseDouble(etConverterInput.getText().toString());
                eur = usd * 0.85;

                String display = String.format("%.2f $ are %.2f €", usd, eur);
                tvConvertDisplay.setText(display);
            }
        } catch (NumberFormatException nfe) {
            if (etConverterInput.getText().toString().isEmpty()) {
                tvConvertDisplay.setText(isEUR ? "1.00 € are 1.17 $" : "1.00 $ are 0.85 €");
            } else {
                tvConvertDisplay.setText("ERROR");
                tvConvertDisplay.setTextColor(Color.RED);
            }
        }
    }
}