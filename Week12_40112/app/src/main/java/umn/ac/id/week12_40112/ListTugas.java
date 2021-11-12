package umn.ac.id.week12_40112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListTugas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tugas);

        Button tutor1 = findViewById(R.id.btnTutor1);
        Button tutor2 = findViewById(R.id.btnTutor2);

        tutor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListTugas.this, MainActivity.class);
                startActivity(i);
            }
        });

        tutor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListTugas.this, PembacaanSensor.class);
                startActivity(i);
            }
        });
    }
}