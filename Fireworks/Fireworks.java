package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.*;
import javax.swing.JFrame;

public class Fireworks extends JFrame implements Runnable, MouseListener {

    private LinkedList particlesList = new LinkedList();
    private BufferStrategy strategy;
    private boolean initialised = false;

    // embedded class for handling individual particles
    public class Particle {
        private double x, y; // position
        private Color c;
        private int lifetime = 200; // how many frames are left before destruction

        int strtCoX = (int) (Math.random() * 600);
        int strtCoY = (int) (Math.random() * 600);
        private double ranCoX = (int) (Math.random() * 20);
        private double ranCoY = (int) (Math.random() * 20);


        // constructor
        public Particle(int x, int y) {
            this.x = x;
            this.y = y;
            int velX = (int) (Math.random() * ranCoX);
            int velY = (int) (Math.random() * ranCoY);
        }

        // updates the particle for 1 frame of animation
        // returns true if the particle should be destroyed
        public boolean update() {

            int velX = (int) (Math.random() * ranCoX);
            int velY = (int) (Math.random() * ranCoY);


            if (strtCoY > 0)
                strtCoY = strtCoY - ;
            else if (strtCoX > 5)
                strtCoX = strtCoX - 10;

            strtCoX += velX;
            strtCoY += velY;

            lifetime--;
            return (lifetime<=0);
        }


        public void paint(Graphics g) {
            g.setColor(Color.white);

            int R = (int) (Math.random() * 256);
            int G = (int) (Math.random() * 256);
            int B = (int) (Math.random() * 256);
            Color c = new Color(R, G, B);
            g.setColor(c);

            g.fillRect((int) x, (int) y, 10,10);
            g.fillRect(strtCoX, strtCoY, 10,10);






        }
    }

    // constructor
    public Fireworks() {
        this.setBounds(10, 10, 600, 600);
        this.setVisible(true);
        addMouseListener(this);

        createBufferStrategy(2);
        strategy = getBufferStrategy();

        Thread t = new Thread(this);
        t.start();

        initialised = true;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Particle p = new Particle(x,y);
        particlesList.add(p);
    }

    public void run() {
        while(1==1) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            Iterator i = particlesList.iterator();
            while (i.hasNext()) {
                Particle p = (Particle)i.next();

                if (p.update())
                    i.remove();

            }

            this.repaint();
        }
    }

    public void paint(Graphics g) {
        if (initialised == true){
            g = strategy.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0,0,600,600);
            int R = (int) (Math.random() * 256);
            int G = (int) (Math.random() * 256);
            int B = (int) (Math.random() * 256);
            Color c = new Color(R, G, B);
            g.setColor(c);

            Iterator i = particlesList.iterator();
            while (i.hasNext()) {
                Particle p = (Particle)i.next();
                p.paint(g);
            }




            g.dispose();
            strategy.show();
        }

    }


    public static void main(String[] args) {
        Fireworks f = new Fireworks();
    }

}
