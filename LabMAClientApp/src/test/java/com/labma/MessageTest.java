package com.labma;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class MessageTest {

    private Message message;
    private int width = 1920;
    private int height = 1080;

    @Before
    public void initTest() {
        message = new Message();
    }

    @After
    public void afterTest() {
        message = null;
    }

    @Test
    public void parseNullMessage() throws Exception {
        boolean expected = message.parseMessage(null,width,height);
        assertFalse(expected);
    }

    @Test
    public void parseIncorrectMessage() throws Exception {
        boolean expected = message.parseMessage(";start;0.15703125;0.28644067;-16777216",width,height);
        assertFalse(expected);

        expected = message.parseMessage("aaaaaaaaa;start;0.15703125;0.28644067;-16777216",width,height);
        assertFalse(expected);

        expected = message.parseMessage("60:21:C0:2A:E0:33;a;0.15703125;0.28644067;-16777216",width,height);
        assertFalse(expected);

        expected = message.parseMessage("60:21:C0:2A:E0:33;start;a;0.28644067;-16777216",width,height);
        assertFalse(expected);

        expected = message.parseMessage("60:21:C0:2A:E0:33;start;0.15703125;a;-16777216",width,height);
        assertFalse(expected);

        expected = message.parseMessage("60:21:C0:2A:E0:33;start;0.15703125;0.28644067;a",width,height);
        assertFalse(expected);
    }

    @Test
    public void parseCorrectMessage() throws Exception {
        boolean expected = message.parseMessage("60:21:C0:2A:E0:33;start;0.15703125;0.28644067;-16777216",width,height);
        assertTrue(expected);

        expected = message.parseMessage("60:21:C0:2A:E0:33;move;0.15703125;0.28644067;-16777216",width,height);
        assertTrue(expected);
    }

    @Test
    public void getPoint() throws Exception {
        message.parseMessage("60:21:C0:2A:E0:33;start;0.15703125;0.28644067;-16777216",width,height);
        assertEquals(message.getPoint(), new Point2D.Double(0.15703125*width,0.28644067*height));
    }

    @Test
    public void getAction() throws Exception {
        message.parseMessage("60:21:C0:2A:E0:33;start;0.15703125;0.28644067;-16777216",width,height);
        assertEquals(message.getAction(), Message.Action.start);
    }

    @Test
    public void getColor() throws Exception {
        message.parseMessage("60:21:C0:2A:E0:33;start;0.15703125;0.28644067;-16777216",width,height);
        assertEquals(message.getColor(), Color.black);
    }

    @Test
    public void getRedColor() throws Exception {
        message.parseMessage("60:21:C0:2A:E0:33;start;0.15703125;0.28644067;" + Color.RED.getRGB(),width,height);
        assertEquals(message.getColor(), Color.RED);
    }

    @Test
    public void getGreenColor() throws Exception {
        message.parseMessage("60:21:C0:2A:E0:33;start;0.15703125;0.28644067;" + Color.GREEN.getRGB(),width,height);
        assertEquals(message.getColor(), Color.GREEN);
    }

    @Test
    public void getBlueColor() throws Exception {
        message.parseMessage("60:21:C0:2A:E0:33;start;0.15703125;0.28644067;" + Color.BLUE.getRGB(),width,height);
        assertEquals(message.getColor(), Color.BLUE);
    }
}