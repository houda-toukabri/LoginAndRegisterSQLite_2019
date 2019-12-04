package com.iset.loginandregistersqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText editTextLogin, editTextPassword;
    Button buttonLogin;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLogin = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        db = new DatabaseHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextLogin.getText().toString();
                String password = editTextPassword.getText().toString();
                boolean checkEmailPassword = db.emailPassword(email, password);
                if (checkEmailPassword)
                    Toast.makeText(getApplicationContext(), R.string.message_connection_success,
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), R.string.message_connection_failed,
                            Toast.LENGTH_SHORT).show();
            }
        });
    }
}