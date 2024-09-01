package com.example.h2otimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            int originalPaddingLeft = v.getPaddingLeft();
            int originalPaddingTop = v.getPaddingTop();
            int originalPaddingRight = v.getPaddingRight();
            int originalPaddingBottom = v.getPaddingBottom();

            v.setPadding(
                    systemBars.left + originalPaddingLeft,
                    systemBars.top + originalPaddingTop,
                    systemBars.right + originalPaddingRight,
                    systemBars.bottom + originalPaddingBottom
            );
            return insets;
        });
        Button btnIniciar = findViewById(R.id.btn_Iniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        EditText nombre = findViewById(R.id.Nombre);
        EditText correo = findViewById(R.id.Correo);
        EditText pass = findViewById(R.id.Pass);
        Button btnRegistro = findViewById(R.id.btn_Registro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoInput = correo.getText().toString().trim();
                String passInput = pass.getText().toString().trim();
                String nombreInput = nombre.getText().toString().trim();

                if (!correoInput.isEmpty() && !passInput.isEmpty() && !nombreInput.isEmpty()) {
                    Intent intent = new Intent(Registro.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Registro.this, "Por favor, ingrese todos los datos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}