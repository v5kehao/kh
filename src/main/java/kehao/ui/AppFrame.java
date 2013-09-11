package kehao.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.emulator.UnknownErrorHandler;
import kehao.io.ResourcesIO;
import kehao.ui.widget.ErrorWindow;
import kehao.ui.widget.SwingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AppFrame extends JFrame implements UnknownErrorHandler {

  public static BufferedImage FAVICON = ResourcesIO.getImage(ResourcesIO.FAVICON);
  public static Component LOCATION;

  @Autowired
  private MainPanel mainPanel;
  @Autowired
  private ExecutorService executorService;

  @PostConstruct
  public void init() {
    setTitle("魔卡幻想辅助工具");
    setIconImage(FAVICON);

    add(mainPanel);
    setPreferredSize(new Dimension(900, 500));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    LOCATION = this;
  }

  public void maskAction(final Runnable task, final boolean autoUnmask) {
    final Component me = this;
    SwingUtils.setEnabled(me, false);
    executorService.submit(new Runnable() {
      @Override
      public void run() {
        task.run();
        if(autoUnmask) {
          SwingUtils.setEnabled(me, true);
        }
      }
    });
  }

  public void unmask() {
    SwingUtils.setEnabled(this, true);
  }

  @Override
  public void print(String username, String msg) {
    new ErrorWindow(username, msg);
  }

}
