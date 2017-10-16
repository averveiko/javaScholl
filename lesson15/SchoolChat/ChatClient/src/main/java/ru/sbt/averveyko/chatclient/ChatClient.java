package ru.sbt.averveyko.chatclient;

import ru.sbt.averveyko.api.ChatCommand;
import ru.sbt.averveyko.api.ChatPacket;
import ru.sbt.averveyko.api.Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class ChatClient {
    private static final String USAGE = "Type 'q' for quit\n" +
            "'send <receiver> <msg>' to send message\n" +
            "'receive' to receive messages";

    public static void main(String[] args) {
        System.out.print("Trying connection to " + Connection.HOST + ":" + Connection.PORT);

        try (Socket server = new Socket(Connection.HOST, Connection.PORT)) {
            System.out.println(" Connected");

            try (ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
                 ObjectInputStream ois = new ObjectInputStream(server.getInputStream())) {

                Scanner userInput = new Scanner(System.in);
                String userCmd = "";

                // Логинимся
                System.out.print("Login: ");
                String login = userInput.nextLine();
                System.out.println(login);
                ChatPacket loginPacket = new ChatPacket(ChatCommand.LOGIN, login, "Server", login);
                oos.writeObject(loginPacket);

                System.out.println(USAGE);
                while (true) {
                    if (userInput.hasNextLine()) {
                        userCmd = userInput.nextLine();

                        if (userCmd.startsWith("send")) {

                            String receiver = null;
                            String msg = null;
                            try {
                                String[] sendArr = userCmd.split(" ");
                                receiver = sendArr[1];
                                msg = userCmd.substring(receiver.length() + 6);
                            } catch (Exception e) {
                                System.out.println(USAGE);
                                continue;
                            }
                            System.out.println("sending " + msg + " to " + receiver);

                            ChatPacket chatPacket = new ChatPacket(ChatCommand.MSG, login, receiver, msg);
                            oos.writeObject(chatPacket);
                            oos.flush();

                        } else if (userCmd.startsWith("q")) {

                            ChatPacket chatPacket = new ChatPacket(ChatCommand.EXIT, login, "Server", null);
                            oos.writeObject(chatPacket);
                            oos.flush();
                            break;

                        } else if (userCmd.startsWith("receive")) {

                            ChatPacket chatPacket = new ChatPacket(ChatCommand.RECEIVE_MSG, login, "Server", null);
                            oos.writeObject(chatPacket);
                            oos.flush();

                            // Ожидаем сразу ответа
                            List<ChatPacket> packets = (List<ChatPacket>) ois.readObject();
                            System.out.println("Messages for you: ");
                            for (ChatPacket packet : packets) {
                                System.out.println(packet.getSender() + ": " + packet.getMsg());
                            }

                        } else {

                            System.out.println("Unknown command");
                            System.out.println(USAGE);
                        }
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Disconnected");
        }
    }
}
