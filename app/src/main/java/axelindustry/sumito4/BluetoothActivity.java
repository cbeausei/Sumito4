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

        LinearLayout ll = (LinearLayout) findViewById(R.id.layoutbluetooth);
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);


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
        Button [] buttons= new Button[devices.size()];

        Button serveur=(Button) findViewById(R.id.serveur);
        Button client=(Button) findViewById(R.id.client);

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

        int cpt = 0;

        for ( final BluetoothDevice blueDevice : devices) {
            //Toast.makeText(BluetoothActivity.this, "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
            buttons[cpt] = new Button(this);
            buttons[cpt].setText(blueDevice.getName());
            buttons[cpt].setBackgroundResource(R.drawable.bouton);
            ll.addView(buttons[cpt], lp);
            buttons[cpt].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                           connect(blueDevice);
                }
            });
            cpt++;

        }


        //DrawViewBluetooth drawView = new DrawViewBluetooth(this);
        //drawView.setBackgroundColor(Color.WHITE);
        //setContentView(drawView);


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