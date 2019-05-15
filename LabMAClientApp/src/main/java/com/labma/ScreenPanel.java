package com.labma;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ScreenPanel extends JPanel {

    private ArrayList<CustomLine> lines = new ArrayList<>();
    private ArrayList<CustomLine> tmpLines = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ArrayList<CustomLine> snapshot = new ArrayList<>();
        snapshot.addAll(lines);
        snapshot.addAll(tmpLines);
        for (CustomLine line : snapshot) {
            g2d.setColor(line.getColor());
            g2d.draw(line);
        }
    }

    public ArrayList<CustomLine> getLines() {
        return lines;
    }

    public ArrayList<CustomLine> getTmpLines() {
        return tmpLines;
    }
}
