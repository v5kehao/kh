package kehao.ui.widget;

import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class NumberField extends JFormattedTextField {
  public NumberField(int min, int max) {
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(min);
    formatter.setMaximum(max);
    setFormatterFactory(new DefaultFormatterFactory(formatter));
  }

  public NumberField(int max) {
    this(0, max);
  }

  public NumberField() {
    this(0, Integer.MAX_VALUE);
  }

  public void setIntegerValue(int value) {
    setValue(value);
  }

  public int getIntegerValue() {
    return (Integer) getValue();
  }
}
