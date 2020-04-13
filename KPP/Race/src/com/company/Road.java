package com.company;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.management.AttributeList;
import javax.swing.*;

public class Road extends JPanel implements ActionListener, Runnable {

    Timer mainTimer = new Timer(20, this);

    Image img = new ImageIcon("D:\\kazlik\\proga\\KPP\\Race\\src\\road.jpg").getImage();
    Player p = new Player();

    Thread enemiesFactory = new Thread(this);

    List<Enemy> enemies = new ArrayList<Enemy>();

    public Road(){
        mainTimer.start();
        enemiesFactory.start();
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

        double v = (250/Player.MAX_SPEED) * p.v;
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Скорость: " + v + "км/ч", 100, 30);

        Iterator<Enemy> i = enemies.iterator();
        while(i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            }
            else {
                e.move();
                g.drawImage(e.img, e.x, e.y, null);

            }
        }
    }


    @Override
    public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
        p.move();
        repaint();
        testCollisionWithEnemies();
    }

    private void testCollisionWithEnemies() {
        Iterator<Enemy> i = enemies.iterator();
        while(i.hasNext()) {
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())){

                new Error();
                dispose();
            }
        }
    }
    


    @Override
    public void run() {

        while(true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(3000));
                enemies.add(new Enemy(1200, rand.nextInt(300), rand.nextInt(60), this));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
