package com.denysenko.bublesort;

import com.denysenko.demo.Star;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class DynamicStar extends JPanel {

    private Random random = new Random();
    List<Object> shapes = new ArrayList<>();

    public DynamicStar() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1200, 600));

        for (int j = 0; j < 3; j++) {
            addStar(380, 380);
        }


    }

    public void addStar(int maxX, int maxY) {
        shapes.add(new Star(random.nextInt(maxX), random.nextInt(maxY)));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object shape : shapes) {
                ((Star) shape).draw(g);
        }
    }

}
