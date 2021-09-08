package umn.ac.id.week04a_40112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etIsian, etUrl;
    private Button btnKirim, btnBrowse, main_button_change_1, main_button_change_2;
    private TextView tvJawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etIsian = findViewById(R.id.isian);
        etUrl = findViewById(R.id.url);
        btnBrowse = findViewById(R.id.btnBrowse);
        btnKirim = findViewById(R.id.btnKirim);
        tvJawaban = findViewById(R.id.jawaban);
        main_button_change_1 = findViewById(R.id.main_button_change_1);
        main_button_change_2 = findViewById(R.id.main_button_change_2);

        btnBrowse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String urlText = etUrl.getText().toString();
                if(urlText.isEmpty())
                    urlText = "http://www.umn.ac.id/";
                Intent browseIntent = new Intent(Intent.ACTION_VIEW);
                browseIntent.setData(Uri.parse(urlText));
                if(browseIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(browseIntent);
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentDua = new Intent(MainActivity.this, ActivityDua.class);
                String isian = etIsian.getText().toString();
                intentDua.putExtra("PesanDariMain",isian);
                startActivityForResult(intentDua,1);
            }
        });

        main_button_change_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent keActivity2 = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(keActivity2,1);
            }
        });

        main_button_change_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent keActivity3 = new Intent(MainActivity.this, ThirdActivity.class);
                startActivityForResult(keActivity3,1);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String jawaban = data.getStringExtra("Jawaban");
                tvJawaban.setText(jawaban);
            }
        }
    }

}