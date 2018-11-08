package com.socServer.ServerConn;

import com.socServer.Ref.ServerRef;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConn {
    private ServerSocket Ssoc = null;

    public void ServerConn() {
        try {
            Ssoc = new ServerSocket(ServerRef.port);

            System.out.println("server Ready.");
            waitConn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitConn() {
        while (true) {
            try {
                System.out.println("waiting for connect...");

                Socket soc = Ssoc.accept();
                System.out.println(soc.getInetAddress() + " Connect");

                ClientReceiver anClient = new ClientReceiver(soc, this);
                anClient.run();
//                System.out.println(soc.getInputStream().read());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void UserDisconn(String local) {
        System.out.println("user -> "+local+" Disconnected...");
    }
    public void ServerClose() {System.out.println("serverClosed...");}
}
