package com.example.framelayout;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
    
    private final int[] colors = new int[]  {
            R.color.red,
            R.color.orange,
            R.color.yellow,
            R.color.green,
            R.color.blue,
            R.color.indigo,
            R.color.purple,
    };
    
    private final int[] ids = new int[] {
            R.id.view01,
            R.id.view02,
            R.id.view03,
            R.id.view04,
            R.id.view05,
            R.id.view06,
            R.id.view07,
    };
    
    private int currentColor = 0;
    
    TextView[] views = new TextView[ids.length];
    
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) 
        {
            if (msg.what == 0x123) {
                for (int i = 0; i < ids.length; i++) {
                    views[i].setBackgroundResource(colors[(currentColor + i) % colors.length]);
                }
                
                currentColor++;
            } 
            
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < ids.length; i++) {
            views[i] = (TextView) findViewById(ids[i]);
        }
        
        new Timer().schedule(new TimerTask() {
            
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        }, 0, 200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
