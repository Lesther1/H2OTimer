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

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
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

        Button btnRegistro = findViewById(R.id.btn_registro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
                finish();
            }
        });
        EditText correo = findViewById(R.id.correo);
        EditText pass = findViewById(R.id.pass);
        Button btnSesion = findViewById(R.id.btn_Sesion);

        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoInput = correo.getText().toString().trim();
                String passInput = pass.getText().toString().trim();

                if (!correoInput.isEmpty() && !passInput.isEmpty()) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Por favor, ingrese su correo y contraseña.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}