package com.example.ev8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usuario = findViewById( R.id.usuario);
        final EditText contraseña = findViewById( R.id.Contraseña);
        final Button loginBtn = findViewById( R.id.loginBtn);
        final TextView registrasteAhora = findViewById( R.id.RegistrateAhora);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usuarioTxt = usuario.getClass().toString();
                final String contraseñaTxt = contraseña.getText().toString();

                if (usuarioTxt.isEmpty() || contraseñaTxt.isEmpty()){
                    Toast.makeText(Login.this, "Por favor ingresa tu Usuario", Toast.LENGTH_SHORT).show();
                }
                else{

                }
            }
        });

       registrasteAhora.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               // abrir registro activity
               startActivity(new Intent(Login.this,Register.class));
           }
       });

    }
}