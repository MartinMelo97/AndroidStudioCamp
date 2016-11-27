package actosoft.org.scenetransitiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class SceneTransitionActivity extends AppCompatActivity {

    ViewGroup contenedor;
    Scene scene1;
    Scene scene2;
    Transition transitionmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_transition);

        //colocamos la escena1
        contenedor = (ViewGroup) findViewById(R.id.rootContainer);
        transitionmanager = TransitionInflater.from(this).inflateTransition(R.transition.transition);
        scene1 = Scene.getSceneForLayout(contenedor,R.layout.scene1_layout,this);
        scene1.enter();
        scene2 = Scene.getSceneForLayout(contenedor,R.layout.scene2_layout,this);
    } //onCreate

    public void goToScene2(View v){
        TransitionManager.go(scene2,transitionmanager);
    }

    public void goToScene1(View v){
        TransitionManager.go(scene1,transitionmanager);
    }
} //class
