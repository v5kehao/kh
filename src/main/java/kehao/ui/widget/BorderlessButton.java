package kehao.ui.widget;

import javax.swing.*;

public class BorderlessButton extends JButton {
  public BorderlessButton() {
    setBorder(BorderFactory.createEmptyBorder());
  }

  public BorderlessButton(Icon icon) {
    super(icon);
    setBorder(BorderFactory.createEmptyBorder());
  }

  public BorderlessButton(String text) {
    super(text);
    setBorder(BorderFactory.createEmptyBorder());
  }

  public BorderlessButton(Action a) {
    super(a);
    setBorder(BorderFactory.createEmptyBorder());
  }

  public BorderlessButton(String text, Icon icon) {
    super(text, icon);
    setBorder(BorderFactory.createEmptyBorder());
  }
}
