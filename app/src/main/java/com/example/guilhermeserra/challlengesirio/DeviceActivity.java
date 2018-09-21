package com.example.guilhermeserra.challlengesirio;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeviceActivity extends AppCompatActivity {

    Button buttonOn;
    Button scanButton;
    ListView scanList;
    ArrayList<String> stringArrayList =  new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    BluetoothAdapter myBluetoothAdapter;

    Intent btEnablingIntent;
    int requestCodeForeEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        buttonOn = (Button) findViewById(R.id.ligarBluetooth);
        scanButton = (Button) findViewById(R.id.listarBluetooth);
        scanList = (ListView) findViewById(R.id.lista);

        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btEnablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCodeForeEnable = 1;

        bluetoothOnMethod();

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBluetoothAdapter.startDiscovery();
            }
        });

        IntentFilter intentFilter =  new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(myReceiver, intentFilter);

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stringArrayList);
        scanList.setAdapter(arrayAdapter);
    }

    BroadcastReceiver myReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                stringArrayList.add(device.getName());
                arrayAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getApplicationContext(),"Nenhum Dispositivo Encontrado",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == requestCodeForeEnable){
            if(resultCode == RESULT_OK){
                buttonOn.setText("Bluetooth Ativo");
                buttonOn.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Bluetooth is enable",Toast.LENGTH_LONG).show();
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"Bluetooth Enabling Cancelled",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void bluetoothOnMethod() {
        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBluetoothAdapter == null){
                    Toast.makeText(getApplicationContext(),"Bluetooth does not support on this Device", Toast.LENGTH_LONG).show();
                }else{
                    if(!myBluetoothAdapter.isEnabled()){
                        startActivityForResult(btEnablingIntent,requestCodeForeEnable);
                    }
                }
            }
        });
    }
}
