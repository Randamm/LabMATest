package com.labma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketHandler implements Runnable {

    private ScreenPanel panel;
    private final int port = 29288;

    public SocketHandler(ScreenPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        try (Socket clientSocket = new Socket("localhost", port);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String msg;
            Message message = new Message();
            Bezier bezier = new Bezier();
            while (clientSocket.isConnected() && (msg = in.readLine()) != null) {
                if (message.parseMessage(msg, panel.getParent().getWidth(), panel.getParent().getHeight())) {
                    System.out.println(message);
                    switch (message.getAction()) {
                        case start:
                            panel.getLines().addAll(panel.getTmpLines());
                            panel.getTmpLines().clear();
                            bezier.getPoints().clear();
                            panel.repaint();

                            bezier.setCurrentPos(message.getPoint());
                            bezier.getPoints().add(message.getPoint());
                            break;
                        case move:
                            bezier.getPoints().add(message.getPoint());
                            panel.getTmpLines().clear();
                            panel.getTmpLines().addAll(bezier.getBezierLines(message.getColor()));
                            panel.repaint();
                    }
                } else System.out.println("skip incorrect message");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
