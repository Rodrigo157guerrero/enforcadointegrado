package com.example.forca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button b, b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b = findViewById(R.id.id1);
        b.setOnClickListener(this);

        b1 = findViewById(R.id.miguel);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == b) {
            startActivity(new Intent(this, TelaJogo.class));
        }
        if (v == b1){
            startActivity(new Intent(this, TelaJogo.class));
        }

    }
}