package com.denysenko.bublesort;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class SortSwingUI implements ActionListener{

    JFrame frame;
    JFrame frame2;
    GridLayout grid;
    JPanel panel;
    JPanel panelAnimation;
    JButton startButton;
    List<Shape> shapes;


    public SortSwingUI() {
        fillShapes();
    }

    private void createAndShowGUI() {
        frame = new JFrame("com.denysenko.bublesort.Sort");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1200, 600);
        //frame.setBackground(Color.LIGHT_GRAY);

        //JLabel label = new JLabel("com.denysenko.bublesort.Sort algorithms");
        //frame.getContentPane().add(label);

        startButton = new JButton("Start");
        //frame.add(startButton);

        panel = new JPanel();
        panel.add(startButton);
        grid = new GridLayout(1,2);
        panel.setLayout(grid);

        frame.setVisible(true);
        //frame.add(panel);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        startButton.addActionListener(this);
    }

    public Graphics2D render() {
        Rectangle2D bounds = new Rectangle(0, -100, 200, 2000);
        Rectangle rectangle = bounds.getBounds();
        BufferedImage bi = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = bi.createGraphics();
        graphics.translate(-rectangle.x, -rectangle.y);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < shapes.size(); i++) {
            float hue = (i) / 11f;
            float bri = .75f + .25f * i / ((shapes.size()));
            Shape shape = shapes.get(i);
            graphics.setColor(new Color(Color.HSBtoRGB(hue, .75f, bri)));
            graphics.fill(shape);
        }
        //graphics.dispose();
        return graphics;
    }

    public void renderStar() {
        frame = new JFrame("Render com.denysenko.demo.Star");
        frame.add(new DynamicStar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //panel = new JPanel();
        //frame.add(panel);

      /*  Rectangle rectangle = new Rectangle(0, -100, 200, 2000);
        BufferedImage bi = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = bi.createGraphics();
//        graphics.translate(-rectangle.x, -rectangle.y);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON);

        panel.paintComponents(graphics);
*/
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("action: " + event.getSource());

        if (event.getSource() == startButton) {
            printStart();
        } else {
            printException();
        }
    }

    private void printStart() {
        System.out.println("Start");
        //frame.paint(render());
    }

    private void printException() {
        System.out.println("Exception happened");
    }

    private Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = 10;
        rectangle.y = -20;
        rectangle.width = 10;
        rectangle.height = 100;
        return rectangle;
    }

    private void fillShapes() {
        shapes = new ArrayList<>(5);
        for (int i = 0; i < shapes.size(); i++) {
            shapes.add(getRectangle());
        }
    }

    public void draw() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                createAndShowGUI();
                renderStar();
            }
        });
    }

}
