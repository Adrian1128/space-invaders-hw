package en.tresz.spaceinvaders.util;

import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A utility class to safely load images from the resources folder.
 */
public class ImageLoader {

    private ImageLoader() {
        // private constructor
    }

    /**
     * Loads an image from the resources and returns an ImageIcon
     *
     * @param path resource path
     * @return ImageIcon or null if resource missing
     */
    public static ImageIcon loadIcon(String path) {
        URL iconUrl = ImageLoader.class.getResource(path);

        if (iconUrl == null) {
            System.err.println("Could not find icon: " + path);
            return null;
        }

        return new ImageIcon(iconUrl);
    }

    /**!!!
     * Loads an image from the resources and returns a scaled ImageIcon using
     * nearest-neighbor scaling
     *
     * @param path   resource path
     * @param width  target width in pixels
     * @param height target height in pixels
     * @return scaled ImageIcon or null if resource missing
     */
    public static ImageIcon loadScaledIcon(String path, int width, int height) {
        URL iconUrl = ImageLoader.class.getResource(path);

        if (iconUrl == null) {
            System.err.println("Could not find icon: " + path);
            return null;
        }

        try {
            BufferedImage img = ImageIO.read(iconUrl);
            if (img == null) {
                return new ImageIcon(iconUrl);
            }

            Image scaled = scaleImageNearest(img, width, height);
            return new ImageIcon(scaled);
        } catch (IOException e) {
            System.err.println("Failed to read image: " + path + " -> " + e.getMessage());
            return new ImageIcon(iconUrl);
        }
    }

    /**!!!
     * Scales an Image to the given size using nearest-neighbor scaling.
     * 
     * @param src    the source image
     * @param width  the target width
     * @param height the target height
     * @return the scaled image
     */
    public static Image scaleImageNearest(Image src, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = out.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.drawImage(src, 0, 0, width, height, null);
        g2.dispose();
        return out;
    }
}