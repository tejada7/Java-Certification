package com.oca.interviewquestions.tondeuse.view.interfaces;

import javax.swing.*;
import java.awt.*;

public interface ResizableView {

    /**
     * Resize the frame to the screen resolution where the application runs
     *
     * @param frame
     */
    default void resizeScreenSize(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) (screenSize.width * 0.55), (int) (screenSize.height * 0.60));
        frame.pack();
        frame.setLocationRelativeTo(null); // center the frame
    }

    /**
     * Resize the JInternalFrame half of the parent's full resolution.
     *
     * @param frame
     */
    default void resizeScreenSize(JInternalFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) (screenSize.width * 0.55), (int) (screenSize.height * 0.60));
        frame.pack();
        frame.setLocation(screenSize.width / 2 - frame.getWidth() / 2
                , (screenSize.height / 2 - frame.getHeight() / 2) - 100);
    }

    /**
     * Center the pop up dialog
     *
     * @param dialog
     */
    default void prepareDialog(JDialog dialog) {
        dialog.pack();
        dialog.setLocationRelativeTo(null); // center the dialog
    }

    /**
     * Fully resizes the frame according to the screen resolution
     *
     * @param frame
     */
    default void resizeAndCenterFrame(JFrame frame) {
        fullScreen(frame);
//        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Resize the component according the max screen size
     *
     * @param component the component to resize
     */
    default void fullScreen(Component component) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        component.setSize(screenSize.width - 100, screenSize.height - 50);
//        frame.pack();
    }

    /**
     * Set the icon located at the top-left corner of the frame.
     *
     * @param frame
     */
    default void setIcon(JFrame frame) {
        ImageIcon imagen = new ImageIcon(this.getClass().getResource
                ("/icons/favicon.png"));//cambiar el ícono superior de la izquierda
        frame.setIconImage(imagen.getImage());
    }
}
