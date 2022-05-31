package com.denysenko.bublesort;

import static java.lang.Thread.sleep;

import com.denysenko.Sortable;
import com.denysenko.shapes.RectangularColumns;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class BubbleSort extends JPanel implements Sortable {

    int columnWidth = 15;
    int columnBaseHeight = 100;
    int columnSpacer = 5;
    int multiplier = 50;

    List<RectangularColumns> shapes = new ArrayList<>();
    List<RectangularColumns> shuffled;

    public BubbleSort(int size, boolean shuffled) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(600, 600));
        shapes = fill(size, shuffled);
    }

    private List<RectangularColumns> fill(int size, boolean shuffled) {
        List<RectangularColumns> array = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int x;
            if (i == 0) {
                x = 0;
            } else {
                x = i * (columnWidth + columnSpacer);
            }
            array.add(new RectangularColumns(x ,0, columnWidth, columnBaseHeight + i * multiplier));
        }
        shapes = array;
        if (shuffled) {
            return shuffle();
        }
        return array;
    }

    public List<RectangularColumns> shuffle() {
        shuffled = new ArrayList<>(shapes.size());
        shuffled.addAll(shapes);

        List<Integer> indexes = new ArrayList<>();
        Random random = new Random();
        while (shapes.size() != indexes.size()) {
            int index = random.nextInt(shapes.size());
            if (!indexes.contains(index)) {
                indexes.add(index);
            }
        }

        for (int index = 0; index < shapes.size(); index++) {
            shuffled.set(indexes.get(index), shapes.get(index));
        }
        return shuffled;
    }

    private List<RectangularColumns> fillShuffled(int size, boolean shuffled) {
        Random random = new Random();
        List<RectangularColumns> array = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int x;
            if (i == 0) {
                x = 0;
            } else {
                x = i * (columnWidth + columnSpacer);
            }
            int index;
            if (shuffled) {
                int min = 1;
                index = random.nextInt(size - min) + min;
                System.out.println(i);
            } else {
                index = i;
            }
            array.set(index, new RectangularColumns(x ,0, columnWidth, columnBaseHeight + i * multiplier));
        }
        return array;
    }

    @Override
    public void sort() throws InterruptedException {
        for (int i = 0; i < shapes.size() - 1; i++) {
            for (int j = 0; j < shapes.size() - i - 1; j++) {
                if (shapes.get(j).getX() < shapes.get(j + 1).getX()) {
                    final RectangularColumns grater = shapes.get(j);
                    final RectangularColumns lower = shapes.get(j + 1);

                    int x = grater.getX();
                    grater.setX(lower.getX());
                    lower.setX(x);

                }
                repaint();
                sleep(500);
            }
        }
    }

    public void showShuffled() {
        repaint();
        try {
            sleep(1_500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (RectangularColumns shape : shapes) {
            shape.draw(g);
        }
    }

}
