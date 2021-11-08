package umn.ac.id.week10_40112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button intentTutor1 = findViewById(R.id.tutor1);
        Button intentTutor2 = findViewById(R.id.tutor2);
        Button intentTutor3 = findViewById(R.id.tutor3);

        intentTutor1.setOnClickListener(view -> {
            Intent intent = new Intent(List.this, MainActivity.class);
            startActivity(intent);
        });

        intentTutor2.setOnClickListener(view->{
            Intent intent = new Intent(List.this, AsyncTaskLoader2.class);
            startActivity(intent);
        });

        intentTutor3.setOnClickListener(view -> {
            Intent intent = new Intent(List.this, IntentService2.class);
            startActivity(intent);
        });
    }
}