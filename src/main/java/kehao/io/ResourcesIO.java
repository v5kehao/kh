package kehao.io;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ResourcesIO {

  public static String DELETE2 = "/delete2.png";
  public static String DISK_BLUE = "/disk_blue.png";
  public static String FAVICON = "/favicon.jpg";
  public static String JOURNEY = "/journey.gif";

  public static BufferedImage getImage(String name) {
    try {
      return ImageIO.read(ResourcesIO.class.getResourceAsStream(name));
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static ImageIcon getIcon(String name) {
    return new ImageIcon(getImage(name));
  }

  public static ImageIcon getIcon(String name, int dimension) {
    return new ImageIcon(getImage(name).getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH));
  }

}
