package kehao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.io.ResourcesIO;
import kehao.ui.widget.BorderlessButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ToolsPanel extends JMenuBar {

  private JButton journey = new BorderlessButton(ResourcesIO.getIcon(ResourcesIO.JOURNEY, 64));

  @Autowired
  private AppFrame appFrame;
  @Autowired
  private JourneyWindow journeyWindow;

  @PostConstruct
  public void init() {
    journey.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        appFrame.maskAction(new Runnable() {
          @Override
          public void run() {
            journeyWindow.setVisible(true);
          }
        }, false);
      }
    });
    add(journey);
  }
}
