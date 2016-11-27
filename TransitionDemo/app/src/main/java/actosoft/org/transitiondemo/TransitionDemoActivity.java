package actosoft.org.transitiondemo;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BaseInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

public class TransitionDemoActivity extends AppCompatActivity {

    ViewGroup myLayout;
    Button botoncito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_demo);

        //Seteamos el observador/escuchador/mutilador

        myLayout = (ViewGroup) findViewById(R.id.activity_transition_demo);
        myLayout.setOnTouchListener(
                new RelativeLayout.OnTouchListener(){
                    public boolean onTouch(View v, MotionEvent m){
                        handleTouch();
                        return true;
                    }
                }
        ); //aqui termina el observador

        botoncito = (Button) findViewById(R.id.mybotoncito);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                handleButton();

            }

        };

        botoncito.setOnClickListener(listener);
    } //metodo onCreate

    public void handleTouch(){
        //Traemos el boton como un un View, no hay que castear porque es View
        View  view = findViewById(R.id.mybotoncito);

        //Customizamos la animacion
        Transition changeBounds = new ChangeBounds(); //Instanciamos las animaciones
        changeBounds.setDuration(3000);
        float var =3;
        changeBounds.setInterpolator(new FastOutSlowInInterpolator());
        TransitionManager.beginDelayedTransition(myLayout, changeBounds); //Awebo es al Layout

        //Seteamos los cambios en el layout
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        //Le pasamos los parámetros al boton
        view.setLayoutParams(params);

        ViewGroup.LayoutParams lparams = view.getLayoutParams(); //Sacamos los parámetros del boton
        lparams.width = 500;
        lparams.height = 350; //Está en dp
        view.setLayoutParams(lparams);
    } //Fin handleTouch

    public void handleButton(){
        botoncito = (Button) findViewById(R.id.mybotoncito);

        Transition changeBounds = new ChangeBounds();
        changeBounds.setDuration(2000);
        changeBounds.setInterpolator(new DecelerateInterpolator());
        TransitionManager.beginDelayedTransition(myLayout, changeBounds);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        botoncito.setLayoutParams(params);

        ViewGroup.LayoutParams lparams = botoncito.getLayoutParams();
        lparams.width = 400;
        lparams.height = 250;
        botoncito.setLayoutParams(lparams);
    }
} //Class
