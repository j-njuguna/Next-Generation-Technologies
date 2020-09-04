package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;

public class ConwaysGameOfLife extends JFrame implements Runnable, MouseListener, MouseMotionListener {


    private static final Dimension WindowSize = new Dimension(800, 800);
    private BufferStrategy strategy;
    private boolean[][][] gameState = new boolean[40][40][2];
    private boolean playing =false;

    // constructor
    public ConwaysGameOfLife () {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2 - 400;
        int y = screensize.height/2 - 400;
        setBounds(x, y, 800, 800);
        setVisible(true);
        this.setTitle("Conway's Game of life");

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();

        // initialise double-buffering
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        // register the Jframe itself to receive mouse events
        addMouseListener(this);
        addMouseMotionListener(this);

        // initialise the game state
        for (x=0;x<40;x++) {
            for (y=0;y<40;y++) {
                gameState[x][y][0]=false;
                gameState[x][y][1]=false;
            }
        }
    }



    // thread's entry point
    public void run() {
        while ( 1==1 ) {
            // 1: sleep for 1/5 sec
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) { }

            // 2: animate game objects [nothing yet!]
            if(playing==true){


                for(int x=0;x<40;x++) {
                    for(int y=0;y<40;y++) {
                        // count the live neighbours of cell [x][y][0]
                        int liveneighbours =0;
                        for(int xx=-1;xx<=1;xx++) {
                            for(int yy=-1;yy<=1;yy++) {
                                if(xx!=0 || yy!=0) {

                                    // but.. what if x+xx==-1, etc. ?
                                    int X = x+xx;
                                    int Y = y+yy;
                                    if(X<0)X=0;
                                    if(X>=40)X=39;
                                    if(Y<0)Y=0;
                                    if(Y>=40)Y=39;

                                    // check cell [x+xx][y+yy][0]
                                    if(gameState[X][Y][0]==true)liveneighbours++;

                                }
                            }
                        }

                        //apply rhe rules

                        if(gameState[x][y][0]) {//front buffer
                            if (liveneighbours < 2 ) {
                                gameState[x][y][1] = false;
                            } else if (liveneighbours > 3 ) {
                                gameState[x][y][1] = false;
                            } else if (liveneighbours == 3 ) {
                                gameState[x][y][1] = true;
                            }
                        }
                    }
                }
                //switch the back to the front
                for (int x=0;x<40;x++) {
                    for (int y=0;y<40;y++) {
                        gameState[x][y][0]=gameState[x][y][1];
                    }
                }
            }

            // 3: force an application repaint
            this.repaint();

        }
    }

    // mouse events which must be implemented for MouseListener
    public void mousePressed(MouseEvent e) {

            //the button presses
            int x = e.getX();
            int y = e.getY();

            //start button
            if((x>=20 && x<=70) && (y>=40 && y<=60)){
                playing=true;
            }
            if((x>=80 && x<=140) && (y>=40 && y<=60)){
                //randomise the array
                for (x=0;x<40;x++) {
                    for (y=0;y<40;y++) {
                        int r = (int) (Math.random()*1000);
                        if(r%2==1){
                            gameState[x][y][0]=true;
                        }else{
                            gameState[x][y][0]=false;
                        }
                    }
                }
            }

        if((x>=150 && x<=200) && (y>=40 && y<=60)){
            //reset
            for (x=0;x<40;x++) {
                for (y=0;y<40;y++) {
                    gameState[x][y][0]=false;
                }
            }
        }
        if((x>=250 && x<=290) && (y>=40 && y<=60)){
            saveImage();
            //save
        }
        if((x>=300 && x<=340) && (y>=40 && y<=60)){
            loadImage();
            //load
        }


        // determine which cell of the gameState array was clicked on
            x = e.getX()/20;
            y = e.getY()/20;
            // toggle the state of the cell
            gameState[x][y][0] = !gameState[x][y][0];
            // throw an extra repaint, to get immediate visual feedback
            this.repaint();

    }


    public void mouseReleased(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) { }

    public void mouseExited(MouseEvent e) { }

    public void mouseClicked(MouseEvent e) { }


    public void mouseMoved(MouseEvent e){

    }

    public void mouseDragged(MouseEvent e){

            int x=e.getX()/20;
            int y= e.getY()/20;

            gameState[x][y][0] = !gameState[x][y][0];
            this.repaint();

    }

    public void loadImage(){

        String line=null;
        String filename = "/gameoflife.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            do {
                try {
                    line = reader.readLine();
// do something with String here!
                } catch (IOException e) { }
            }
            while (line != null);
            reader.close();
        } catch (IOException e) { }
    }




    public void saveImage(){

        String outputtext="";

        String line=null;

        String filename = "/gameoflife.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            do {
                try {
                    writer.write(outputtext);
// do something with String here!
                } catch (IOException e) { }
            }
            while (line != null);
            writer.close();
        } catch (IOException e) { }
    }
    //

    // application's paint method
    public void paint(Graphics g) {
        g = strategy.getDrawGraphics(); // draw to offscreen buffer

        // clear the canvas with a big black rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 800);



        // redraw all game objects
        g.setColor(Color.WHITE);
        for (int x=0;x<40;x++) {
            for (int y=0;y<40;y++) {
                if (gameState[x][y][0]) {
                    g.fillRect(x*20, y*20, 20, 20);
                }
            }
        }

        //draw buttons

            g.setColor(Color.GREEN);
            g.fillRect(20, 40, 50, 20);//start
            g.fillRect(80, 40, 60, 20);//random
            g.fillRect(150, 40, 50, 20);//reset
            g.fillRect(250, 40, 40, 20);//save
            g.fillRect(300, 40, 40, 20);//load


            g.setColor(Color.BLACK);
            g.drawString("START", 25, 55);
            g.drawString("RANDOM", 85, 55);
            g.drawString("RESET", 155, 55);
            g.drawString("SAVE", 255, 55);
            g.drawString("LOAD", 305, 55);



        // flip the buffers
        g.dispose();
        strategy.show();

    }

    // application entry point
    public static void main(String[] args) {
        ConwaysGameOfLife w = new ConwaysGameOfLife();



    }

}

