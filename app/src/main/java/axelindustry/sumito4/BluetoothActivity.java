package axelindustry.sumito4;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

import axelindustry.sumito4.IA.BallMove;

public class BluetoothActivity extends Activity {

    BluetoothAdapter bluetoothAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.devicesbluetoothlayout);

        Button B1=(Button) findViewById(R.id.connection1);
        Button B2=(Button) findViewById(R.id.connection2);
        Button B3=(Button) findViewById(R.id.connection3);
        Button B4=(Button) findViewById(R.id.connection4);
        Button B5=(Button) findViewById(R.id.connection5);
        Button B6=(Button) findViewById(R.id.connection6);
        Button serveur=(Button) findViewById(R.id.serveur);
        Button client=(Button) findViewById(R.id.client);

        //DrawViewBluetooth drawView = new DrawViewBluetooth(this);
        //drawView.setBackgroundColor(Color.WHITE);
        //setContentView(drawView);
        onActivityResult(0, 0, new Intent());
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(BluetoothActivity.this, "Pas de Bluetooth",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(BluetoothActivity.this, "Avec Bluetooth",
                    Toast.LENGTH_SHORT).show();
        }
        Intent enableBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBlueTooth, 1);
        Set<BluetoothDevice> devices;
        devices = bluetoothAdapter.getBondedDevices();
        String [] str= new String[devices.size()];
        int cpt = 0;
        for (BluetoothDevice blueDevice : devices) {
            //Toast.makeText(BluetoothActivity.this, "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
            str[cpt]=blueDevice.getName();
            cpt ++;
        }
        switch (devices.size())
        {
            case 0:
                B1.setVisibility(View.GONE);
                B2.setVisibility(View.GONE);
                B3.setVisibility(View.GONE);
                B4.setVisibility(View.GONE);
                B5.setVisibility(View.GONE);
                B6.setVisibility(View.GONE);
                break;
            case 1:
                B1.setText(str[0]);
                B2.setVisibility(View.GONE);
                B3.setVisibility(View.GONE);
                B4.setVisibility(View.GONE);
                B5.setVisibility(View.GONE);
                B6.setVisibility(View.GONE);
                break;
            case 2:
                B1.setText(str[0]);
                B2.setText(str[1]);
                B3.setVisibility(View.GONE);
                B4.setVisibility(View.GONE);
                B5.setVisibility(View.GONE);
                B6.setVisibility(View.GONE);
                break;
            case 3:
                B1.setText(str[0]);
                B2.setText(str[1]);
                B3.setText(str[2]);
                B4.setVisibility(View.GONE);
                B5.setVisibility(View.GONE);
                B6.setVisibility(View.GONE);
                break;
            case 4:
                B1.setText(str[0]);
                B2.setText(str[1]);
                B3.setText(str[2]);
                B4.setVisibility(View.GONE);
                B5.setVisibility(View.GONE);
                B6.setVisibility(View.GONE);
                break;
            case 5:
                B1.setText(str[0]);
                B2.setText(str[1]);
                B3.setText(str[2]);
                B4.setText(str[3]);
                B5.setText(str[4]);
                B6.setVisibility(View.GONE);
                break;
            case 6:
                B1.setText(str[0]);
                B2.setText(str[1]);
                B3.setText(str[2]);
                B4.setText(str[3]);
                B5.setText(str[4]);
                B6.setText(str[5]);
                break;
        }
        Button myButton = new Button(this);
        myButton.setText("Push Me");



        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cpt = 0;
                for (BluetoothDevice blueDevice : bluetoothAdapter.getBondedDevices()) {
                    if (cpt == 0) {
                        connect(blueDevice);
                        break;
                    }
                    cpt++;
                }
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cpt = 0;
                for (BluetoothDevice blueDevice : bluetoothAdapter.getBondedDevices()) {
                    if(cpt == 1) {
                        connect(blueDevice);
                        break;
                    }
                    cpt ++;
                }
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cpt = 0;
                for (BluetoothDevice blueDevice : bluetoothAdapter.getBondedDevices()) {
                    if(cpt == 2) {
                        connect(blueDevice);
                        break;
                    }
                    cpt ++;
                }
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cpt = 0;
                for (BluetoothDevice blueDevice : bluetoothAdapter.getBondedDevices()) {
                    if(cpt == 3) {
                        connect(blueDevice);
                        break;
                    }
                    cpt ++;
                }
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cpt = 0;
                for (BluetoothDevice blueDevice : bluetoothAdapter.getBondedDevices()) {
                    if(cpt == 4) {
                        connect(blueDevice);
                        break;
                    }
                    cpt ++;
                }
            }
        });
        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cpt = 0;
                for (BluetoothDevice blueDevice : bluetoothAdapter.getBondedDevices()) {
                    if(cpt == 5) {
                        connect(blueDevice);
                        break;
                    }
                    cpt ++;
                }
            }
        });
        serveur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void movementExecuted(LinkedList<BallMove> ballMove){
        for(BallMove m: ballMove) {
            Toast.makeText(BluetoothActivity.this, "(" + m.fromI + ", " + m.fromJ + ") -> (" + m.toI + ", " + m.toJ + ")", Toast.LENGTH_SHORT).show();
        }
    }

    private void connect(BluetoothDevice device){
        final BluetoothSocket socket;
        BluetoothSocket tmp = null;
        try {
            tmp = device.createRfcommSocketToServiceRecord(new UUID(200, 100));
        } catch (IOException e) { }
        socket = tmp;
        try {
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            if(socket.isConnected())
                Toast.makeText(BluetoothActivity.this,"Yep", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(BluetoothActivity.this,"Nope", Toast.LENGTH_SHORT).show();
        }
    }

}