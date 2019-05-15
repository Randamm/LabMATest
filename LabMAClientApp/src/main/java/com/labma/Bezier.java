package com.labma;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Bezier {
    private final static double T_STEP = 0.0001;
    private ArrayList<Point2D.Double> points = new ArrayList<>();
    private Point2D.Double currentPos = new Point2D.Double(0, 0);

    public ArrayList<Point2D.Double> getPoints() {
        return points;
    }

    public void setCurrentPos(Point2D.Double currentPos) {
        this.currentPos = currentPos;
    }

    public Point2D.Double getBezierPoint(ArrayList<Point2D.Double> bezierPoints, double t) {
        if(bezierPoints == null || bezierPoints.isEmpty()|| t<0 || t>1) return null;
        if (bezierPoints.size() == 1) {
            return bezierPoints.get(0);
        } else {
            ArrayList<Point2D.Double> nextPoints = new ArrayList<>();
            for (int i = 1; i < bezierPoints.size(); i++) {
                nextPoints.add(new Point2D.Double(
                        (bezierPoints.get(i).getX() - bezierPoints.get(i - 1).getX()) * t + bezierPoints.get(i - 1).getX(),
                        (bezierPoints.get(i).getY() - bezierPoints.get(i - 1).getY()) * t + bezierPoints.get(i - 1).getY()
                ));
            }
            bezierPoints = nextPoints;
            return getBezierPoint(bezierPoints, t);
        }
    }

    public ArrayList<CustomLine> getBezierLines(Color color) {
        ArrayList<CustomLine> lines = new ArrayList<>();
        Point2D.Double nextPoint;
        if (this.points.size() > 2) {
            this.currentPos = points.get(0);
            for (double t = 0; t < 1; t += T_STEP) {
                nextPoint = this.getBezierPoint(this.points, t);
                lines.add(new CustomLine(currentPos, nextPoint, color));
                currentPos = nextPoint;
            }
        } else {
            lines.add(new CustomLine(points.get(0), points.get(1), color));
        }
        return lines;
    }
}
