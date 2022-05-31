package com.denysenko;

import static java.lang.Thread.sleep;

import java.awt.Component;

import javax.swing.JFrame;

public class RunAnimation {

    JFrame frame;
    Sortable component;

    public void createFrameAndAnimate(String title, Sortable component) {
        this.component = component;
        frame = new JFrame(title);
        frame.add((Component) component);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        component.showShuffled();

        try {
            sleep(1_500);
            component.sort();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
