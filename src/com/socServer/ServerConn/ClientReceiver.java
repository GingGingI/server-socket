package com.socServer.ServerConn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver {
    private Socket clientSoc;
    private DataInputStream is;
    private ServerConn sConn;
    private DataOutputStream os;

    ClientReceiver(Socket clientSoc, ServerConn sConn){
        this.clientSoc = clientSoc;
        this.sConn = sConn;

        try{
            os = new DataOutputStream(clientSoc.getOutputStream());
            is = new DataInputStream(clientSoc.getInputStream());

            os.writeUTF("Server Connected!");
            System.out.println("Data Send");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        boolean done = false;
        while (!done) {
            try {
                if (clientSoc.isConnected()) {
                    byte msgType = is.readByte();
                    String txt = is.readUTF();
                    switch (msgType) {
                        case 1:
                            System.out.println(clientSoc.getLocalAddress() + " : " + txt);
                            System.out.println("Server to " + clientSoc.getLocalAddress() + " -> ");
                            os.writeUTF("Server to " + clientSoc.getLocalAddress() + " -> " + txt);
                            break;
                        case 2:
                            System.out.print(txt);
                            System.out.println(" <- Pong!");
                            os.writeUTF(txt + " <- Pong!");
                            break;
                        default:
                            done = true;
                    }
                }
            } catch (Exception e) {
                done = true;
                e.printStackTrace();
            }
        }
        try{
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sConn.UserDisconn(clientSoc.getInetAddress().toString());
    }
}
