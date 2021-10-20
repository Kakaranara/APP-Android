package umn.ac.id.week8_40112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnToTES, btnToSISP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToTES =  findViewById(R.id.intent1);
        btnToSISP = findViewById(R.id.intent2);

        btnToTES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoTES = new Intent(MainActivity.this, TES.class);
                gotoTES.putExtra("judulTES", btnToTES.getText().toString());
                startActivity(gotoTES);
            }
        });

        btnToSISP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoSISP = new Intent(MainActivity.this, SISP.class);
                gotoSISP.putExtra("judulSISP", btnToSISP.getText().toString());
                startActivity(gotoSISP);
            }
        });

    }
}