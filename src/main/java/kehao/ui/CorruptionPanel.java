package kehao.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.model.CorruptionSetting;
import kehao.service.AssetsService;
import kehao.service.CorruptionService;
import kehao.ui.widget.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CorruptionPanel extends FunctionalInterfacePanel<CorruptionSetting> {

  public static String KEY = "反攻";

  private JCheckBox map1 = new JCheckBox("1");
  private JCheckBox map2 = new JCheckBox("2");
  private JCheckBox map3 = new JCheckBox("3");
  private JCheckBox map4 = new JCheckBox("4");
  private JCheckBox map5 = new JCheckBox("5");
  private JCheckBox map6 = new JCheckBox("6");
  private JCheckBox map7 = new JCheckBox("7");
  private JCheckBox map8 = new JCheckBox("8");
  private JCheckBox map9 = new JCheckBox("9");
  private JCheckBox map10 = new JCheckBox("10");
  private JCheckBox map11 = new JCheckBox("11");
  private JCheckBox map12 = new JCheckBox("12");
  private NumberField energyTf = new NumberField();
  private CardGroupComboBox cardGroupCb = new CardGroupComboBox();
  private String dynamicCardGroup;
  private CardGroupComboBox exitCardGroupCb = new CardGroupComboBox();
  private String exitDynamicCardGroup;
  private NumberField retryTf = new NumberField(20);
  private NumberField hour = new NumberField(23);
  private NumberField minute = new NumberField(59);
  private JCheckBox auto = new JCheckBox("自动执行");

  @Autowired
  private CorruptionService corruptionService;
  @Autowired
  private ExecutorService executorService;
  @Autowired
  private AssetsService assetsService;
  @Autowired
  private InfoService infoService;

  @PostConstruct
  public void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1;

    JPanel maps = new JPanel(new GridLayout(0, 6));
    maps.add(map1);
    maps.add(map2);
    maps.add(map3);
    maps.add(map4);
    maps.add(map5);
    maps.add(map6);
    maps.add(map7);
    maps.add(map8);
    maps.add(map9);
    maps.add(map10);
    maps.add(map11);
    maps.add(map12);
    add(maps, constraints);

    JPanel energy = new JPanel(new FlowLayout());
    energyTf.setPreferredSize(new Dimension(50, 23));
    energy.add(new JLabel("当选中地图出现反攻且行动力大于"));
    energy.add(energyTf);
    energy.add(new JLabel("时"));
    constraints.gridy = 1;
    add(energy, constraints);

    JPanel cardGroup = new JPanel(new FlowLayout(FlowLayout.LEFT));
    cardGroupCb.setPreferredSize(new Dimension(50, 23));
    cardGroup.add(new JLabel("战斗前切换第"));
    cardGroup.add(cardGroupCb);
    cardGroup.add(new JLabel("卡组"));
    JButton dynamicCardGroupSelector = new JButton("超动态卡组");
    dynamicCardGroupSelector.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new DynamicCardGroupEditor(KEY, DynamicCardGroupEditor.BEFORE_BATTLE, assetsService, username, infoService.getUserCards(username), infoService.getUserRune(username), infoService.getUserInfo(username).getLeaderShip()) {
          @Override
          public String loadDynamicCardGroup() {
            return dynamicCardGroup;
          }

          @Override
          public void setDynamicCardGroup(String cg) {
            dynamicCardGroup = cg;
          }
        };
      }
    });
    cardGroup.add(dynamicCardGroupSelector);
    constraints.gridy = 3;
    add(cardGroup, constraints);

    JPanel exitCardGroup = new JPanel(new FlowLayout(FlowLayout.LEFT));
    exitCardGroupCb.setPreferredSize(new Dimension(50, 23));
    exitCardGroup.add(new JLabel("战斗后切换第"));
    exitCardGroup.add(exitCardGroupCb);
    exitCardGroup.add(new JLabel("卡组"));
    JButton exitDynamicCardGroupSelector = new JButton("超动态卡组");
    exitDynamicCardGroupSelector.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new DynamicCardGroupEditor(KEY, DynamicCardGroupEditor.AFTER_BATTLE, assetsService, username, infoService.getUserCards(username), infoService.getUserRune(username), infoService.getUserInfo(username).getLeaderShip()) {
          @Override
          public String loadDynamicCardGroup() {
            return exitDynamicCardGroup;
          }

          @Override
          public void setDynamicCardGroup(String cg) {
            exitDynamicCardGroup = cg;
          }
        };
      }
    });
    exitCardGroup.add(exitDynamicCardGroupSelector);
    constraints.gridy = 4;
    add(exitCardGroup, constraints);

    JPanel failureRetry = new JPanel(new FlowLayout(FlowLayout.LEFT));
    retryTf.setPreferredSize(new Dimension(50, 23));
    failureRetry.add(new JLabel("连续战斗失败"));
    failureRetry.add(retryTf);
    failureRetry.add(new JLabel("次后放弃关卡"));
    constraints.gridy = 5;
    add(failureRetry, constraints);

    constraints.weighty = 1;
    constraints.gridy = 6;
    add(new JLabel(), constraints);

    JButton start = new JButton("一键执行");
    start.addActionListener(new AsyncActionListener(executorService) {
      @Override
      public void run() {
        oneKeyStart();
      }
    });start.setPreferredSize(new Dimension(120, 50));
    constraints.weighty = 0;
    constraints.gridy = 7;
    constraints.insets = new Insets(5, 20, 5, 20);
    add(start, constraints);

    JPanel schedule = new JPanel(new FlowLayout());
    schedule.add(new JLabel("每天"));
    hour.setPreferredSize(new Dimension(50, 23));
    schedule.add(hour);
    schedule.add(new JLabel("时"));
    minute.setPreferredSize(new Dimension(50, 23));
    schedule.add(minute);
    schedule.add(new JLabel("分"));
    schedule.add(auto);
    constraints.gridy = 8;
    constraints.insets = new Insets(0, 0, 5, 0);
    add(schedule, constraints);
  }

  @Override
  protected void save(CorruptionSetting setting) {
    setting.setMap1(map1.isSelected());
    setting.setMap2(map2.isSelected());
    setting.setMap3(map3.isSelected());
    setting.setMap4(map4.isSelected());
    setting.setMap5(map5.isSelected());
    setting.setMap6(map6.isSelected());
    setting.setMap7(map7.isSelected());
    setting.setMap8(map8.isSelected());
    setting.setMap9(map9.isSelected());
    setting.setMap10(map10.isSelected());
    setting.setMap11(map11.isSelected());
    setting.setMap12(map12.isSelected());
    setting.setEnergy(energyTf.getIntegerValue());
    setting.setCardGroup(cardGroupCb.getCardGroup());
    setting.setDynamicCardGroup(dynamicCardGroup);
    setting.setExitCardGroup(exitCardGroupCb.getCardGroup());
    setting.setDynamicCardGroup(exitDynamicCardGroup);
    setting.setRetry(retryTf.getIntegerValue());
    setting.setHour(hour.getIntegerValue());
    setting.setMinute(minute.getIntegerValue());
    setting.setAuto(auto.isSelected());
  }

  @Override
  protected void load(CorruptionSetting setting) {
    map1.setSelected(setting.isMap1());
    map2.setSelected(setting.isMap2());
    map3.setSelected(setting.isMap3());
    map4.setSelected(setting.isMap4());
    map5.setSelected(setting.isMap5());
    map6.setSelected(setting.isMap6());
    map7.setSelected(setting.isMap7());
    map8.setSelected(setting.isMap8());
    map9.setSelected(setting.isMap9());
    map10.setSelected(setting.isMap10());
    map11.setSelected(setting.isMap11());
    map12.setSelected(setting.isMap12());
    energyTf.setIntegerValue(setting.getEnergy());
    cardGroupCb.setCardGroup(setting.getCardGroup());
    dynamicCardGroup = setting.getDynamicCardGroup();
    exitCardGroupCb.setCardGroup(setting.getExitCardGroup());
    exitDynamicCardGroup = setting.getDynamicExitCardGroup();
    retryTf.setIntegerValue(setting.getRetry());
    hour.setIntegerValue(setting.getHour());
    minute.setIntegerValue(setting.getMinute());
    auto.setSelected(setting.isAuto());
  }

  @Override
  protected void oneKeyStart(String username, CorruptionSetting setting) {
    corruptionService.start(username, setting, new ReportWindow(KEY, username));
  }
}
