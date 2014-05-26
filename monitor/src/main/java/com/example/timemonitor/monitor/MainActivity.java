package com.example.timemonitor.monitor;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;


public class MainActivity extends ActionBarActivity {

    private Spinner profileDropDown;
    private CountDownTimer timer;
    private Button startButton;
    private Button stopButton;

    private static final String[] Profiles = {"Workout", "Work"};

    private Time startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileDropDown = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Profiles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profileDropDown.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void startTimer(View view) {
        startTime = new Time();
        startTime.setToNow();

        startButton = (Button)findViewById(R.id.startbutton);
        startButton.setEnabled(false);

        stopButton = (Button)findViewById(R.id.stopbutton);
        stopButton.setEnabled(true);

        final TextView timerText = (TextView)findViewById(R.id.timertext);

        timer = new CountDownTimer(30000, 1000)
        {

            @Override
            public void onTick(long l) {
                Time currentTime = new Time();
                currentTime.setToNow();

                long timeDiff = (currentTime.toMillis(false) - startTime.toMillis(false)) / 1000;

                timerText.setText("" + timeDiff);
            }

            @Override
            public void onFinish() {
                timer.start();
            }
        }.start();
    }

    public void stopTimer(View view) {
        if(timer != null)
        {
            timer.cancel();
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
        }
    }
}
