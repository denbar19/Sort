package com.denysenko;

import com.denysenko.bublesort.BubbleSort;

public class Main {

    static final int SORT_SIZE = 100;

    public static void main(String[] args) {
        RunAnimation animation = new RunAnimation();
        animation.createFrameAndAnimate("Bubble sort animation", new BubbleSort(SORT_SIZE, true));
    }

}
