package com.denysenko.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RectangularColumns {

    private int x;
    private int y;
    private int width;
    private int height;
    boolean done = false;

    private static boolean blink = true;

    public RectangularColumns(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setDone() {
        this.done = true;
    }
    public void draw(Graphics g) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        Graphics2D g2d = (Graphics2D) g;
        if (done) {
            g2d.setColor(Color.GREEN);
            g2d.fill(rectangle);
            return;
        }
        if (blink) {
            g2d.setColor(Color.YELLOW);
            blink = false;
        } else {
            g2d.setColor(Color.GRAY);
            blink = true;
        }
        g2d.fill(rectangle);
    }

}
