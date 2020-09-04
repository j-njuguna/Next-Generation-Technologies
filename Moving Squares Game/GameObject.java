package com.company;
import java.awt.*;

public class GameObject {
    private double x;
    private double y;
    private Color c;

    int strtCoX = (int) (Math.random() * 600);
    int strtCoY = (int) (Math.random() * 600);
    private int ranCoX = (int) (Math.random() * 20);
    private int ranCoY = (int) (Math.random() * 20);

    public GameObject() {
        int strtX = (int) x;
        int strtY = (int) y;
        int speedX = (int) (Math.random() * 10);
        int speedY = (int) (Math.random() * 10);
    }

    public void move() {
        int velX = (int) (Math.random() * ranCoX);
        int velY = (int) (Math.random() * ranCoY);

        if (strtCoY > 600)
            strtCoY = strtCoY - 550;
        else if (strtCoX > 600)
            strtCoX = strtCoX - 550;

        strtCoX += velX;
        strtCoY += velY;
    }

    public void paint(Graphics g) {
        int size = 50;
        x = (Math.random() * 600);
        y = (Math.random() * 600);
        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);
        Color c = new Color(R, G, B);
        g.setColor(c);
        g.fillRect((int) x, (int) y, size, size);
        g.fillRect(strtCoX, strtCoY, size, size);


    }
}