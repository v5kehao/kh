package kehao.ui.widget;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import kehao.ui.AppFrame;
import kehao.util.AppendableReport;
import kehao.util.DateStringUtils;

public class ReportWindow extends JFrame implements AppendableReport {

  private JTextArea text = new JTextArea();

  public ReportWindow(String section, String user) {
    this(section + " - " + user);
  }

  public ReportWindow(String title) {
    super(title);
    setIconImage(AppFrame.FAVICON);
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(800, 300));

    text.setEditable(false);
    add(new JScrollPane(text), BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel(new FlowLayout());
    JButton hide = new JButton("隐藏");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    hide.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    buttonsPanel.add(hide);

    add(buttonsPanel, BorderLayout.SOUTH);

    pack();
    setLocationRelativeTo(AppFrame.LOCATION);
    setVisible(true);
  }

  @Override
  public AppendableReport append(String src, String record) {
    if(isVisible()) {
      text.append(DateStringUtils.time() + "\t" + record + "\n");
      text.setCaretPosition(text.getDocument().getLength());
    }
    return this;
  }

  @Override
  public void finish(String src) {
    if(!isVisible()) {
      dispose();
    } else {
      append(src, "执行完成");
    }
  }

  @Override
  public void destroy() {
    dispose();
  }
}
