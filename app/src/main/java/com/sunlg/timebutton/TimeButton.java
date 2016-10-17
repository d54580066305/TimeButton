package com.sunlg.timebutton;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sunlg on 16-10-15.
 */

public class TimeButton extends Button {

    private int second = 60;       //倒计时/秒，默认为60秒

    private String clickStr = "点击获取验证码";
    private String clickedStr = "秒后重新获取";
    private static int CLICK = 0;
    private Timer timer;
    private TimerTask timerTask;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what != CLICK){
                TimeButton.this.setText(msg.what+clickedStr);
            }
            if(msg.what == CLICK){
                TimeButton.this.setText(clickStr);
                TimeButton.this.setClickable(true);
            }
        }
    };

    public TimeButton(Context context) {
        super(context);
        TimeButton.this.setText(clickStr);
    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TimeButton.this.setText(clickStr);
    }

    public void setSecond(int second){
        this.second = second;
    }

    public void init(){

        timer = new Timer();

        timerTask = new TimerTask() {

            int temp = second;

            @Override
            public void run() {

                Message msg = new Message();

                if(--temp != 0){
                    msg.what = temp;
                }else if(temp == 0){
                    msg.what = 0;
                    timer.cancel();
                }
                handler.sendMessage(msg);
            }
        };
    }

    public void start() {
        timer.schedule(timerTask, 1000, 1000);
        this.setClickable(false);
    }
}
