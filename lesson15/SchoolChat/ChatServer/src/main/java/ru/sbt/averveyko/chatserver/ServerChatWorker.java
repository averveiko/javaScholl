package ru.sbt.averveyko.chatserver;

import ru.sbt.averveyko.api.ChatCommand;
import ru.sbt.averveyko.api.ChatPacket;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerChatWorker implements Runnable {
    private Socket socket;
    private String login;

    public ServerChatWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            //Первичная регистрация пользователя по логину
            try {
                ChatPacket chatPacket = (ChatPacket) ois.readObject();
                if (chatPacket.getCmd() != ChatCommand.LOGIN) {
                    System.out.println("Login packet must be first");
                    return;
                }
                login = chatPacket.getMsg();
                System.out.println("User " + login + " connected");

            } catch (ClassNotFoundException e) {
                System.out.println("Login packet must be first");
                return;
            }

            //Цикл обработки пакетов
            while (true){
                try {
                    ChatPacket chatPacket = (ChatPacket) ois.readObject();
                    switch (chatPacket.getCmd()) {
                        case MSG:
                            System.out.println("receive msg " + chatPacket.getMsg() + " to " + chatPacket.getReceiver());
                            ChatServer.messageList.addMessage(chatPacket);
                            break;
                        case RECEIVE_MSG:
                            List<ChatPacket> packets = ChatServer.messageList.getMessages(login);
                            if (packets.isEmpty()) {
                                System.out.println("No message for " + login);
                                ChatPacket packet = new ChatPacket(ChatCommand.MSG,"server","No message for you");
                                packets.add(packet);
                            }
                            oos.writeObject(packets);
                            break;
                        case EXIT:
                            System.out.println("user " + login + " disconnected");
                            return;
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.out.println("user " + login + " disconnected");
                    return;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            //return
            e.printStackTrace();
        }
    }
}
