package com.example.seatwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button navigateToLoginPageButton, navigateToRegisterPageButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            Intent nav = new Intent(MainActivity.this, ContentPage.class);
            startActivity(nav);
            finish();
        }
        else{
            setContentView(R.layout.activity_main);
            navigateToLoginPageButton = findViewById(R.id.navigateToLoginPageButton);
            navigateToRegisterPageButton = findViewById(R.id.navigateToRegisterPageButton);

            navigateToLoginPageButton.setOnClickListener(v -> {
                Intent nav = new Intent(MainActivity.this, LoginPage.class);
                startActivity(nav);
                finish();
            });

            navigateToRegisterPageButton.setOnClickListener(v -> {
                Intent nav = new Intent(MainActivity.this, RegisterPage.class);
                startActivity(nav);
                finish();
            });
        }
    }
}