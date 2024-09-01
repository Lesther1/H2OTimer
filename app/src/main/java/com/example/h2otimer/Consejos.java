package com.example.h2otimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Consejos extends AppCompatActivity {

    private String[] consejosArray = {
            "Cierra el grifo mientras te cepillas los dientes.",
            "Repara cualquier fuga en tu hogar.",
            "Utiliza un balde para lavar el auto en lugar de una manguera.",
            "Instala dispositivos ahorradores de agua en tu hogar.",
            "Riega las plantas temprano en la mañana o tarde en la noche."
    };
    private int consejoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consejos);

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

        TextView textViewConsejos = findViewById(R.id.Consejos);
        Button btnIniciar = findViewById(R.id.btn_iniciar);
        Button btnVolver = findViewById(R.id.btn_detener);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewConsejos.setText(consejosArray[consejoIndex]);
                consejoIndex++;
                if (consejoIndex >= consejosArray.length) {
                    consejoIndex = 0;
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consejos.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
