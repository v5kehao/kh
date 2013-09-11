package kehao.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.model.MazeSetting;
import kehao.service.AssetsService;
import kehao.service.MazeService;
import kehao.ui.widget.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MazePanel extends FunctionalInterfacePanel<MazeSetting> {

  public static String KEY = "迷宫";

  private JCheckBox maze2 = new JCheckBox("2");
  private JCheckBox maze3 = new JCheckBox("3");
  private JCheckBox maze4 = new JCheckBox("4");
  private JCheckBox maze5 = new JCheckBox("5");
  private JCheckBox maze6 = new JCheckBox("6");
  private JCheckBox maze7 = new JCheckBox("7");
  private JCheckBox maze8 = new JCheckBox("8");
  private JRadioButton asc = new JRadioButton("升序进行");
  private JRadioButton desc = new JRadioButton("降序进行");
  private NumberField budgetTf = new NumberField(200);
  private NumberField energyTf = new NumberField(400);
  private CardGroupComboBox cardGroupCb = new CardGroupComboBox();
  private JCheckBox dynamicCardGroupCb = new JCheckBox("使用");
  private String dynamicCardGroup;
  private JCheckBox exitDynamicCardGroupCb = new JCheckBox("使用");
  private CardGroupComboBox exitCardGroupCb = new CardGroupComboBox();
  private String exitDynamicCardGroup;
  private NumberField retryTf = new NumberField(20);
  private JCheckBox shuffle = new JCheckBox("随机遇敌顺序");
  private NumberField minDelayTf = new NumberField(300000);
  private NumberField maxDelayTf = new NumberField(300000);

  private NumberField hour = new NumberField(24);
  private NumberField minute = new NumberField(59);
  private JCheckBox auto = new JCheckBox("自动执行");

  @Autowired
  private MazeService mazeService;
  @Autowired
  private ExecutorService executorService;
  @Autowired
  private AssetsService assetsService;
  @Autowired
  private InfoService infoService;
  @Autowired
  private AppFrame appFrame;

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

    JPanel order = new JPanel(new FlowLayout());
    ButtonGroup bg = new ButtonGroup();
    bg.add(asc);
    bg.add(desc);
    order.add(asc);
    order.add(desc);
    constraints.gridy = 1;
    add(order, constraints);

    JPanel budget = new JPanel(new FlowLayout(FlowLayout.LEFT));
    budgetTf.setPreferredSize(new Dimension(50, 23));
    budget.add(new JLabel("单次重置最多使用"));
    budget.add(budgetTf);
    budget.add(new JLabel("晶钻"));
    constraints.gridy = 2;
    add(budget, constraints);

    JPanel energy = new JPanel(new FlowLayout(FlowLayout.LEFT));
    energyTf.setPreferredSize(new Dimension(50, 23));
    energy.add(new JLabel("当体力耗尽时最多使用"));
    energy.add(energyTf);
    energy.add(new JLabel("晶钻购买体力"));
    constraints.gridy = 3;
    add(energy, constraints);

    JPanel cardGroup = new JPanel(new FlowLayout(FlowLayout.LEFT));
    cardGroupCb.setPreferredSize(new Dimension(50, 23));
    cardGroup.add(new JLabel("战斗前切换第"));
    cardGroup.add(cardGroupCb);
    cardGroup.add(new JLabel("卡组"));
    cardGroup.add(dynamicCardGroupCb);
    dynamicCardGroupCb.addItemListener(new CheckboxListener() {
      @Override
      public void check(boolean state) {
        if(state) {
          if(dynamicCardGroup.isEmpty()) newDynamicCardGroup();
        } else {
          dynamicCardGroup = "";
        }
      }
    });
    JButton dynamicCardGroupSelector = new JButton("超动态卡组");
    dynamicCardGroupSelector.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        newDynamicCardGroup();
      }
    });
    cardGroup.add(dynamicCardGroupSelector);
    constraints.gridy = 4;
    add(cardGroup, constraints);

    JPanel exitCardGroup = new JPanel(new FlowLayout(FlowLayout.LEFT));
    exitCardGroupCb.setPreferredSize(new Dimension(50, 23));
    exitCardGroup.add(new JLabel("战斗后切换第"));
    exitCardGroup.add(exitCardGroupCb);
    exitCardGroup.add(new JLabel("卡组"));
    exitDynamicCardGroupCb.addItemListener(new CheckboxListener() {
      @Override
      public void check(boolean state) {
        if(state) {
          if(exitDynamicCardGroup.isEmpty()) newDynamicExitCardGroup();
        } else {
          exitDynamicCardGroup = "";
        }
      }
    });
    exitCardGroup.add(exitDynamicCardGroupCb);
    JButton exitDynamicCardGroupSelector = new JButton("超动态卡组");
    exitDynamicCardGroupSelector.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        newDynamicExitCardGroup();
      }
    });
    exitCardGroup.add(exitDynamicCardGroupSelector);
    constraints.gridy = 5;
    add(exitCardGroup, constraints);

    JPanel failureRetry = new JPanel(new FlowLayout(FlowLayout.LEFT));
    retryTf.setPreferredSize(new Dimension(50, 23));
    failureRetry.add(new JLabel("连续战斗失败"));
    failureRetry.add(retryTf);
    failureRetry.add(new JLabel("次后放弃迷宫"));
    constraints.gridy = 6;
    add(failureRetry, constraints);

    JPanel antiDetect = new JPanel(new FlowLayout(FlowLayout.LEFT));
    minDelayTf.setPreferredSize(new Dimension(50, 23));
    maxDelayTf.setPreferredSize(new Dimension(50, 23));
    antiDetect.add(new JLabel("战斗间隔"));
    antiDetect.add(minDelayTf);
    antiDetect.add(new JLabel("至"));
    antiDetect.add(maxDelayTf);
    antiDetect.add(new JLabel("毫秒"));
    antiDetect.add(shuffle);
    constraints.gridy = 7;
    add(antiDetect, constraints);

    constraints.weighty = 1;
    constraints.gridy = 8;
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
    constraints.gridy = 9;
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
    constraints.insets = new Insets(0, 0, 5, 0);
    constraints.gridy = 10;
    add(schedule, constraints);
  }

  private void newDynamicCardGroup() {
    appFrame.maskAction(new Runnable() {
      @Override
      public void run() {
        new DynamicCardGroupEditor(KEY, DynamicCardGroupEditor.BEFORE_BATTLE, assetsService, username, infoService.getUserCards(username), infoService.getUserRune(username), infoService.getUserInfo(username).getLeaderShip()) {
          @Override
          public String loadDynamicCardGroup() {
            return dynamicCardGroup;
          }

          @Override
          public void setDynamicCardGroup(String cg) {
            dynamicCardGroup = cg;
            dynamicCardGroupCb.setSelected(!cg.isEmpty());
            appFrame.unmask();
          }
        };
      }
    }, false);
  }

  private void newDynamicExitCardGroup() {
    appFrame.maskAction(new Runnable() {
      @Override
      public void run() {
        new DynamicCardGroupEditor(KEY, DynamicCardGroupEditor.AFTER_BATTLE, assetsService, username, infoService.getUserCards(username), infoService.getUserRune(username), infoService.getUserInfo(username).getLeaderShip()) {
          @Override
          public String loadDynamicCardGroup() {
            return exitDynamicCardGroup;
          }

          @Override
          public void setDynamicCardGroup(String cg) {
            exitDynamicCardGroup = cg;
            exitDynamicCardGroupCb.setSelected(!cg.isEmpty());
            appFrame.unmask();
          }
        };
      }
    }, false);
  }

  @Override
  protected void save(MazeSetting setting) {
    setting.setMaze2(maze2.isSelected());
    setting.setMaze3(maze3.isSelected());
    setting.setMaze4(maze4.isSelected());
    setting.setMaze5(maze5.isSelected());
    setting.setMaze6(maze6.isSelected());
    setting.setMaze7(maze7.isSelected());
    setting.setMaze8(maze8.isSelected());
    setting.setDesc(desc.isSelected());
    setting.setBudget(budgetTf.getIntegerValue());
    setting.setCardGroup(cardGroupCb.getCardGroup());
    setting.setDynamicCardGroup(dynamicCardGroup);
    setting.setExitCardGroup(exitCardGroupCb.getCardGroup());
    setting.setDynamicExitCardGroup(exitDynamicCardGroup);
    setting.setRetry(retryTf.getIntegerValue());
    setting.setMinDelay(minDelayTf.getIntegerValue());
    setting.setMaxDelay(maxDelayTf.getIntegerValue());
    setting.setShuffle(shuffle.isSelected());
    setting.setHour(hour.getIntegerValue());
    setting.setMinute(minute.getIntegerValue());
    setting.setAuto(auto.isSelected());
  }

  @Override
  protected void load(MazeSetting setting) {
    maze2.setSelected(setting.isMaze2());
    maze3.setSelected(setting.isMaze3());
    maze4.setSelected(setting.isMaze4());
    maze5.setSelected(setting.isMaze5());
    maze6.setSelected(setting.isMaze6());
    maze7.setSelected(setting.isMaze7());
    maze8.setSelected(setting.isMaze8());
    asc.setSelected(!setting.isDesc());
    desc.setSelected(setting.isDesc());
    budgetTf.setIntegerValue(setting.getBudget());
    cardGroupCb.setCardGroup(setting.getCardGroup());
    dynamicCardGroup = setting.getDynamicCardGroup();
    dynamicCardGroupCb.setSelected(!setting.getDynamicCardGroup().isEmpty());
    exitCardGroupCb.setCardGroup(setting.getExitCardGroup());
    exitDynamicCardGroup = setting.getDynamicExitCardGroup();
    exitDynamicCardGroupCb.setSelected(!setting.getDynamicExitCardGroup().isEmpty());
    retryTf.setIntegerValue(setting.getRetry());
    minDelayTf.setIntegerValue(setting.getMinDelay());
    maxDelayTf.setIntegerValue(setting.getMaxDelay());
    shuffle.setSelected(setting.isShuffle());
    hour.setIntegerValue(setting.getHour());
    minute.setIntegerValue(setting.getMinute());
    auto.setSelected(setting.isAuto());
  }

  @Override
  protected void oneKeyStart(String username, MazeSetting setting) {
    mazeService.start(username, setting, new ReportWindow(KEY, username));
  }

}
