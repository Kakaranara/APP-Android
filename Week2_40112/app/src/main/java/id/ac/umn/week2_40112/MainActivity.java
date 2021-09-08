package id.ac.umn.week2_40112;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    EditText angka1,angka2;
    TextView hasil;
    Button btnTambah, btnKurang, btnKali, btnBagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angka1 = (EditText) this.findViewById(R.id.angka1);
        angka2 = (EditText) this.findViewById(R.id.angka2);
        hasil = (TextView) this.findViewById(R.id.hasil);
        btnTambah = (Button) this.findViewById(R.id.btnTambah);
        btnKurang = (Button) this.findViewById(R.id.btnKurangi);
        btnKali = (Button) this.findViewById(R.id.btnKali);
        btnBagi = (Button) this.findViewById(R.id.btnBagi);

        btnTambah.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ hitung('+');}
        });
        btnKurang.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ hitung('-');}
        });
        btnKali.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ hitung('*');}
        });
        btnBagi.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ hitung('/');}
        });
    }

    protected void hitung(char operator){
        try{
            double operand1 = Double.parseDouble(angka1.getText().toString());
            double operand2 = Double.parseDouble(angka2.getText().toString());
            double result = 0.0;
            switch(operator){
                case('+'): result = operand1 + operand2; break;
                case('-'): result = operand1 - operand2; break;
                case('*'): result = operand1 * operand2; break;
                case('/'): result = operand1 / operand2; break;
            }
            hasil.setText(String.valueOf(result));
        }
        catch(Exception e){
            hasil.setText(e.getMessage());
        }
    }
}