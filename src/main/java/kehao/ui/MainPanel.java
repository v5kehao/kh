package kehao.ui;

import java.awt.*;
import javax.annotation.PostConstruct;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class MainPanel extends JPanel {

  @Autowired
  private AccountPanel accountPanel;
  @Autowired
  private CenterPanel centerPanel;
  @Autowired
  private AdminPanel adminPanel;

  @PostConstruct
  public void init() {
    setLayout(new BorderLayout());
    add(accountPanel, BorderLayout.WEST);
    add(centerPanel, BorderLayout.CENTER);
    add(adminPanel, BorderLayout.SOUTH);
  }
}
