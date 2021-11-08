package umn.ac.id.week10_40112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

public class IntentService2 extends AppCompatActivity {
    CustomBoundService customBoundService;
    boolean isBound = false;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CustomBoundService.CustomLocalBinder binder =
                    (CustomBoundService.CustomLocalBinder) iBinder;
            customBoundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        Button btnStartSimple = findViewById(R.id.simpleService);
        Button btnStart = findViewById(R.id.main_button_startservice);
        Button btnShowTime = findViewById(R.id.main_button_showtime);

        btnStart.setOnClickListener(view->{
            Intent intent2 = new Intent(IntentService2.this, CustomService.class);
            startService(intent2);
        });
        btnStartSimple.setOnClickListener(view -> {
            Intent intent = new Intent(IntentService2.this, SimpleIntentService.class);
            startActivity(intent);
        });

        Intent intent = new Intent(this, CustomBoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        btnShowTime.setOnClickListener(view->{
            String currentTime = customBoundService.getCurrentTime();
            Toast.makeText(getApplicationContext(), currentTime, Toast.LENGTH_SHORT).show();
        });
    }
}