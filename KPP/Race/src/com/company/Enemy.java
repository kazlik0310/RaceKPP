package com.company;


import javax.swing.ImageIcon;
import java.awt.*;

public class Enemy {

    int x;
    int y;
    int v;
    Image img = new ImageIcon("D:\\kazlik\\proga\\KPP\\Race\\src\\enemy.png").getImage();
    Road road;

    public Enemy(int x, int y, int v, Road road) {
        this.x=x;
        this.y=y;
        this.v=v;
        this.road=road;
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, 302, 117);
    }

    public void move() {
        x = x - road.p.v + v;

    }

}
