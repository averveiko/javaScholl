package ru.sbt.averveyko.chatserver;

import ru.sbt.averveyko.api.ChatPacket;

import java.io.*;
import java.net.Socket;

public class ServerChatWorker implements Runnable {
    private Socket socket;

    public ServerChatWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
//        InputStream inp = null;
//        BufferedReader brinp = null;
//        DataOutputStream out = null;
        ObjectInputStream ois = null;

        try {
//            inp = socket.getInputStream();
//            brinp = new BufferedReader(new InputStreamReader(inp));
            ois = new ObjectInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            //return
            e.printStackTrace();
        }

        String line;
        while (true){
            try {
                ChatPacket chatPacket = (ChatPacket) ois.readObject();
                switch (chatPacket.getCmd()) {
                    case MSG:
                        System.out.println("receive msg " + chatPacket.getMsg());
                        break;
                    case RECEIVE_MSG:
                        break;
                    case EXIT:
                        System.out.println("client exit");
                        return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
