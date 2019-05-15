package com.labma;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class CustomLine extends Line2D.Double {

    private Color color;

    public CustomLine(Point2D p1, Point2D p2, Color color) {
        super(p1, p2);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
