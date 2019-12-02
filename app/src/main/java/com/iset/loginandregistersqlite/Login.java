package com.iset.loginandregistersqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    Button b1;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = findViewById(R.id.editTextEmail);
        e2 = findViewById(R.id.editTextPassword);
        b1 = findViewById(R.id.buttonLogin);

        db = new DatabaseHelper(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                boolean checkEmailPassword = db.emailPassword(email,password);
                if(checkEmailPassword)
                    Toast.makeText(getApplicationContext(),"Login réussi",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Erreur de l'email ou mot de passe", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
