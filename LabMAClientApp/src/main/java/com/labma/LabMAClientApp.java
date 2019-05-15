package com.labma;

import javax.swing.JFrame;
import java.awt.Color;

public class LabMAClientApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LabMAClientApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setBackground(Color.WHITE);
        frame.setUndecorated(true);

        ScreenPanel panel = new ScreenPanel();
        frame.add(panel);
        frame.setVisible(true);

        Thread t = new Thread(new SocketHandler(panel));
        t.start();
    }
}
