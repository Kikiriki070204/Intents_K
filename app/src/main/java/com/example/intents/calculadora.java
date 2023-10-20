package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class calculadora extends AppCompatActivity {

    private TextView operacionK;
    private TextView resultadoK;

    private String data = "";
    private double num1 = Double.NaN;
    private double num2 = Double.NaN;
    private String signo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        operacionK= findViewById(R.id.opn);
        resultadoK= findViewById(R.id.res);

        // Configurar click listeners para los botones numéricos
        Button[] numButtons = new Button[]{
                findViewById(R.id.n0), findViewById(R.id.n1),
                findViewById(R.id.n2), findViewById(R.id.n3),
                findViewById(R.id.n4), findViewById(R.id.n5),
                findViewById(R.id.n6), findViewById(R.id.n7),
                findViewById(R.id.n8), findViewById(R.id.n9)
        };

        for (Button button : numButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data += button.getText().toString();
                    operacionK.setText(data);
                }
            });
        }

        // Configurar click listeners para los botones de operación
        findViewById(R.id.sum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation();
                signo = "+";
            }
        });

        findViewById(R.id.rt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation();
                signo = "-";
            }
        });

        findViewById(R.id.mul).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation();
                signo = "x";
            }
        });

        findViewById(R.id.div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation();
                signo = "/";
            }
        });

        // Configurar click listener para el botón igual
        findViewById(R.id.total).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation();
                signo = "";
            }
        });

        // Configurar click listener para el botón punto
        findViewById(R.id.pt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.contains(".")) {
                    data += ".";
                    operacionK.setText(data);
                }
            }
        });

        // Configurar click listener para el botón BORRAR
        findViewById(R.id.dt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.length() > 0) {
                    data = data.substring(0, data.length() - 1);
                    operacionK.setText(data);
                }
            }
        });

        // Configurar click listener para el botón LIMPIAR
        findViewById(R.id.c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void performOperation() {
        if (!Double.isNaN(num1)) {
            if (!data.isEmpty()) {
                num2 = Double.parseDouble(data);
                data = "";

                switch (signo) {
                    case "+":
                        num1 = num1 + num2;
                        break;
                    case "-":
                        num1 = num1 - num2;
                        break;
                    case "*":
                        num1 = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            num1 = num1 / num2;
                        } else {
                            resultadoK.setText("Error: División por cero");
                            clear();
                        }
                        break;
                }

                resultadoK.setText(String.valueOf(num1));
            }
        } else {
            try {
                num1 = Double.parseDouble(data);
            } catch (NumberFormatException e) {
                resultadoK.setText("Error");
                clear();
            }
            data = "";
        }
    }

    private void clear() {
        data = "";
        num1 = Double.NaN;
        num2 = Double.NaN;
        operacionK.setText("");
        resultadoK.setText("");
        signo = "";
    }
}