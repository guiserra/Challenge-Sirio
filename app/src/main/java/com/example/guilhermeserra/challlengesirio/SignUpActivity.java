package com.example.guilhermeserra.challlengesirio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        findViewById(R.id.cardViewLogin).setOnClickListener(this);
        findViewById(R.id.txtViewSignIn).setOnClickListener(this);
    }

    private void registerUser(){
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();





        if(email.isEmpty()){
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Please enter a valid email");
            edtEmail.requestFocus();
            return;
        }

        if(password.length()<6){
            edtPassword.setError("Minimum length of password should be 6");
            edtPassword.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edtPassword.setError("Password is required");
            edtPassword.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardViewLogin:
                registerUser();
                break;
            case R.id.txtViewSignIn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
