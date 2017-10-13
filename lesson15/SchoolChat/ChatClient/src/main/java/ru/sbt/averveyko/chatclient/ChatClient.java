package ru.sbt.averveyko.chatclient;

import ru.sbt.averveyko.api.ChatCommand;
import ru.sbt.averveyko.api.ChatPacket;
import ru.sbt.averveyko.api.Connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    private static final String USAGE = "Type 'q' for quit\n" +
            "Type 'send <msg>' to send message";

    public static void main(String[] args) {
        ObjectOutputStream oos = null;

        System.out.println("Client started");
        System.out.println("Trying connection to " + Connection.HOST + ":" + Connection.PORT);

        try (Socket server = new Socket(Connection.HOST, Connection.PORT)) {
            System.out.println("Connected");

            oos = new ObjectOutputStream(server.getOutputStream());

            Scanner userInput = new Scanner(System.in);
            String userCmd = "";

            System.out.println(USAGE);
            while (true) {
                if (userInput.hasNextLine()) {
                    userCmd = userInput.nextLine();
                    if (userCmd.startsWith("send")) {
                        String msg = userCmd.substring(5);
                        System.out.println("sending " + msg);

                        ChatPacket chatPacket = new ChatPacket(ChatCommand.MSG, "receiver", msg);
                        oos.writeObject(chatPacket);
                        oos.flush();

                    } else if (userCmd.startsWith("q")) {

                        ChatPacket chatPacket = new ChatPacket(ChatCommand.EXIT, "receiver",  null);
                        oos.writeObject(chatPacket);
                        oos.flush();

                        break;
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
        } finally {
            System.out.println("Disconnected");
        }
    }
}
