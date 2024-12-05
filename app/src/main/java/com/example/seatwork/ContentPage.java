package com.example.seatwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ContentPage extends AppCompatActivity {
    private TextView usersEmail;
    private Button LogoutButton;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_content_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usersEmail = findViewById(R.id.usersEmail);
        LogoutButton = findViewById(R.id.LogoutButton);
        usersEmail.setText(LoginPage.getUsersEmail());

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            usersEmail.setText(user.getEmail());
        }
        else{
            Intent nav = new Intent(ContentPage.this, MainActivity.class);
            startActivity(nav);
            finish();
        }

        LogoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            Intent nav = new Intent(ContentPage.this, MainActivity.class);
            startActivity(nav);
            finish();
        });
    }
}