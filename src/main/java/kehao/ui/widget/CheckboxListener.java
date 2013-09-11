package kehao.ui.widget;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public abstract class CheckboxListener implements ItemListener {
  @Override
  public void itemStateChanged(ItemEvent e) {
    check(e.getStateChange() == ItemEvent.SELECTED);
  }

  public abstract void check(boolean state);
}
