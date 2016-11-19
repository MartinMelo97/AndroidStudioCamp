package actosoft.org.touchdetector;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private GestureDetectorCompat detector;
    private TextView mostrador;
    int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout myLayout = (ConstraintLayout) findViewById(R.id.activity_main);
        myLayout.setOnTouchListener(new ConstraintLayout.OnTouchListener(){
           public boolean onTouch(View v, MotionEvent m){
               handleTouch(m);
               return true;
           }

        });

        mostrador = (TextView) findViewById(R.id.contactortoques);
        this.detector = new GestureDetectorCompat(this,this);
        detector.setOnDoubleTapListener(this);

    }

    @Override
    public void onLongPress(MotionEvent event){
        mostrador.setText("Long Presss");
    }

    @Override
    public boolean onDown(MotionEvent event){
        mostrador.setText("On Down");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event){
        contador = contador + 1;
        mostrador.setText(contador);
        return true;
    }
    @Override
    public boolean onDoubleTapEvent(MotionEvent event){
        mostrador.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event){
        mostrador.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent ev1, MotionEvent ev2, float velocity, float velocity2){
        mostrador.setText("onFling");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent ev1,MotionEvent ev2, float vel, float vel2){
        mostrador.setText("onScroll");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event){
        mostrador.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event){
        mostrador.setText("onSingleTapUp");
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    void handleTouch(MotionEvent m){
        TextView textView1 = (TextView) findViewById(R.id.textView5);
        TextView textView2 = (TextView) findViewById(R.id.textView6);
        FloatingActionButton botoncito = (FloatingActionButton) findViewById(R.id.botondedo);
        int pointercont = m.getPointerCount();

        for (int i = 0; i < pointercont; i++){
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            int id = m.getPointerId(i);
            int action =  m.getActionMasked();
            int actionIndex = m.getActionIndex();
            String actionString;
            switch(action){

                case MotionEvent.ACTION_UP:
                    actionString = "UP";
                    break;
                case MotionEvent.ACTION_DOWN:
                    actionString = "DOWN";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "POINTER DOWN";
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "POINTER UP";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
                    break;
            }
            String touchStatus = "Action: "+actionString+" Index: "+actionIndex+" Id: "+id+" X: "+x+" Y: "+y;
            if(id == 0){
                textView1.setText(touchStatus);
                botoncito.setVisibility(View.VISIBLE);
                botoncito.setX(x);
                botoncito.setY(y);
            }
            else{
                textView2.setText(touchStatus);
            }
        }
    }
}
