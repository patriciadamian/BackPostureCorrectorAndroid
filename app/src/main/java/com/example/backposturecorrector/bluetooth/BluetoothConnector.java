package com.example.backposturecorrector.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothConnector {

    private static BluetoothSocket bluetoothSocket;

    public static void sendMessage(String message) {
        if (bluetoothSocket == null || !bluetoothSocket.isConnected()) {
            BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
            System.out.println(btAdapter.getBondedDevices());

            BluetoothDevice hc05 = btAdapter.getRemoteDevice("98:D3:C1:FD:52:0C");
            System.out.println(hc05.getName());

            int counter = 0;
            do {
                try {
                    bluetoothSocket = hc05.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                    System.out.println(bluetoothSocket);
                    bluetoothSocket.connect();
                    System.out.println(bluetoothSocket.isConnected());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                counter++;
            } while (!bluetoothSocket.isConnected() && counter < 3);
        }

        try {
            OutputStream outputStream = bluetoothSocket.getOutputStream();
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String receiveMessage() {
        InputStream inputStream;
        String message = "";
        try {
            inputStream = bluetoothSocket.getInputStream();
            while (inputStream.available() != 0) {
                byte b = (byte) inputStream.read();
                message += (char) b;
            }
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }
}
