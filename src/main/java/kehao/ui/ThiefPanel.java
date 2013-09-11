package kehao.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.model.ThiefSetting;
import kehao.service.AssetsService;
import kehao.service.ThiefService;
import kehao.ui.widget.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ThiefPanel extends FunctionalInterfacePanel<ThiefSetting> {

  public static String KEY = "盗贼";

  private CardGroupComboBox cardGroupCb = new CardGroupComboBox();
  private String dynamicCardGroup;
  private CardGroupComboBox exitCardGroupCb = new CardGroupComboBox();
  private String exitDynamicCardGroup;
  private JCheckBox self = new JCheckBox("优先当前账号盗贼");
  private JCheckBox elite = new JCheckBox("优先精英盗贼");
  private JCheckBox lowHp = new JCheckBox("优先濒死盗贼");
  private NumberField minute = new NumberField(120);
  private JCheckBox auto = new JCheckBox("自动刷新通缉令");

  @Autowired
  private ThiefService thiefService;
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

    add(self, constraints);
    constraints.gridy = 1;
    add(elite, constraints);
    constraints.gridy = 2;
    add(lowHp, constraints);

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

    constraints.weighty = 1;
    constraints.gridy = 5;
    add(new JLabel(), constraints);

    JButton start = new JButton("一键执行");
    start.addActionListener(new AsyncActionListener(executorService) {
      @Override
      public void run() {
        oneKeyStart();
      }
    });start.setPreferredSize(new Dimension(120, 50));
    constraints.weighty = 0;
    constraints.gridy = 6;
    constraints.insets = new Insets(5, 20, 5, 20);
    add(start, constraints);

    JPanel schedule = new JPanel(new FlowLayout());
    schedule.add(new JLabel("每"));
    minute.setPreferredSize(new Dimension(50, 23));
    schedule.add(minute);
    schedule.add(new JLabel("分钟"));
    schedule.add(auto);
    constraints.insets = new Insets(0, 0, 5, 0);
    constraints.gridy = 7;
    add(schedule, constraints);
  }

  @Override
  protected void save(ThiefSetting setting) {
    setting.setFavourSelf(self.isSelected());
    setting.setFavourElite(elite.isSelected());
    setting.setFavourLowHp(lowHp.isSelected());
    setting.setCardGroup(cardGroupCb.getCardGroup());
    setting.setDynamicCardGroup(dynamicCardGroup);
    setting.setExitCardGroup(exitCardGroupCb.getCardGroup());
    setting.setDynamicExitCardGroup(exitDynamicCardGroup);
    setting.setMinute(minute.getIntegerValue());
    setting.setAuto(auto.isSelected());
  }

  @Override
  protected void load(ThiefSetting setting) {
    self.setSelected(setting.isFavourSelf());
    elite.setSelected(setting.isFavourElite());
    lowHp.setSelected(setting.isFavourLowHp());
    cardGroupCb.setCardGroup(setting.getCardGroup());
    dynamicCardGroup = setting.getDynamicCardGroup();
    exitCardGroupCb.setCardGroup(setting.getExitCardGroup());
    exitDynamicCardGroup = setting.getDynamicExitCardGroup();
    minute.setIntegerValue(setting.getMinute());
    auto.setSelected(setting.isAuto());
  }

  @Override
  protected void oneKeyStart(String username, ThiefSetting setting) {
    thiefService.start(username, setting, new ReportWindow(KEY, username));
  }
}
