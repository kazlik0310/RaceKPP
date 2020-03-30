package com.company;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener {

    Timer mainTimer = new Timer(20, this);

    Image img = new ImageIcon("D:\\kazlik\\proga\\KPP\\Race\\src\\road.jpg").getImage();

    Player p = new Player();

    public Road(){
        mainTimer.start();
        addKeyListener(new myKeyAdapter());
        setFocusable(true);
    }

    private class myKeyAdapter extends KeyAdapter {
        public void keyPressed (KeyEvent e){
            p.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            p.keyReleased(e);
        }
    }

    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img, p.layer2, 0, null);
        g.drawImage(p.img, p.x, p.y, null);
    }


    @Override
    public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
        p.move();
        repaint();
    }
}
