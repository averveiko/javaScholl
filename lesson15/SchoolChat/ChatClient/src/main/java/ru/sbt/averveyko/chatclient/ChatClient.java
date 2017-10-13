package ru.sbt.averveyko.chatclient;

import ru.sbt.averveyko.api.Connection;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        System.out.println("Client started");
        System.out.println("Trying connection to " + Connection.HOST + ":" + Connection.PORT);

        try(Socket server = new Socket(Connection.HOST, Connection.PORT)) {
            System.out.println("Connected");

            Scanner userInput = new Scanner(System.in);
            String userCmd = "";
            System.out.println("Type 'q' for quit");
            System.out.println("Type 'send <msg>' to send message");
            while (! userCmd.equalsIgnoreCase("q")) {
                if (userInput.hasNext()) {
                    userCmd = userInput.next();
                    if (userCmd.equalsIgnoreCase("send")) {
                        String msg = userInput.next();
                        System.out.println("sending " + msg);
                    } else {
                        String cmd = userInput.nextLine();
                        System.out.println("Unknown command " + cmd);
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
