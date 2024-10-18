package com.example.ev8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;

public class Register extends AppCompatActivity {

    DatabaseReference  databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://evo8-e18d0-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText nombres = findViewById( R.id.nombres);
        final EditText correo = findViewById( R.id.correo);
        final EditText celular = findViewById( R.id.celular);
        final EditText contraseña1 = findViewById( R.id.Contraseña1);
        final EditText contraseña2 = findViewById( R.id.Contraseña2);

        final Button btnRegistro = findViewById( R.id.BtnRegistro);
        final TextView ingresaAhora = findViewById( R.id.ingresarAhora);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String nombreTxt = nombres.getText().toString();
                final String correoTxt = correo.getText().toString();
                final String celularTxt = celular.getText().toString();
                final String contraseña1Txt = contraseña1.getText().toString();
                BreakIterator Contraseña2;

                if (nombreTxt.isEmpty() || correoTxt.isEmpty() || celularTxt.isEmpty() || contraseña1Txt.isEmpty()) {
                    Toast.makeText(Register.this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
                }

                else if (!contraseña1Txt.equals(contraseña1Txt)){
                    Toast.makeText(Register.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                }
                else {

                    databaseReference.child("usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(celularTxt)){
                                Toast.makeText(Register.this, "Celular Registrado.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("usuarios").child(celularTxt).child("nombres").setValue(nombreTxt);
                                databaseReference.child("usuarios").child(celularTxt).child("correo").setValue(correoTxt);
                                databaseReference.child("usuarios").child(celularTxt).child("contraseña").setValue(contraseña1Txt);

                                Toast.makeText(Register.this, "Usuario registrado Exitosamente.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        ingresaAhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}