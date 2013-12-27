package kehao.ui.widget;

import javax.swing.*;

public class BorderlessButton extends JButton {
  public BorderlessButton() {
    setBorder(BorderFactory.createEmptyBorder());
    setFocusPainted(false);
  }

  public BorderlessButton(Icon icon) {
    super(icon);
    setBorder(BorderFactory.createEmptyBorder());
    setFocusPainted(false);
  }

  public BorderlessButton(String text) {
    super(text);
    setBorder(BorderFactory.createEmptyBorder());
    setFocusPainted(false);
  }

  public BorderlessButton(Action a) {
    super(a);
    setBorder(BorderFactory.createEmptyBorder());
    setFocusPainted(false);
  }

  public BorderlessButton(String text, Icon icon) {
    super(text, icon);
    setBorder(BorderFactory.createEmptyBorder());
    setFocusPainted(false);
  }
}
