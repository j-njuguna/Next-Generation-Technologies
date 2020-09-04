package com.company;
import java.awt.*;
import javax.swing.*;

public class MovingSquares extends JFrame implements Runnable {
    private static final Dimension WindowSize = new Dimension(600, 600);
    private static final int NUMGAMEOBJECTS = 30;
    private GameObject[] GameObjectsArray = new GameObject[NUMGAMEOBJECTS];
//    int strtCoX = (int) (Math.random() * 600);
//    int strtCoY = (int) (Math.random() * 600);

    public MovingSquares() {

        for (int i = 0; i < GameObjectsArray.length; i++) GameObjectsArray[i] = new GameObject();

        this.setTitle("Animated squares");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);

        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        while (true) {
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e)
            {
                for (GameObject currentObject : GameObjectsArray) currentObject.move();
            }
            this.repaint();
        }
    }

        public void paint(Graphics g){
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WindowSize.width, WindowSize.height);

            int size = 50;
            int R = (int) (Math.random() * 256);
            int G = (int) (Math.random() * 256);
            int B = (int) (Math.random() * 256);
            Color c = new Color(R, G, B);
            g.setColor(c);
            for (GameObject currentObject : GameObjectsArray)
                g.fillRect(currentObject.strtCoX, currentObject.strtCoY, size, size);
        }

        public static void main (String[] args){
            MovingSquares M = new MovingSquares();
        }
    }

