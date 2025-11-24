package en.tresz.spaceinvaders.util;

import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A utility class to safely load images from the resources folder.
 */
public class ImageLoader {

    private ImageLoader() {
        // private constructor
    }

    public static BufferedImage loadBufferedImage(String path) {
        URL imgUrl = ImageLoader.class.getResource(path);

        if (imgUrl == null) {
            System.err.println("Could not find image: " + path);
            return null;
        }

        try {
            return ImageIO.read(imgUrl);
        } catch (IOException e) {
            System.err.println("Failed to read image: " + path + " -> " + e.getMessage());
            return null;
        }
    }

    /**
     * !!!
     * Scales a BufferedImage to the given size using nearest-neighbor scaling.
     * 
     * @param img    the source image
     * @param width  the target width
     * @param height the target height
     * @return the scaled image
     */
    public static BufferedImage scaledBufferedImageNearest(BufferedImage img, int width, int height) {
        if (img == null) {
            System.err.println("Image not found, cannot scale it.");
            return null;
        }

        BufferedImage scaled = new BufferedImage(width, height, img.getType());

        for (int y = 0; y < height; y++) {
            int srcY = y * img.getHeight() / height;
            for (int x = 0; x < width; x++) {
                int srcX = x * img.getWidth() / width;
                scaled.setRGB(x, y, img.getRGB(srcX, srcY));
            }
        }

        return scaled;
    }

    /**
     * Loads a BufferedImage from the resources and returns a scaled ImageIcon using
     * nearest-neighbor scaling
     *
     * @param path   resource path
     * @param width  target width in pixels
     * @param height target height in pixels
     * @return scaled ImageIcon or null if resource missing
     */
    public static ImageIcon scaledIcon(String path, int width, int height) {
        URL iconUrl = ImageLoader.class.getResource(path);

        if (iconUrl == null) {
            System.err.println("Could not find icon: " + path);
            return null;
        }

        try {
            BufferedImage img = ImageIO.read(iconUrl);
            BufferedImage scaled = scaledBufferedImageNearest(img, width, height);
            return new ImageIcon(scaled);
        } catch (IOException e) {
            System.err.println("Failed to read image: " + path + " -> " + e.getMessage());
            return new ImageIcon(iconUrl);
        }
    }

}