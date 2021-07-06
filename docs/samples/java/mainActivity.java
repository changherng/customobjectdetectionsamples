//The name of this project
package com.example.bicta;
//Importing libraries for the below functions(buttons, imageview,Uri for browser etc...)
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//Main activity(Main menu) with the following buttons declared private so future functions cannot access them, main activity made public so other functions can call
public class MainActivity extends AppCompatActivity {
    private Button Camera;
    private Button Temperature;
    private Button Movement;
    private Button Activity;
    private Button Settings;
    private int STORAGE_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     //set the view of the main menu layout
        setContentView(R.layout.activity_main);
        //Get the id for the settings button to call
        Settings = (Button) findViewById(R.id.Settings);
        //Function for detecting user tapping on the settings buttton
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensettingspage();
            }
        });
        Activity = (Button) findViewById(R.id.Activity);
        Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivitypage();
            }
        });
        Movement = (Button) findViewById(R.id.Movement);
        Movement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmovementpage();
            }
        });
        Temperature = (Button) findViewById(R.id.Temperature);
        Temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentemperaturepage();
                }
        });
        Camera = (Button) findViewById(R.id.Camera);
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"You have already granted this permission", Toast.LENGTH_SHORT).show();
                }else{
                    requestStoragePermission();
                }
                opencamerapage();
            }
        });
    }
    public void opencamerapage(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.100.10:8000/"));
        startActivity(browserIntent);
    }
    public void opentemperaturepage(){
        Intent intent = new Intent(this, temperaturepage.class);
        startActivity(intent);
    }
    public void openmovementpage(){
        Intent intent = new Intent(this, movementpage.class);
        startActivity(intent);
    }
    public void openactivitypage(){
        Intent intent = new Intent(this, activitypage.class);
        startActivity(intent);
    }
    public void opensettingspage(){
        Intent intent = new Intent(this, settingspage.class);
        startActivity(intent);
    }

    private void requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("This permission is needed to access the camera")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();

        }else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
