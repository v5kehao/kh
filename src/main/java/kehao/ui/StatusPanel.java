package kehao.ui;

import java.awt.*;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.emulator.UnknownErrorHandler;
import org.springframework.stereotype.Controller;

@Controller
public class StatusPanel extends JPanel {

  private JLabel statusL = new JLabel();

  @PostConstruct
  public void init() {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    add(statusL);
  }

  public void info(String msg) {
    statusL.setForeground(Color.black);
    statusL.setText(msg);
  }

  public void error(String msg) {
    statusL.setForeground(Color.red);
    statusL.setText(msg);
  }
}
