package kehao.ui.widget;

import javax.swing.*;

public class LegionTechComboBox extends JComboBox<String> {
  public LegionTechComboBox() {
    super(new String[]{"军团资产", "军团等级", "金币加成", "经验加成", "强化加成", "森林加成", "王国加成", "蛮荒加成", "地狱加成", "军团商店"});
  }

  public int getTech() {
    return getSelectedIndex();
  }

  public void setTech(int tech) {
    setSelectedIndex(tech);
  }
}
