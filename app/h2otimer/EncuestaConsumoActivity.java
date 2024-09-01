package com.example.h2otimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EncuestaConsumoActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_encuesta_consumo);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // Get original XML padding
            int originalPaddingLeft = v.getPaddingLeft();
            int originalPaddingTop = v.getPaddingTop();
            int originalPaddingRight = v.getPaddingRight();
            int originalPaddingBottom = v.getPaddingBottom();

            // Set combined padding (system bars + XML padding)
            v.setPadding(
                    systemBars.left + originalPaddingLeft,
                    systemBars.top + originalPaddingTop,
                    systemBars.right + originalPaddingRight,
                    systemBars.bottom + originalPaddingBottom
            );
            return insets;
        });

        radioGroup = findViewById(R.id.radio_group);
        btnGuardar = findViewById(R.id.btn_guardar);

        btnGuardar.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                // No radio button selected
                showWarningMessage("Seleccione una opción");
            } else {
                RadioButton selectedRadioButton = findViewById(selectedId);
                String opcionSeleccionada = selectedRadioButton.getText().toString();

                // Start CalculadorConsumoActivity with selected option
                Intent intent = new Intent(EncuestaConsumoActivity.this, MainActivity.class);
                intent.putExtra("opcionSeleccionada", opcionSeleccionada);
                startActivity(intent);
            }
        });
    }

    private void showWarningMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
