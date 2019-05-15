package com.labma;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BezierTest {
    private Bezier bezier;
    private double t = 0.050;
    private double epsilon = 0.1;
    private Point2D.Double p1= new Point2D.Double(1,2);
    private Point2D.Double p2= new Point2D.Double(5,6);
    private Point2D.Double p3= new Point2D.Double(3,3);
    private Point2D.Double p4= new Point2D.Double(9,4);

    @Before
    public void initTest() {
        bezier = new Bezier();
    }

    @After
    public void afterTest() {
        bezier = null;
    }

    @Test
    public void getNullBezierPoint() throws Exception {
        assertNull(bezier.getBezierPoint(null,t));
        assertNull(bezier.getBezierPoint(new ArrayList<Point2D.Double>(),t));
        assertNull(bezier.getBezierPoint(new ArrayList<Point2D.Double>(),-5));
    }

    @Test
    public void getBezierPoint() throws Exception {
        bezier.getPoints().add(p1);
        bezier.getPoints().add(p2);
        bezier.getPoints().add(p3);
        bezier.getPoints().add(p4);

        Point2D.Double expected = bezier.getBezierPoint(bezier.getPoints(),t);

        Point2D.Double actual = new Point2D.Double(
                Math.pow(1-t,3) * bezier.getPoints().get(0).getX() +
                        3*Math.pow(1-t,2) * t * bezier.getPoints().get(1).getX() +
                        3*(1-t) * Math.pow(t,2) * bezier.getPoints().get(2).getX() +
                        Math.pow(t,3) * bezier.getPoints().get(3).getX(),
                Math.pow(1-t,3) * bezier.getPoints().get(0).getY() +
                        3*Math.pow(1-t,2) * t * bezier.getPoints().get(1).getY() +
                        3*(1-t) * Math.pow(t,2) * bezier.getPoints().get(2).getY() +
                        Math.pow(t,3) * bezier.getPoints().get(3).getY()
        );
        assertTrue(
                Math.abs(expected.getX()-actual.getX())<epsilon &&
                        Math.abs(expected.getY()-actual.getY())<epsilon
        );
    }

}