package actosoft.org.videorecorder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // declaramos las variables contenedoras
    private static final int VIDEO_CAPTURE = 101;
    private static final int IMAGE_CAPTURE = 115;
    private Uri fileUri; //Ruta de donde se guardara el archivo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checamos que exista boton en el celular
        Button elboton = (Button) findViewById(R.id.elboton);
        if(!hasCamera()){
            elboton.setEnabled(false);
        }

    }//onCreate

    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature((PackageManager.FEATURE_CAMERA_ANY));
    }//hasCamera

    public void videoGrabar(View view){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_CAPTURE);
    }

    public void tomarFoto(View view){
        Intent intentfoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentfoto, IMAGE_CAPTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Uri videoUri = data.getData();
        if(requestCode == VIDEO_CAPTURE){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "Tu video esta en: \n"+videoUri, Toast.LENGTH_LONG).show();
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this,"Grabacion cancelada por puto", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Algo falló", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
