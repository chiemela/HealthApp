package com.eliamtechnologies.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edConfirmPassword;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextAppFullName);
        edEmail = findViewById(R.id.editTextAppAddress);
        edPassword = findViewById(R.id.editTextAppFees);
        edConfirmPassword = findViewById(R.id.editTextAppContactNumber);
        btn = findViewById(R.id.buttonBookAppointment);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPassword = edConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "healthier", null, 1);
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirmPassword.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill out all details", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(confirmPassword) == 0){
                        if(isValid(password)){
                            db.register(username, email, password);
                            Toast.makeText(getApplicationContext(),"Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit and special symbol", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Passwords match", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });
    }

    public static boolean isValid(String passwordchecker){
        int f1=0, f2=0, f3=0;
        if (passwordchecker.length() < 0){
            return false;
        }else{
            for (int p = 0; p < passwordchecker.length(); p++){
                if (Character.isLetter(passwordchecker.charAt(p))){
                    f1=1;
                }
            }
            for (int r = 0; r < passwordchecker.length(); r++){
                if (Character.isDigit(passwordchecker.charAt(r))){
                    f2=1;
                }
            }
            for (int s = 0; s < passwordchecker.length(); s++){
                char c = passwordchecker.charAt(s);
                if(c >= 33 || c <= 46 || c == 64){
                    f3=1;
                }
            }
            if(f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;
        }
    }
}