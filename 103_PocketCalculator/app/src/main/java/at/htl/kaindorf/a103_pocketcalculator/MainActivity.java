package at.htl.kaindorf.a103_pocketcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import at.htl.kaindorf.a103_pocketcalculator.pojos.MyStringQueue;
import at.htl.kaindorf.a103_pocketcalculator.pojos.PostfixCalculator;
import at.htl.kaindorf.a103_pocketcalculator.pojos.ShuntingYardImpl;

public class MainActivity extends AppCompatActivity {

    private Button btClear;
    private Button btDel;
    private Button btComma;
    private Button btEnter;
    private Button btBracketOpen;
    private Button btBracketClose;
    private TextView tvInputField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btClear = findViewById(R.id.id_btButtonClr);
        btDel = findViewById(R.id.id_btButtonDel);
        btComma = findViewById(R.id.id_btButtonComma);
        btEnter = findViewById(R.id.id_btButtonEnter);
        btBracketOpen = findViewById(R.id.id_btButtonBracketOpen);
        btBracketClose = findViewById(R.id.id_btButtonBracketClosed);
        tvInputField = findViewById(R.id.id_tvInputField);

        Button[] numButtons = {
                findViewById(R.id.id_btButton0),
                findViewById(R.id.id_btButton1),
                findViewById(R.id.id_btButton2),
                findViewById(R.id.id_btButton3),
                findViewById(R.id.id_btButton4),
                findViewById(R.id.id_btButton5),
                findViewById(R.id.id_btButton6),
                findViewById(R.id.id_btButton7),
                findViewById(R.id.id_btButton8),
                findViewById(R.id.id_btButton9)
        };

        Button[] operatorButtons = {
                findViewById(R.id.id_btButtonPlus),
                findViewById(R.id.id_btButtonMinus),
                findViewById(R.id.id_btButtonMultiply),
                findViewById(R.id.id_btButtonDivide)
        };

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClear(view);
            }
        });

        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBack(view);
            }
        });

        btComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onComma(view);
            }
        });

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEnter(view);
            }
        });

        btBracketOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBracketOpen(view);
            }
        });

        btBracketClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBracketClose(view);
            }
        });

        for (Button button : numButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNumberClicked(view);
                }
            });
        }

        for (Button button : operatorButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onOperator(view);
                }
            });
        }
    }

    private void onClear(View view) {
        tvInputField.setText("");
    }

    private void onNumberClicked(View view) {
        Button btn = (Button) view;

        if (isLastElementClosingBracket() && !isLastElementZero()) {
            tvInputField.append(btn.getText());
        }
    }

    private void onOperator(View view) {
        Button btn = (Button) view;

        if (!tvInputField.getText().toString().isEmpty() && isLastElementOpeningBracket() && isLastElementComma() && isLastElementOperator()) {
            tvInputField.append(btn.getText());
        }
    }

    private void onBack(View view) {
        if (!tvInputField.getText().toString().isEmpty()) {
            String lastCharDeleted = tvInputField.getText().toString().substring(0, tvInputField.length() - 1);

            tvInputField.setText(lastCharDeleted);
        }
    }

    private void onComma(View view) {
        Button btn = (Button) view;

        if (isLastElementComma() && isLastElementOperator() && isLastElementOpeningBracket() && isLastElementClosingBracket() && !tvInputField.getText().toString().isEmpty() && !isLastCommaNumber()) {
            tvInputField.append(btn.getText());
        }
    }

    private void onBracketOpen(View view) {
        Button btn = (Button) view;

        if (!isLastElementNumber() && isLastElementComma() && isLastElementClosingBracket()) {
            tvInputField.append(btn.getText());
        }
    }

    private void onBracketClose(View view) {
        Button btn = (Button) view;

        if (isLastElementOperator() && isLastElementComma() && hasUnclosedBracket() && !tvInputField.getText().toString().isEmpty()) {
            tvInputField.append(btn.getText());
        }
    }

    private void onEnter(View view) {
        if (!tvInputField.getText().toString().isEmpty()) {
            MyStringQueue postfix = ShuntingYardImpl.toPostfix(tvInputField.getText().toString());
            PostfixCalculator postfixCalculator = new PostfixCalculator();
            double result = postfixCalculator.evalPostfix(postfix);

            tvInputField.setText("");
            tvInputField.append(String.format(Locale.GERMAN, "%.2f", result));
        }
    }

    private boolean isLastElementZero() {
        String inputField = tvInputField.getText().toString();

        return inputField.endsWith("0");
    }

    private boolean isLastElementOperator() {
        String inputField = tvInputField.getText().toString();

        return !inputField.endsWith("+") && !inputField.endsWith("-") && !inputField.endsWith("*") && !inputField.endsWith("/");
    }

    private boolean isLastElementOpeningBracket() {
        String inputField = tvInputField.getText().toString();

        return !inputField.endsWith("(");
    }

    private boolean isLastElementClosingBracket() {
        String inputField = tvInputField.getText().toString();

        return !inputField.endsWith(")");
    }

    private boolean isLastElementComma() {
        String inputField = tvInputField.getText().toString();

        return !inputField.endsWith(",");
    }

    private boolean isLastElementNumber() {
        String inputField = tvInputField.getText().toString();

        if (!inputField.isEmpty()) {
            char lastChar = inputField.charAt(inputField.length() - 1);
            return Character.isDigit(lastChar);
        }

        return false;
    }

    private boolean isLastCommaNumber() {
        String inputField = tvInputField.getText().toString();

        if (inputField.isEmpty()) {
            return false;
        }

        int i = inputField.length() - 1;

        while (i >= 0 && (Character.isDigit(inputField.charAt(i)) || inputField.charAt(i) == ',')) {
            i--;
        }

        String lastNumber = inputField.substring(i + 1);

        return lastNumber.contains(",");
    }

    private boolean hasUnclosedBracket() {
        String inputField = tvInputField.getText().toString();
        int open = 0;
        int closed = 0;

        for (char c : inputField.toCharArray()) {
            if (c == '(') {
                open++;
            }

            if (c == ')') {
                closed++;
            }
        }

        return open > closed;
    }
}