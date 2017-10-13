package ru.sbt.averveyko.chatserver;

import java.io.*;
import java.net.Socket;

public class ServerChatWorker implements Runnable {
    private Socket socket;

    public ServerChatWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;

        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            //return
            e.printStackTrace();
        }

        String line;
        while (true){
            try {
                //echo
                line  = brinp.readLine();
                out.writeUTF(line);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
