package com.labma;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {

    enum Action {
        start,
        move
    }

    private static final Pattern p = Pattern.compile("^([a-fA-F0-9]{2}[:]){5}[a-fA-F0-9]{2}$");
    private String mac;
    private Action action;
    private Point2D.Double point;
    private Color color;

    public boolean parseMessage(String msg, int width, int height) {
        if (msg == null) return false;
        if (msg.replaceAll("[^;]", "").length() == 4) {
            String[] buf = msg.split(";", 5);
            try {
                mac = buf[0];
                Matcher m = p.matcher(mac);
                if (!m.find()) {
                    System.out.println("Incorrect MAC address");
                    return false;
                }
                action = Action.valueOf(buf[1]);
                point = new Point2D.Double(Double.parseDouble(buf[2]) * width, Double.parseDouble(buf[3]) * height);
                color = new Color(Integer.parseInt(buf[4]));
                return true;
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        } else return false;
    }

    public Point2D.Double getPoint() {
        return point;
    }

    public Action getAction() {
        return action;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mac='" + mac + '\'' +
                ", action=" + action +
                ", point=" + point +
                ", color=" + color +
                '}';
    }
}
