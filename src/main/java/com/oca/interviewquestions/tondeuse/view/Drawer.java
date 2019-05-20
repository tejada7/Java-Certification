package com.oca.interviewquestions.tondeuse.view;

import com.oca.interviewquestions.tondeuse.MowerLauncher;
import com.oca.interviewquestions.tondeuse.business.Mower;
import com.oca.interviewquestions.tondeuse.business.enums.Orientation;
import com.oca.interviewquestions.tondeuse.business.interfaces.Movable;
import com.oca.interviewquestions.tondeuse.model.Position;
import com.oca.interviewquestions.tondeuse.view.interfaces.ResizableView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static java.util.Arrays.asList;

public class Drawer extends JFrame implements ResizableView, Movable {

    private int rows;
    private int columns;
    private transient Mower mower;
    private double[] coordinatesInX;
    private double[] coordinatesInY;
    private URL currentImageURL;

    public Drawer(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        initComponents();
        coordinatesInX = new double[rows];
        coordinatesInY = new double[columns];
    }

    public void setMower(Mower mower) {
        this.mower = mower;
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // grassDrawer
        JPanel grassDrawer = new GrassDrawer(columns, rows);
        fullScreen(grassDrawer);
        grassDrawer.setBackground(Color.black);
        add(grassDrawer);
        // frame
        resizeAndCenterFrame(this);
        setBackground(Color.white);
    }

    @Override
    public void moveForward(Orientation orientation) {
        mower.moveForward(orientation);
        currentImageURL = orientation.getImageURL();
        repaint();
    }

    class GrassDrawer extends JPanel {

        private int rows;
        private int columns;

        public GrassDrawer(int rows, int columns) {
            setOpaque(false);
            this.rows = rows;
            this.columns = columns;
        }

        @Override
        public void paint(Graphics g) {
            initMower(g);
        }

        public void initMower(Graphics g) {
            getContentPane().setBackground(Color.green);
            g.setColor(Color.black);
            final double rectangleHeight = getSize().getHeight() / rows;
            for (int i = 1; i < rows; i++) {
                g.fillRect(0, (int) (rectangleHeight * i), (int) getSize().getWidth(), 10);
                coordinatesInY[rows - i - 1] = (rectangleHeight * i);
            }
            final double rectangleWidth = getSize().getWidth() / columns;
            for (int i = 1; i <= columns; i++) {
                g.fillRect((int) (rectangleWidth * i), 0, 10, (int) getSize().getHeight());
                coordinatesInX[i - 1] = (rectangleWidth * (i - 1));
            }
            try {
                showImage(g, mower.getCurrentPosition());
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        private void showImage(Graphics g, Position currentPosition) throws IOException {
            g.drawImage(ImageIO.read(currentImageURL), (int) coordinatesInX[currentPosition.posX],
                    (int) coordinatesInY[currentPosition.posY], this);
        }
    }

    public static void main(String[] args) {
        MowerLauncher.processInput(asList("5 5",
                "1 2 N",
                "GAGAGAGAA",
                "3 3 E",
                "AADAADADDA"), true);
    }
}
