package com.denysenko.bublesort;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Sort implements ActionListener {

    Frame frame;
    Panel panel;
    GridLayout grid;
    Button startButton;
    TextField startText;

    Shape[] shapes;

    Sort() {
        frame = new Frame("com.denysenko.bublesort.Sort");
        frame.setLayout(new FlowLayout());
        frame.setSize(1200, 600);
        frame.setVisible(true);
        frame.setBackground(Color.LIGHT_GRAY);

        startButton = new Button("Start");
        startButton.addActionListener(this);

        panel = new Panel();
        panel.add(startButton);

//        startText = new TextField(10);
//        frame.add(startText);
        grid = new GridLayout(1,1);
        panel.setLayout(grid);

        frame.add(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == startButton) {
            printStart();
        } else {
            printException();
        }
    }

    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = 10;
        rectangle.y = -20;
        rectangle.width = 10;
        rectangle.height = 100;
        return rectangle;
    }

    public void fillShapes() {
        shapes = new Shape[5];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = getRectangle();
        }
    }

    public BufferedImage render() {
        Rectangle2D bounds = new Rectangle(20, -40, 20, 200);
        Rectangle r = bounds.getBounds();
        BufferedImage bi = new BufferedImage(r.width, r.height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = bi.createGraphics();
        g.translate(-r.x, -r.y);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);

        for (int a = 0; a < shapes.length; a++) {
            float hue = (a) / 11f;
            float bri = .75f + .25f * a / ((shapes.length));
            Shape shape = shapes[a];
            g.setColor(new Color(Color.HSBtoRGB(hue, .75f, bri)));
            g.fill(shape);
        }
        g.dispose();
        return bi;
    }

    public void draw(BufferedImage bi) {

//        frame.add(bi);
    }

    public void printStart() {
        fillShapes();
        draw(render());
    }

    public void printException() {
        System.out.println("Exception happened");
    }

}
