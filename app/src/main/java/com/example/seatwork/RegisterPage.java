package com.example.seatwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends AppCompatActivity {
    private TextInputLayout TextInputLayout_1, TextInputLayout_2;
    private TextInputEditText email_input, password_input;
    private Button RegisterButton;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputLayout_1 = findViewById(R.id.TextInputLayout_1);
        TextInputLayout_2 = findViewById(R.id.TextInputLayout_2);
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        RegisterButton = findViewById(R.id.RegisterButton);

        RegisterButton.setOnClickListener(v -> {
            String email = email_input.getText().toString();
            String pass = password_input.getText().toString();

            FirebaseAuth.getInstance().signOut();

            if(email.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Fields must not be empty!", Toast.LENGTH_SHORT).show();
            }
            else{
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(RegisterPage.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent nav = new Intent(RegisterPage.this, LoginPage.class);
                                    startActivity(nav);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterPage.this, "Registered Unsuccessfully!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}