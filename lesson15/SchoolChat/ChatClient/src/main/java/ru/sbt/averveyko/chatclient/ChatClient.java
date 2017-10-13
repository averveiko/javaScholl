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
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        System.out.println("Client started");
        System.out.println("Trying connection to " + Connection.HOST + ":" + Connection.PORT);

        try (Socket server = new Socket(Connection.HOST, Connection.PORT)) {
            System.out.println("Connected");

            //TODO try with resurses
            oos = new ObjectOutputStream(server.getOutputStream());
            ois = new ObjectInputStream(server.getInputStream());

            Scanner userInput = new Scanner(System.in);
            String userCmd = "";

            // Логинимся
            System.out.println("Login: ");
            String login = userInput.nextLine();
            ChatPacket loginPacket = new ChatPacket(ChatCommand.LOGIN, null, login);
            oos.writeObject(loginPacket);

            System.out.println(USAGE);
            while (true) {
                if (userInput.hasNextLine()) {
                    userCmd = userInput.nextLine();
                    if (userCmd.startsWith("send")) {
                        String[] sendArr = userCmd.split(" ");
                        String receiver = sendArr[1];
                        String msg = userCmd.substring(receiver.length() + 6);

                        System.out.println("sending " + msg + " to " + receiver);

                        ChatPacket chatPacket = new ChatPacket(ChatCommand.MSG, receiver, msg);
                        oos.writeObject(chatPacket);
                        oos.flush();

                    } else if (userCmd.startsWith("q")) {

                        ChatPacket chatPacket = new ChatPacket(ChatCommand.EXIT, null,  null);
                        oos.writeObject(chatPacket);
                        oos.flush();

                        break;
                    } else if (userCmd.startsWith("receive")) {

                        ChatPacket chatPacket = new ChatPacket(ChatCommand.RECEIVE_MSG, null,  null);
                        oos.writeObject(chatPacket);
                        oos.flush();

                        // Ожидаем сразу ответа
                        List<ChatPacket> packets = (List<ChatPacket>) ois.readObject();
                        System.out.println("Messages for you: ");
                        for (ChatPacket packet : packets) {
                            System.out.println(packet.getMsg());
                        }
                    } else {
                        System.out.println("Unknown command");
                        System.out.println(USAGE);
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
