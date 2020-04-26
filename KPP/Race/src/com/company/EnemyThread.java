package com.company;

import java.util.Random;

public class EnemyThread extends Thread {
    Enemy enemy;
    private boolean flag = true;

    public EnemyThread(Enemy enemy) {
        super(String.valueOf(++Main.number));
        this.enemy = enemy;
    }

    public void run() {
        Random rand = new Random();
        while (flag) {
            try {
                Thread.sleep(100);//rand.nextInt(300) + 100);
                enemy.move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void disable() {
        flag = false;
    }
}