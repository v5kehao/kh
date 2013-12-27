package kehao.ui.widget;

import javax.swing.*;

public class JourneyBossDifficultyComboBox extends JComboBox<String> {
  public JourneyBossDifficultyComboBox() {
    super(new String[]{"简单", "普通", "困难", "噩梦", "炼狱"});
  }

  public int getDifficulty() {
    return getSelectedIndex();
  }

  public void setDifficulty(int difficulty) {
    setSelectedIndex(difficulty);
  }
}
