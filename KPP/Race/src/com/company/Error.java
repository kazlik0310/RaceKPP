package com.company;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Error {
    private JFrame frame;
    private JLabel lose;
    private JButton start;
    private JButton exit;

    public Error(){
        constructFrame();
        addComponentsToFrame();
        finalSettingOfFrame();
    }

    private void finalSettingOfFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocation(600,100);
        frame.setUndecorated(false);
        frame.setVisible(true);
    }

    private void addComponentsToFrame() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.getContentPane().add(panel);
        lose.setBounds(250,100,200,50);
        start.setBounds(190,150,200,50);
        exit.setBounds(190,300,200,50);
        panel.add(lose);
        panel.add(start);
        panel.add(exit);
        frame.getContentPane().add(panel);
    };

    private void constructFrame(){
        frame = new JFrame("Проигрыш");
        lose = new JLabel("Вы проиграли!");
        start = new JButton("Начало игры");
        exit = new JButton("выход");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
                frame.dispose();
                new GameWindow();
            }

        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
                frame.dispose();
            }

        });
    };



}
