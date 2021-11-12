package umn.ac.id.week12_40112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class PembacaanSensor extends AppCompatActivity implements SensorEventListener {
    private TextView tvLight,tvProximity;
    private Sensor sensorLight,sensorProximity;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembacaan_sensor);

        tvLight = findViewById(R.id.lightSensor);
        tvProximity = findViewById(R.id.proximitySensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];
        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                tvLight.setText("LightSensor : " + String.format("%.2f",currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                tvProximity.setText("Proximity Sensor : " + String.format("%.2f",currentValue)  );
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sensorProximity != null){
            sensorManager.registerListener(this, sensorProximity, sensorManager.SENSOR_DELAY_NORMAL);
        }
        if(sensorLight != null){
            sensorManager.registerListener(this, sensorLight, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}