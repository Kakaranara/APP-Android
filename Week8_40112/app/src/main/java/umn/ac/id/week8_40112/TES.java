package umn.ac.id.week8_40112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.util.Output;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TES extends AppCompatActivity {
    private RadioGroup rgJenis;
    private EditText etFileName, etText;
    private File tempDir, lokalDir, extDir, curDir;
    private Context context;
    private Button btnOpen;
    private static PopupMenu pilihFile;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File[] tempFiles = tempDir.listFiles();
        for(File tempfile: tempFiles){
            if(tempfile.isFile())tempfile.delete();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes);

        rgJenis = (RadioGroup) findViewById(R.id.rgJenis);
        etFileName = (EditText) findViewById(R.id.etNamaFile);
        etText = (EditText) findViewById(R.id.etText);
        getSupportActionBar().setTitle("Text Editor and Storage");

        //files
        tempDir = getCacheDir();
        lokalDir = getFilesDir();
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            extDir = getExternalFilesDir(null);
        }else{
            findViewById(R.id.rbExternal).setEnabled(false);
            extDir = null;
        }
        curDir = lokalDir;

        rgJenis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String pilihan = ((RadioButton) findViewById(rgJenis.getCheckedRadioButtonId())).getText().toString();
                if(pilihan.equalsIgnoreCase("Temporary"))
                    curDir = tempDir;
                else if(pilihan.equalsIgnoreCase("Internal"))
                    curDir = lokalDir;
                else curDir = extDir;
            }
        });

        //popup menu
        context = this;
        btnOpen = (Button) findViewById(R.id.btnOpen);
        pilihFile = new PopupMenu(context,btnOpen);
        pilihFile.getMenuInflater().inflate(R.menu.menu_kosong, pilihFile.getMenu());
        pilihFile.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                etFileName.setText(menuItem.getTitle().toString());
                etFileName.setText("");
                etText.setText("");
                return true;
            }
        });
    }

    public void openFile(View view){
        File[] files = null;
        if(curDir != null)
            files = curDir.listFiles();
        if(files != null){
            int n = files.length;
            pilihFile.getMenu().clear();
            for(int i=0; i < files.length; i++){
                pilihFile.getMenu().add(files[i].getName());
            }
            pilihFile.show();
            readFile();
        }
        else{
            Toast.makeText(context, "Ada masalah akses folder atau folder masih kosong", Toast.LENGTH_LONG).show();
        }
    }

    private void readFile(){
        if(etFileName.getText().toString().length()>0){
            File file = new File(curDir, etFileName.getText().toString());
            String isiFile = "";
            try{
                InputStream inputStream = new FileInputStream(file);
                if(inputStream != null){
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String terimaString =  "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while((terimaString = bufferedReader.readLine()) != null){
                        stringBuilder.append(terimaString).append("\n");
                    }
                    inputStream.close();
                    isiFile = stringBuilder.toString();
                    etText.setText(isiFile);
                }
            }catch (FileNotFoundException e){
                Toast.makeText(context, "File tidak ditemukan",Toast.LENGTH_LONG).show();
            }catch (IOException e){
                Toast.makeText(context, "I/O Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void saveFile(View view){
        String nFile = etFileName.getText().toString();
        String isiText = etText.getText().toString();
        if(nFile.length() > 0 && isiText.length() > 0 && curDir != null){
            File file = new File(curDir, nFile);
            try{
                OutputStreamWriter writer = new OutputStreamWriter(
                        new FileOutputStream(file)
                );
                writer.write(isiText);
                writer.close();
                Toast.makeText(this, "Text sudah tersimpan", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e){
                Toast.makeText(this, "File tidak ditemukan", Toast.LENGTH_SHORT).show();
            } catch (IOException e ){
                Toast.makeText(this, "Ada kesalahan I/O", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void deleteFile(View view){
        if(etFileName.getText().toString().length()>0){
            boolean succes = false;
            if(curDir != null && curDir == lokalDir){
                succes = context.deleteFile(etFileName.getText().toString());
            }else{
                succes = new File(curDir, etFileName.getText().toString()).delete();
            }
            if(succes)
                Toast.makeText(context, "file berhasil dihapus", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "File GAGAL dihapus", Toast.LENGTH_SHORT).show();
            etFileName.setText("");
            etText.setText("");
        }
    }

    public void clearText(View view){
        etText.setText("");
        etFileName.setText("");
    }

    public void keluarApp(View view){
        finishAffinity();
    }
}