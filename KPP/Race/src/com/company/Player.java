package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Player {

    public static final int MAX_SPEED=50;
    public static final int MAX_TOP=10;
    public static final int MAX_BOTTON=300;


    Image img = new ImageIcon("D:\\kazlik\\proga\\KPP\\Race\\src\\player.png").getImage();

    public Rectangle getRect(){
        return new Rectangle(x, y, 302, 117);
    }

    int v=0;
    int dv=0;
    int s=0;

    int x=100;
    int y=30;
    int dy=0;

    int layer1=0;
    int layer2=1300;

    public void move(){
        s+=v;
        v+=dv;
        if(v<=0){
            v=0;
        }
        if(v>=MAX_SPEED){
            v=MAX_SPEED;
        }
        y+=dy;
        if(y<=MAX_TOP){
            y=MAX_TOP;
        }
        if(y>=MAX_BOTTON){
            y=MAX_BOTTON;
        }
        if(layer2 - v <= 0){
            layer1=0;
            layer2=1300;
        }
        else {
            layer1 -= v;
            layer2 -= v;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT) {
            dv=1;
        }
        if(key == KeyEvent.VK_LEFT) {
            dv=-1;
        }
        if(key == KeyEvent.VK_UP) {
            dy=-10;
        }
        if(key == KeyEvent.VK_DOWN) {
            dy=10;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            dv = 0;
        }
        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }



}
