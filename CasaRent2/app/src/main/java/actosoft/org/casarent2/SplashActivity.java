package actosoft.org.casarent2;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView image;
    TextView text;
    ViewGroup mylayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image = (ImageView) findViewById(R.id.imageView);
        text = (TextView) findViewById(R.id.textView2);
        mylayout = (ViewGroup) findViewById(R.id.activity_splash);
        image.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
        Thread myThread = new Thread(){
            @Override
            public void run(){
                try{
                    image.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                    sleep(6000);
                    Intent myintent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(myintent);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                }//try
                catch(InterruptedException e){
                    e.printStackTrace();
                }//catch
            }//run
        };
        myThread.start();
    }//onCreate
}
