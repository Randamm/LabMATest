package com.labma;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LabMAServerApp {
    public static void main(String[] args) {
        try (
                ServerSocket server = new ServerSocket(29288);
                Socket clientSocket = server.accept();
                FileInputStream fstream = new FileInputStream("commands.txt");
                BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            String strLine;
            while ((strLine = in.readLine()) != null) {
                System.out.println(strLine);
                out.write(strLine + "\n");
                out.flush();
                sleep(100);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }
}



