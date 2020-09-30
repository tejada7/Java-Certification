package com.javacertification.interviewquestions.tondeuse.view.interfaces;

import javax.swing.*;
import java.awt.*;

/**
 * Helper for resizing views.
 */
public interface ResizableView {

    /**
     * Resize the frame according the max screen size
     *
     * @param frame the frame to resize
     */
    default void resizeAndCenterFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width - 200, screenSize.height - 50);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Resize a component horizontally based on a percentage.
     *
     * @param component the component to resize
     * @param percentage the width percentage that will cover out of the screen
     */
    default void setProportionalWidthPreferredSize(Component component, int percentage) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        component.setPreferredSize(new Dimension((int) (screenSize.width * percentage / 100), screenSize.height - 50));
    }
}
