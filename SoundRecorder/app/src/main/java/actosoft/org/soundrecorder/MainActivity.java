package actosoft.org.soundrecorder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //codigos de los permisos
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;

    //putero de variables alv

    private static MediaRecorder mediarecorder;
    private static MediaPlayer mediaplayer;
    private static String audioFilePath;
    private static Button stopButton;
    private static Button playButton;
    private static Button recordButton;
    private boolean isRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //traemos los botones y checamos si el cel tiene mic
        recordButton = (Button) findViewById(R.id.record);
        stopButton = (Button) findViewById(R.id.stop);
        playButton = (Button) findViewById(R.id.play);

        if(!hasMic()){
            stopButton.setEnabled(false);
            recordButton.setEnabled(false);
            playButton.setEnabled(false);
        }
        else
        {
            playButton.setEnabled(false);
            stopButton.setEnabled(false);
        }
        audioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/lupe.3gp";

        //Pedimos el permiso
        requestPermission(Manifest.permission.RECORD_AUDIO, RECORD_REQUEST_CODE);

    }//onCreate

    protected boolean hasMic(){
        PackageManager pmanager = this.getPackageManager();
        return pmanager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    }

    public void RecordMethod(View view) throws IOException {
        isRecording = true;
        stopButton.setEnabled(true);
       ; playButton.setEnabled(false);
        recordButton.setEnabled(false);

        try{
            mediarecorder = new MediaRecorder();
            mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediarecorder.setOutputFile(audioFilePath);
            mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediarecorder.prepare();

        }catch(Exception e){
            e.printStackTrace();
        }
        mediarecorder.start();
    }

    public void StopMethod(View view){
        stopButton.setEnabled(false);
        playButton.setEnabled(true);
        if(isRecording){
            recordButton.setEnabled(false);
            mediarecorder.stop();
            mediarecorder.release();
            mediarecorder = null;
            isRecording = false;
        }
        else{
            mediaplayer.stop();
            mediaplayer.release();
            mediaplayer = null;
            recordButton.setEnabled(true);
        }
    }

    public void PlayMethod(View view) throws IOException{
        playButton.setEnabled(false);
        recordButton.setEnabled(false);
        stopButton.setEnabled(true);

        mediaplayer = new MediaPlayer();
        mediaplayer.setDataSource(audioFilePath);
        mediaplayer.prepare();
        mediaplayer.start();
    }
    protected void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(this, permissionType);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permissionType}, requestCode);

        }
    } //requestPermissions

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    recordButton.setEnabled(false);
                    Toast.makeText(this,"Permiso de almacenamiento requerido",Toast.LENGTH_LONG).show();
                }
                else{
                    requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,STORAGE_REQUEST_CODE);
                }
                break;

            }
            case RECORD_REQUEST_CODE:{
                if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    recordButton.setEnabled(false);
                    Toast.makeText(this,"Permiso de grabacion requerido", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }


}//class
