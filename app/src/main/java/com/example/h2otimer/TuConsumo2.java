package com.example.h2otimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class TuConsumo2 extends AppCompatActivity {

    private LinearLayout container;
    private List<EncuestaData> encuestaDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tu_consumo2);

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

        container = findViewById(R.id.container);

        Intent intent = getIntent();
        encuestaDataList = intent.getParcelableArrayListExtra("encuestaDataList");
        Button btnVolver = findViewById(R.id.btn_regresar);

        encuestaDataList = new ArrayList<>();
        encuestaDataList.add(new EncuestaData("Lavarse las manos", 12000));
        encuestaDataList.add(new EncuestaData("Ducharse", 35000));

        if (encuestaDataList != null) {
            for (EncuestaData data : encuestaDataList) {
                TextView textView = new TextView(this);
                textView.setText(data.getOpcionSeleccionada() + ": " + data.getTiempoEnMilisegundos() + " segundos");
                textView.setTextColor(getResources().getColor(android.R.color.white));
                textView.setTextSize(18);
                textView.setPadding(0, 10, 0, 10);

                // Agregar el TextView al LinearLayout
                container.addView(textView);
            }
        }

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TuConsumo2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
