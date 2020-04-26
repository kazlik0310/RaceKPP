package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {

    Timer mainTimer = new Timer(20, this);
    private boolean flag = true;
    Image img = new ImageIcon("D:\\kazlik\\proga\\KPP\\Race\\src\\road.jpg").getImage();

    Player p = new Player();

    Thread enemiesFactory = new Thread(this);

    List<EnemyThread> enemies = new ArrayList<EnemyThread>();

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

        double v = (250/ Player.MAX_SPEED) * p.v;
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Скорость: " + v + "км/ч", 100, 30);

        for (int index = 0; index < enemies.size(); index++) {
            EnemyThread e = enemies.get(index);
            if (e.enemy.x >= 2400 || e.enemy.x <= -2400) {
                e.disable();
                enemies.remove(e);
                index--;
            }
            else {
                g.drawImage(e.enemy.img, e.enemy.x, e.enemy.y, null);
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
        for (int i = 0; i < enemies.size(); i++) {
            EnemyThread e = enemies.get(i);
            if (p.getRect().intersects(e.enemy.getRect())){

                new Error();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
                Main.number = 0;
                flag = false;
                //e.disable();
                for (int index = 0; index < enemies.size(); index++) {
                    EnemyThread enemyThread = enemies.get(index);
                    enemyThread.disable();
                    //enemies.remove(enemyThread);
                }
                enemies.clear();
                return;
            }
        }
    }



    @Override
    public void run() {

        while(flag) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(3000) + 1000);
                EnemyThread enemyThread = new EnemyThread(new Enemy(1200, rand.nextInt(300), rand.nextInt(60), this));
                enemies.add(enemyThread);
                enemyThread.start();
                //enemies.add(new Enemy(1200, rand.nextInt(300), rand.nextInt(60), this));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}