package axelindustry.sumito4;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Set;

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
        for (BluetoothDevice blueDevice : devices) {
            //Toast.makeText(BluetoothActivity.this, "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
            //str[cpt]=blueDevice.getName();
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

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * Created by Bruno Quercia on 15/04/2015.
     */
    public static class drawBall {
        private Bitmap image;
        private int x, y;

        public void drawBall(Bitmap image, int x, int y){
            this.image = image;
            this.x = x;
            this.y = y;
        }

        public void move(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Bitmap getBitmap()
        {
            return image;
        }
    }
}