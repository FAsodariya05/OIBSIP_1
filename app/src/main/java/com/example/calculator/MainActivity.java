package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView result;
    String currentInput = "";
    double num1 = 0;
    String operator = "";
    String displayContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.display);

        setupNumberPad();
        setupOperationButtons();
    }

    private void setupNumberPad() {
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9, R.id.btnDot
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(view -> {
                Button button = (Button) view;
                currentInput += button.getText().toString();
                displayContent += button.getText().toString();
                result.setText(displayContent); // Update the display with numbers entered
            });
        }
    }

    private void setupOperationButtons() {
        findViewById(R.id.btnAdd).setOnClickListener(view -> setOperator("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(view -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(view -> setOperator("*"));
        findViewById(R.id.btnDivide).setOnClickListener(view -> setOperator("/"));
        findViewById(R.id.btnEquals).setOnClickListener(view -> calculateResult());
        findViewById(R.id.btnClear).setOnClickListener(view -> clearCalculator());
    }

    @SuppressLint("SetTextI18n")
    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            num1 = Double.parseDouble(currentInput);
            operator = op;
            currentInput = "";
            displayContent += " " + operator + " ";
            result.setText(displayContent); // Show the operator on the display
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double num2 = Double.parseDouble(currentInput);
            double resultValue;

            switch (operator) {
                case "+":
                    resultValue = num1 + num2;
                    break;
                case "-":
                    resultValue = num1 - num2;
                    break;
                case "*":
                    resultValue = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        result.setText("Cannot divide by zero");
                        return;
                    }
                    resultValue = num1 / num2;
                    break;
                default:
                    resultValue = 0;
            }

            displayContent += " = " + resultValue;
            result.setText(displayContent); // Show the complete equation and result
            currentInput = "";
            operator = "";
            displayContent = "";
        }
    }

    private void clearCalculator() {
        currentInput = "";
        operator = "";
        displayContent = "";
        result.setText("0"); // Reset the display
    }
}
