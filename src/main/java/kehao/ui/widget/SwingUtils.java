package kehao.ui.widget;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class SwingUtils {
  public static void setEnabled(Component component, boolean enable) {
    if(!(component instanceof Window)) component.setEnabled(enable);
    if(component instanceof Container) {
      for(Component child : ((Container) component).getComponents()) {
        setEnabled(child, enable);
      }
    }
  }



  public static void addValueChangeListener(Component component, final Runnable task) {
    if(component instanceof JTextComponent) {
      ((JTextComponent) component).getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
          task.run();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
          task.run();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
          task.run();
        }
      });
    } else if(component instanceof ItemSelectable) {
      ((ItemSelectable) component).addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
          task.run();
        }
      });
    }
  }
}
