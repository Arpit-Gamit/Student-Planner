package com.example.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.studentplanner.databinding.ActivityLoginBinding;
import com.example.studentplanner.databinding.ActivityRegistrationFormBinding;

public class RegistrationForm extends AppCompatActivity {

    ActivityRegistrationFormBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.SButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the data which has been entered on edittext.
                String username = binding.username.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();
                String confirmpass = binding.confPassword.getText().toString();
                if (username.equals("") || email.equals("") || password.equals("") || confirmpass.equals("")) {
                    Toast.makeText(RegistrationForm.this, "Provide All Information!!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkUser = databaseHelper.checkusername(username);
                    if (checkUser == false) {

                        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            boolean checkEmail = databaseHelper.checkuseremail(email);
                            if (checkEmail == false) {
                                if (password.length() >= 6) {
                                    if (password.equals(confirmpass)) {
                                        boolean insert = databaseHelper.insert(username, email, password);
                                        if (insert == true) {
                                            Toast.makeText(RegistrationForm.this, "Data inserted successfully!!!", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                            startActivity(i);
                                            Toast.makeText(RegistrationForm.this, "Login Yourself!!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(RegistrationForm.this, "Signup Failed!!", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(RegistrationForm.this, "Password Must Be Same!!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(RegistrationForm.this, "password length should be greater than 6!!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegistrationForm.this, "Email is already Taken!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistrationForm.this, "Enter Valid Email Address!!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistrationForm.this, "User Already Exits!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Textview to go back to login page.
        binding.LoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(a);
            }
        });

    }

    // Method to disable back button.
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

}