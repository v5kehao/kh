package kehao.ui.widget;

import javax.swing.*;

public class CardGroupComboBox extends JComboBox<Integer> {
  public CardGroupComboBox() {
    super(new Integer[]{1, 2, 3, 4});
  }

  public int getCardGroup() {
    return getSelectedIndex();
  }

  public void setCardGroup(int cardGroup) {
    setSelectedIndex(cardGroup);
  }
}
