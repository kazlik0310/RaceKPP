package com.company;

import javax.swing.*;

public class GameWindow {

    GameWindow() {
        JFrame f = new JFrame("Race");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1100,600);
        f.add(new Road());
        f.setVisible(true);
    }
}
