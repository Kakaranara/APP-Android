package umn.ac.id.week07b_40112;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetilVideoActivity2 extends AppCompatActivity {
    private VideoView vvDetil;
    private EditText etJudul, etKeterangan, etUri;
    private Button btnKembali;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_video2);
        vvDetil = (VideoView) findViewById(R.id.vvDetil);
        etJudul = (EditText) findViewById(R.id.etJudul);
        etKeterangan = (EditText) findViewById(R.id.etKeterangan);
        etUri = (EditText) findViewById(R.id.etUri);
        btnKembali = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        SumberVideo sv = (SumberVideo) bundle.getSerializable("DetilVideo");
        etJudul.setText(sv.getJudul());
        etKeterangan.setText(sv.getKeterangan());
        etUri.setText(sv.getVideoURI());
        vvDetil.setVideoURI(Uri.parse(sv.getVideoURI()));
        vvDetil.seekTo(100);
        vvDetil.start();
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(vvDetil);
        vvDetil.setMediaController(controller);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}