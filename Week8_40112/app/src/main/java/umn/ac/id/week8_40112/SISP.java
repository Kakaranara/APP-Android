package umn.ac.id.week8_40112;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SISP extends AppCompatActivity {
    private int mCount = 0;
    private int mWarna;
    private TextView tvCounter;
    private Context context;
    private final String COUNTER_KEY = "counter", WARNA_KEY = "warna";
    private SharedPreferences mPreferences;
    private String sharedPreFile;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY,mCount);
        outState.putInt(WARNA_KEY,mWarna);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.putInt(COUNTER_KEY, mCount);
        preferenceEditor.putInt(WARNA_KEY, mWarna);
        preferenceEditor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.putInt(COUNTER_KEY, 0);
        preferenceEditor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sisp);

        context = this;
        tvCounter = (TextView) findViewById(R.id.tvCounter);
        sharedPreFile = context.getPackageName();
        mPreferences = getSharedPreferences(sharedPreFile, MODE_PRIVATE);
        mCount = mPreferences.getInt(COUNTER_KEY,0);
        tvCounter.setText(String.valueOf(mCount));
        mWarna = mPreferences.getInt(WARNA_KEY,0);
        tvCounter.setBackgroundColor(mWarna);
        getSupportActionBar().setTitle("Saved Instance and Shared Preference");

        if(savedInstanceState != null){
            mCount = savedInstanceState.getInt(COUNTER_KEY);
            if(mCount != 0){
                tvCounter.setText(String.valueOf(mCount));
            }
            mWarna = savedInstanceState.getInt(WARNA_KEY);
            tvCounter.setBackgroundColor(mWarna);
        }
    }

    public void gantiBackground(View view){
        mWarna = ((ColorDrawable)view.getBackground()).getColor();
        tvCounter.setBackgroundColor(mWarna);
    }

    public void tambahCounter(View view){
        mCount++;
        tvCounter.setText(String.valueOf(mCount));
    }

    public void reset(View view){
        mCount = 0;
        tvCounter.setText(String.valueOf(mCount));
        mWarna = ContextCompat.getColor(context,R.color.gray);
        tvCounter.setBackgroundColor(mWarna);
    }
}