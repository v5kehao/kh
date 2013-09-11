package kehao.ui.widget;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import kehao.ui.AppFrame;

public class ErrorWindow extends JFrame {

  public ErrorWindow(String username, final String message) {
    super("未知错误 - " + username);
    setIconImage(AppFrame.FAVICON);
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(300, 120));
    add(new JLabel(message), BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout());
    JButton copy = new JButton("复制到剪贴板");
    copy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(message), null);
      }
    });
    buttonPanel.add(copy);
    JButton close = new JButton("关闭");
    close.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    buttonPanel.add(close);
    add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setLocationRelativeTo(AppFrame.LOCATION);
    setVisible(true);
  }
}
