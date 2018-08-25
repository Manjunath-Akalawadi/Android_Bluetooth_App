package com.demo.manju.bluetooth_app;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bON , bOFF , dView;
    BluetoothAdapter bluetoothAdapter;
    Intent intentEnable;
    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bON = (Button) findViewById(R.id.bOn);
        bOFF = (Button) findViewById(R.id.bOff);
        dView = (Button) findViewById(R.id.viewD);


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        intentEnable = new Intent(bluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCode = 1;
        
        bluetoothOn();
        bluetoothOff();
        viewDevices();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==requestCode){

            if (resultCode==RESULT_OK){

                Toast.makeText(getApplicationContext(),"Bluetooth is Enable",Toast.LENGTH_LONG).show();

            }else if (resultCode ==RESULT_CANCELED){

                Toast.makeText(getApplicationContext(),"Bluetooth is Enabling Canceled ",Toast.LENGTH_LONG).show();

            }
        }
    }

    public void bluetoothOn(){

        bON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bluetoothAdapter == null){

                    Toast.makeText(getApplicationContext(),"This device not support Bluetooth",Toast.LENGTH_LONG).show();

                }else if(!bluetoothAdapter.isEnabled()){

                    startActivityForResult(intentEnable,requestCode);

                }
            }
        });
    }

    public  void bluetoothOff(){

        bOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bluetoothAdapter.isEnabled()){

                    bluetoothAdapter.disable();
                    Toast.makeText(getApplicationContext(),"Bluetooth is Disabled",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void viewDevices(){

        dView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,DeviceListActivity.class);
                startActivity(intent);
            }
        });

    }
}
