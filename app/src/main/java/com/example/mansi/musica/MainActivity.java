package com.example.mansi.musica;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //private static final int CLOSE_ID = Menu.FIRST+2;
    private ImageButton play;
    private ImageButton pause;
    private ImageButton stop;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=(ImageButton)findViewById(R.id.play);
        pause=(ImageButton)findViewById(R.id.pause);
        stop=(ImageButton)findViewById(R.id.stop);
        play.setOnClickListener(new View.OnClickListener() { public void onClick(View view)
            {
                mp.start();
                play.setEnabled(false);
                pause.setEnabled(true);
                stop.setEnabled(true);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() { public void onClick(View view)
            {
                mp.pause();
                play.setEnabled(true);
                pause.setEnabled(false);
                stop.setEnabled(true);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() { public void onClick(View view)
            {
                stop();
            }
        });

        try {
            mp=new MediaPlayer();
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { public void onPrepared(MediaPlayer mp)
                {
                    play.setEnabled(true);
                }
            });
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { public void onCompletion(MediaPlayer mp)
                {
                    stop();
                }
            });
            setup();
        } catch (Throwable t) {
            android.util.Log.e("AudioDemo", "Exception playing audio", t);
            Toast.makeText(this, "Ick!", Toast.LENGTH_LONG).show();
        }
    }

    /*@Override public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, CLOSE_ID, "Close", R.drawable.eject) .setAlphabeticShortcut('c');
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(Menu.Item item) {
        switch (item.getId()) {
            case CLOSE_ID: finish();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
*/
    private void stop() {
        mp.reset();
        setup();
    }
    private void setup() {
        play.setEnabled(false);
        pause.setEnabled(false);
        stop.setEnabled(false);
        try {
            mp = MediaPlayer.create(this, R.raw.a);
        }
        catch (Throwable t) {
            android.util.Log.e("AudioDemo", "Exception playing audio", t);
            Toast.makeText(this, "Ick!", Toast.LENGTH_LONG).show();
        } mp.prepareAsync();
    }
}
