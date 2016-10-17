package com.sunlg.timebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TimeButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (TimeButton) findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setSecond(30);
        button.init();

    }

    @Override
    public void onClick(View v) {
        button.start();
    }
}
