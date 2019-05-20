package com.oca.interviewquestions.tondeuse.view;

import com.oca.interviewquestions.tondeuse.MowerLauncher;
import com.oca.interviewquestions.tondeuse.business.enums.Orientation;
import com.oca.interviewquestions.tondeuse.business.interfaces.Movable;
import com.oca.interviewquestions.tondeuse.business.interfaces.Rotatable;
import com.oca.interviewquestions.tondeuse.model.Mower;
import com.oca.interviewquestions.tondeuse.model.Position;
import com.oca.interviewquestions.tondeuse.view.interfaces.ResizableView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Arrays.asList;

/**
 * View component responsible for displaying a representation of the grass field, as well as the logging.
 */
public class Drawer extends JFrame implements ResizableView, Movable, Rotatable {

    private int rows;
    private int columns;
    private transient Mower mower;
    private double[] coordinatesInX;
    private double[] coordinatesInY;
    private URL currentImageURL;
    public static JTextArea textArea = new JTextArea();
    private static final Logger LOGGER = Logger.getLogger(Drawer.class.toString());

    /**
     * Initialize the UI components.
     *
     * @param rows    number of rows to be drawn
     * @param columns number of columns to be drawn
     */
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
        // main panel
        JPanel mainPanel = new JPanel();
        BoxLayout boxLayoutManager = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(boxLayoutManager);
        mainPanel.setOpaque(false);
        // grassDrawer
        JPanel grassDrawer = new GrassDrawer();
        mainPanel.add(grassDrawer);
        setProportionalWidthPreferredSize(grassDrawer, 70);
        // logging
        textArea.setBackground(new Color(1, 36, 86));
        mainPanel.add(textArea);
        setProportionalWidthPreferredSize(textArea, 30);
        textArea.setFont(new Font("Consolas", Font.BOLD, 27));
        textArea.setForeground(Color.white);
        textArea.setText("Welcome !!!");
        // frame
        add(mainPanel);
        resizeAndCenterFrame(this);
        setBackground(Color.white);
    }

    @Override
    public void execute(Orientation orientation) {
        repaint();
    }

    @Override
    public Orientation turnRight() {
        currentImageURL = mower.getCurrentOrientation().turnRight().getImageURL();
        return rotateImage();
    }

    @Override
    public Orientation turnLeft() {
        currentImageURL = mower.getCurrentOrientation().turnLeft().getImageURL();
        return rotateImage();
    }

    public Orientation rotateImage() {
        repaint();
        return mower.getCurrentOrientation();
    }

    /**
     * Inner class representing the grass drawer
     */
    private class GrassDrawer extends JPanel {

        /**
         * Init the panel components
         */
        private GrassDrawer() {
            setOpaque(false);
            getContentPane().setBackground(new Color(47, 174, 62));
        }

        @Override
        public void paint(Graphics g) {
            drawGrassField(g);
        }

        private void drawGrassField(Graphics g) {
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
                displayImage(g, mower.getCurrentPosition());
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "The image cannot be found {0}", e.getMessage());
            }

        }

        private void displayImage(Graphics g, Position currentPosition) throws IOException {
            g.drawImage(ImageIO.read(currentImageURL), (int) coordinatesInX[currentPosition.posX],
                    (int) coordinatesInY[currentPosition.posY], this);
        }
    }

    /**
     * Unit test for GUI
     *
     * @param args empty
     */
    public static void main(String[] args) {
        MowerLauncher.processInput(asList("5 5",
                "1 2 N",
                "GAGAGAGAA"), true);
    }
}
