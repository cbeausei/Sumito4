package axelindustry.sumito4;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Set;

public class BluetoothActivity extends Activity {

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawViewBluetooth drawView = new DrawViewBluetooth(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
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
        for (BluetoothDevice blueDevice : devices) {
            Toast.makeText(BluetoothActivity.this, "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
        }
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