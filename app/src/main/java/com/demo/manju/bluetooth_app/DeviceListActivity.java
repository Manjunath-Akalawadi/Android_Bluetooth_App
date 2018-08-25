package com.demo.manju.bluetooth_app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Set;

public class DeviceListActivity extends AppCompatActivity {

    Button viewDevices , enableD;
    ListView listViewD;

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        viewDevices = (Button) findViewById(R.id.showD);
        listViewD = (ListView) findViewById(R.id.dList);
        enableD = (Button) findViewById(R.id.enableD);

        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

        showDevices();

        enableD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                intent1.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,100);
                startActivity(intent1);

            }
        });
    }

    private void showDevices() {

        viewDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<BluetoothDevice> bDevices = bluetoothAdapter.getBondedDevices();
                String[] str = new String[bDevices.size()];
                        int count=0;

                if (bDevices.size()>0){

                    for (BluetoothDevice bd:bDevices){

                        str[count] = bd.getName();
                        count++;
                    }
                    ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1,str);
                    listViewD.setAdapter(aAdapter);
                }
            }
        });
    }

}
