package com.example.backposturecorrector;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    static final UUID mUUi9ID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        System.out.println(btAdapter.getBondedDevices());

        BluetoothDevice hc05 = btAdapter.getRemoteDevice("98:D3:C1:FD:52:0C");
        System.out.println(hc05.getName());

        BluetoothSocket btSocket = null;
        int counter = 0;
        do {
            try {
                //btSocket = hc05.createRfcommSocketToServiceRecord(mUUID);
                //btSocket = hc05.createInsecureRfcommSocketToServiceRecord(mUUID);
                btSocket = hc05.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                System.out.println(btSocket);
                btSocket.connect();
                System.out.println(btSocket.isConnected());
            } catch (IOException e) {
                e.printStackTrace();
            }
            counter++;
        } while (!btSocket.isConnected() && counter < 3);


        try {
            OutputStream outputStream = btSocket.getOutputStream();
            outputStream.write("LOOP".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = null;
        try {
            inputStream = btSocket.getInputStream();
            inputStream.skip(inputStream.available());

            String message = "";
            //for (int i = 0; i < 200; i++) {
            while (inputStream.available() > 0) {

                //while (true) {
                //if (inputStream.available() > 0) {
                byte b = (byte) inputStream.read();
                message += (char) b;
                //System.out.println("A AJUNS AICI!!");
                //System.out.println((char) b);

                // }
                Log.i("info", message);
            }
//                        byte b = (byte) inputStream.read();
//                        System.out.println("A AJUNS AICI!!");
//                        System.out.println("Byte: " + b + " char: " + (char) b);
//                    } else {
//                        SystemClock.sleep(100);
//                    }
//                }


        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
////            btSocket.close();
//            System.out.println(btSocket.isConnected());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}

