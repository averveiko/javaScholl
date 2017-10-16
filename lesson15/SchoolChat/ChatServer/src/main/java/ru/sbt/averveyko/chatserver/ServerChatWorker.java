package ru.sbt.averveyko.chatserver;

import ru.sbt.averveyko.api.ChatCommand;
import ru.sbt.averveyko.api.ChatPacket;

import java.io.*;
import java.net.Socket;

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
                login = chatPacket.getSender();
                System.out.println("User " + login + " connected");

                //Уведомляем о новом участнике чата всех пользователей
                for (String user : ChatServer.users) {
                    ChatPacket notifyPacket = new ChatPacket(
                            ChatCommand.MSG,
                            "Server",
                            user,
                            "User " + login + " connected to chat");
                    ChatServer.messageList.addMessage(notifyPacket);
                }
                ChatServer.users.add(login);
            } catch (ClassNotFoundException e) {
                System.out.println("Login packet must be first");
                return;
            }

            //Цикл обработки пакетов
            while (true) {
                try {
                    ChatPacket chatPacket = (ChatPacket) ois.readObject();
                    switch (chatPacket.getCmd()) {
                        case MSG:
                            System.out.println("receive msg " + chatPacket.getMsg() + " to " + chatPacket.getReceiver());
                            ChatServer.messageList.addMessage(chatPacket);
                            break;
                        case RECEIVE_MSG:
                            oos.writeObject(ChatServer.messageList.getMessages(login));
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
            e.printStackTrace();
            return;
        }
    }
}
