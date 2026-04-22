package at.htl.kaindorf.a104_formatter;

import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edTextInput;
    private CheckBox cbBold;
    private CheckBox cbItalic;
    private TextView tvSize;
    private SeekBar sbSize;
    private RadioButton rbUbuntu;
    private RadioButton rbGrandHotel;
    private RadioButton rbPermanentMarker;
    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edTextInput = findViewById(R.id.id_edTextInput);
        tvSize = findViewById(R.id.id_tvSize);
        sbSize = findViewById(R.id.id_sbSize);

        CheckBox[] checkBoxes = {
                cbBold = findViewById(R.id.id_cbBold),
                cbItalic = findViewById(R.id.id_cbItalic)
        };

        RadioButton[] radioButtons = {
                rbUbuntu = findViewById(R.id.id_rbUbuntu),
                rbGrandHotel = findViewById(R.id.id_rbGrandHotel),
                rbPermanentMarker = findViewById(R.id.id_rbPermamentMarker),
        };

        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                    onChangeFont();
                }
            });
        }

        onChangeFontStyle();

        for (RadioButton radioButton : radioButtons) {
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onChangeFontStyle();
                }
            });
        }

        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onChangeFontSize();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void onChangeFontStyle() {
        if (rbUbuntu.isChecked()) {
            font = ResourcesCompat.getFont(this, R.font.ubuntu_regular);
        }
        if (rbGrandHotel.isChecked()) {
            font = ResourcesCompat.getFont(this, R.font.grandhotel_regular);
        }
        if (rbPermanentMarker.isChecked()) {
            font= ResourcesCompat.getFont(this, R.font.permanentmarker_regular);
        }

        onChangeFont();
    }

    private void onChangeFontSize() {
        float progress = sbSize.getProgress();

        tvSize.setText(String.format(Locale.GERMAN, "Size: %.0f", progress));
        edTextInput.setTextSize(progress);
    }

    private void onChangeFont() {
        int style = Typeface.NORMAL;

        if (cbBold.isChecked() && cbItalic.isChecked()) {
            style = Typeface.BOLD_ITALIC;
        } else if (cbBold.isChecked()) {
            style = Typeface.BOLD;
        } else if (cbItalic.isChecked()) {
            style = Typeface.ITALIC;
        }

        if (font != null) {
            edTextInput.setTypeface(Typeface.create(font, style));
        } else {
            edTextInput.setTypeface(Typeface.defaultFromStyle(style));
        }
    }
}