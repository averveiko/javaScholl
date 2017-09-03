package com.sbt.javaschool.averveyko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter URL (q - quit):");
                String url = in.next();
                if (url.equalsIgnoreCase("q")) break;

                readContent(url);

            } catch (IOException e) {
                System.out.println("Error while reading url: " + e.getMessage());
            }
        }
    }

    static void readContent(String pUrl) throws IOException{
        System.out.println("Trying read " + pUrl);

        URL url = new URL(pUrl);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
