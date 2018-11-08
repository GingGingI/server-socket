package com.socServer;

import com.socServer.ServerConn.ServerConn;

public class Main extends ServerConn {
    static Main m;
    public static void main(String[] args) {
        m = new Main();
        m.ServerStarter();
    }

    private void ServerStarter(){
        ServerConn();
    }
}
