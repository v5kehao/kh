package kehao.ui;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.model.ExploreSetting;
import kehao.service.ExploreService;
import kehao.ui.widget.AsyncActionListener;
import kehao.ui.widget.NumberField;
import kehao.ui.widget.ReportWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExplorePanel extends FunctionalInterfacePanel<ExploreSetting> {

  public static String KEY = "探索";

  private JCheckBox maze2 = new JCheckBox("2");
  private JCheckBox maze3 = new JCheckBox("3");
  private JCheckBox maze4 = new JCheckBox("4");
  private JCheckBox maze5 = new JCheckBox("5");
  private JCheckBox maze6 = new JCheckBox("6");
  private JCheckBox maze7 = new JCheckBox("7");
  private JCheckBox maze8 = new JCheckBox("8");
  private JCheckBox noChip = new JCheckBox("缺少探索碎片");
  private JCheckBox noThief = new JCheckBox("盗贼冷却");
  private NumberField energyTf = new NumberField();
  private NumberField hour = new NumberField(23);
  private NumberField minute = new NumberField(59);
  private JCheckBox auto = new JCheckBox("自动执行");

  @Autowired
  private ExploreService exploreService;
  @Autowired
  private ExecutorService executorService;
  @Autowired
  private AppFrame frame;

  @PostConstruct
  public void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1;

    JPanel mazes = new JPanel(new GridLayout(0, 4));
    mazes.add(maze2);
    mazes.add(maze3);
    mazes.add(maze4);
    mazes.add(maze5);
    mazes.add(maze6);
    mazes.add(maze7);
    mazes.add(maze8);
    add(mazes, constraints);

    JPanel energy = new JPanel(new FlowLayout());
    energyTf.setPreferredSize(new Dimension(50, 23));
    energy.add(new JLabel("当选中迷宫通关且行动力大于"));
    energy.add(energyTf);
    energy.add(new JLabel("时"));
    constraints.gridy = 1;
    add(energy, constraints);

    JPanel conditions = new JPanel(new FlowLayout());
    conditions.add(new JLabel("其它条件："));
    conditions.add(noChip);
    conditions.add(noThief);
    constraints.gridy = 2;
    add(conditions, constraints);


    constraints.weighty = 1;
    constraints.gridy = 3;
    add(new JLabel(), constraints);

    JButton start = new JButton("一键执行");
    start.addActionListener(new AsyncActionListener(executorService) {
      @Override
      public void run() {
        oneKeyStart();
      }
    });
    start.setPreferredSize(new Dimension(120, 50));
    constraints.weighty = 0;
    constraints.gridy = 4;
    constraints.insets = new Insets(5, 20, 5, 20);
    add(start, constraints);

    JPanel schedule = new JPanel(new FlowLayout());
    schedule.add(new JLabel("每"));
    hour.setPreferredSize(new Dimension(50, 23));
    schedule.add(hour);
    schedule.add(new JLabel("小时"));
    schedule.add(new JLabel("第"));
    minute.setPreferredSize(new Dimension(50, 23));
    schedule.add(minute);
    schedule.add(new JLabel("分钟"));
    schedule.add(auto);
    constraints.gridy = 5;
    constraints.insets = new Insets(0, 0, 5, 0);
    add(schedule, constraints);
  }

  @Override
  protected void save(ExploreSetting setting) {
    setting.setMaze2(maze2.isSelected());
    setting.setMaze3(maze3.isSelected());
    setting.setMaze4(maze4.isSelected());
    setting.setMaze5(maze5.isSelected());
    setting.setMaze6(maze6.isSelected());
    setting.setMaze7(maze7.isSelected());
    setting.setMaze8(maze8.isSelected());
    setting.setNoChip(noChip.isSelected());
    setting.setNoThief(noThief.isSelected());
    setting.setEnergy(energyTf.getIntegerValue());
    setting.setHour(hour.getIntegerValue());
    setting.setMinute(minute.getIntegerValue());
    setting.setAuto(auto.isSelected());
  }

  @Override
  protected void load(ExploreSetting setting) {
    maze2.setSelected(setting.isMaze2());
    maze3.setSelected(setting.isMaze3());
    maze4.setSelected(setting.isMaze4());
    maze5.setSelected(setting.isMaze5());
    maze6.setSelected(setting.isMaze6());
    maze7.setSelected(setting.isMaze7());
    maze8.setSelected(setting.isMaze8());
    noChip.setSelected(setting.isNoChip());
    noThief.setSelected(setting.isNoThief());
    energyTf.setIntegerValue(setting.getEnergy());
    hour.setIntegerValue(setting.getHour());
    minute.setIntegerValue(setting.getMinute());
    auto.setSelected(setting.isAuto());
  }

  @Override
  protected void oneKeyStart(String username, ExploreSetting setting) {
    exploreService.start(username, setting, new ReportWindow(KEY, username));
  }
}
