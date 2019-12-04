package com.iset.loginandregistersqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1, e2, e3;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        e1 = findViewById(R.id.email);
        e2 = findViewById(R.id.pass);
        e3 = findViewById(R.id.cpass);
        b1 = findViewById(R.id.register);
        b2 = findViewById(R.id.login);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();

                if (s1.equals("") || s2.equals("") || s3.equals("")) {
                    Toast.makeText(getApplicationContext(), "Les champs sont vides",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (s2.equals(s3)) {
                        boolean checkMail = db.checkMail(s1);
                        if (checkMail) {
                            boolean insert = db.insert(s1, s2);
                            if (insert) {
                                Toast.makeText(getApplicationContext(), "Enregistrement réussi",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Email existant !!!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(getApplicationContext(),
                                "Les mots de passe ne se correspondent pas",
                                Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}