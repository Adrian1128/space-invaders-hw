package en.tresz.spaceinvaders.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * A utility class to create customized buttons.
 */
public class ButtonMaker {

    private ButtonMaker() {
        // private constructor
    }

    /**
     * Makes a customized button
     * The names of the images should follow these rules:
     * -normal icon: <name>-button.png
     * -hover icon: <name>-button-selected.gif
     * -hover icon: <name>-button-pressed.gif
     * 
     * @param name the name of the button
     * @return a customized button
     */
    public static JButton buttonSetup(String name, int width, int height) {
        JButton button = new JButton();

        ImageIcon playNormalIcon = ImageLoader.scaledIcon("/images/" + name + "-button.png", width, height);
        ImageIcon playHoverIcon = ImageLoader.scaledIcon("/images/" + name + "-button-selected.png",
                width, height);
        ImageIcon playPressedIcon = ImageLoader.scaledIcon("/images/" + name + "-button-pressed.png",
                width, height);
        
        button.setIcon(playNormalIcon);
        button.setRolloverIcon(playHoverIcon);
        button.setPressedIcon(playPressedIcon);

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        return button;
    }
}
