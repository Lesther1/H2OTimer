package com.example.h2otimer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculadorConsumoActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private long startTime = 0;
    private boolean isRunning = false;
    private String opcionSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculador_consumo);

        // Combine systemBars padding with XML padding
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

        Button btnIniciar = findViewById(R.id.btn_iniciar);
        Button btnDetener = findViewById(R.id.btn_detener);
        TextView crono = findViewById(R.id.crono);
        ImageView waterImage1 = findViewById(R.id.water_image1);
        ImageView waterImage2 = findViewById(R.id.water_image2);

        crono.setVisibility(View.GONE);
        waterImage1.setVisibility(View.GONE);
        waterImage2.setVisibility(View.GONE);
        btnDetener.setVisibility(View.GONE);

        // Retrieve the selected option from the Intent
        Intent intent = getIntent();
        opcionSeleccionada = intent.getStringExtra("opcionSeleccionada");

        btnIniciar.setOnClickListener(v -> {
            startTime = System.currentTimeMillis();
            isRunning = true;
            handler.post(updateTimer);

            waterImage1.setVisibility(View.VISIBLE);
            waterImage2.setVisibility(View.VISIBLE);
            crono.setVisibility(View.VISIBLE);

            btnIniciar.setVisibility(View.GONE);
            btnDetener.setVisibility(View.VISIBLE);
        });

        btnDetener.setOnClickListener(v -> {
            // Stop the timer
            isRunning = false;
            handler.removeCallbacks(updateTimer);

            long elapsedTime = System.currentTimeMillis() - startTime;

            // Create EncuestaData object
            EncuestaData encuestaData = new EncuestaData(opcionSeleccionada, elapsedTime);

            // Pass EncuestaData to next activity if needed
            Intent nextIntent = new Intent(CalculadorConsumoActivity.this, EncuestaConsumoActivity.class);
            nextIntent.putExtra("encuestaData", encuestaData);
            startActivity(nextIntent);
        });
    }

    private final Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                int seconds = (int) (elapsedTime / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                TextView crono = findViewById(R.id.crono);
                crono.setText(String.format("%02d:%02d", minutes, seconds));

                handler.postDelayed(this, 1000);
            }
        }
    };
}
