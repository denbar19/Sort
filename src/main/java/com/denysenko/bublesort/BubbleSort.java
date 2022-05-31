package com.denysenko.bublesort;

import static java.lang.Thread.sleep;

import com.denysenko.Sortable;
import com.denysenko.shapes.RectangularColumns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class BubbleSort extends JPanel implements Sortable {

    int spacesInPercent = 10;
    int columnBaseWidth = 1;
    int columnBaseHeight = 10;
    int columnSpacer = 1;
    int columnWidth = 1;
    int columnHeightMultiplier = 1;

    List<RectangularColumns> shapes = new ArrayList<>();
    List<RectangularColumns> sortedShapes = new ArrayList<>();
    List<RectangularColumns> shuffled;

    public BubbleSort(int size, boolean shuffled) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(600, 600));

        setProportions(size);
        shapes = fill(size, shuffled);
    }

    private List<RectangularColumns> fill(int size, boolean shuffled) {
        List<RectangularColumns> array = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int x;
            if (i == 0) {
                x = 0;
            } else {
                x = i * (columnBaseWidth + columnSpacer);
            }
            final int height = columnBaseHeight + i * this.columnHeightMultiplier;
            array.add(new RectangularColumns(x , invertForY(height), columnWidth, height));
        }
        shapes = array;
        sortedShapes = array;
        if (shuffled) {
            return shuffle();
        }
        return array;
    }

    private int invertForY(int height) {
        return this.getPreferredSize().height - height;
    }

    private void setProportions(int numberColumns) {
        final int frameWidth = this.getPreferredSize().width;
        final int frameHeight = this.getPreferredSize().height;

        int spacesSumWidth = frameWidth * spacesInPercent / 100;
        int columnsSumWidth = frameWidth - spacesSumWidth;

        columnWidth = Math.max(columnsSumWidth / numberColumns, 1);
        columnSpacer = Math.max(spacesSumWidth / (numberColumns - 1), 1);

        columnHeightMultiplier = frameHeight / numberColumns;
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
            int shuffledX = indexes.get(index) * (columnWidth + columnSpacer);
            shuffled.get(index).setX(shuffledX);
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
            array.set(index, new RectangularColumns(x , 0, columnWidth, columnBaseHeight + i * columnWidth));
        }
        return array;
    }

    @Override
    public void sort() throws InterruptedException {
        for (int i = 0; i < shapes.size() - 1; i++) {
            for (int j = 0; j < shapes.size() - i - 1; j++) {
                if (shapes.get(j).getX() > shapes.get(j + 1).getX()) {
                    final RectangularColumns grater = shapes.get(j);
                    final RectangularColumns lower = shapes.get(j + 1);

                    int x = grater.getX();
                    grater.setX(lower.getX());
                    lower.setX(x);

                }
                repaint();
                sleep(10);
            }
        }
    }

    public void showShuffled() {
        repaint();
        try {
            sleep(3_000);
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
