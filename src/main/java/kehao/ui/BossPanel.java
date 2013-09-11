package kehao.ui;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.model.BossSetting;
import kehao.service.BossService;
import kehao.ui.widget.AsyncActionListener;
import kehao.ui.widget.CardGroupComboBox;
import kehao.ui.widget.ReportWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BossPanel extends FunctionalInterfacePanel<BossSetting> {

  public static String KEY = "魔神";

  private CardGroupComboBox boss1Cb = new CardGroupComboBox();
  private CardGroupComboBox boss2Cb = new CardGroupComboBox();
  private CardGroupComboBox boss3Cb = new CardGroupComboBox();
  private CardGroupComboBox boss4Cb = new CardGroupComboBox();
  private CardGroupComboBox boss5Cb = new CardGroupComboBox();
  private CardGroupComboBox boss6Cb = new CardGroupComboBox();
  private JCheckBox time13 = new JCheckBox("13:00");
  private JCheckBox time21 = new JCheckBox("21:00");
  private JCheckBox auto = new JCheckBox("自动执行");

  @Autowired
  private BossService bossService;
  @Autowired
  private ExecutorService executorService;

  @PostConstruct
  public void init() {
    setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1;

    JPanel cardGroup = new JPanel(new GridLayout(0, 2));
    cardGroup.setBorder(BorderFactory.createTitledBorder("对应卡组"));

    JPanel boss1 = new JPanel(new FlowLayout());
    JLabel boss1L = new JLabel("复仇女神");
    boss1Cb.setPreferredSize(new Dimension(50, 23));
    boss1.add(boss1L);
    boss1.add(boss1Cb);
    cardGroup.add(boss1);

    JPanel boss2 = new JPanel(new FlowLayout());
    JLabel boss2L = new JLabel("邪龙之神");
    boss2Cb.setPreferredSize(new Dimension(50, 23));
    boss2.add(boss2L);
    boss2.add(boss2Cb);
    cardGroup.add(boss2);

    JPanel boss3 = new JPanel(new FlowLayout());
    JLabel boss3L = new JLabel("噩梦之主");
    boss3Cb.setPreferredSize(new Dimension(50, 23));
    boss3.add(boss3L);
    boss3.add(boss3Cb);
    cardGroup.add(boss3);

    JPanel boss4 = new JPanel(new FlowLayout());
    JLabel boss4L = new JLabel("毁灭之神");
    boss4Cb.setPreferredSize(new Dimension(50, 23));
    boss4.add(boss4L);
    boss4.add(boss4Cb);
    cardGroup.add(boss4);

    JPanel boss5 = new JPanel(new FlowLayout());
    JLabel boss5L = new JLabel("深渊影魔");
    boss5Cb.setPreferredSize(new Dimension(50, 23));
    boss5.add(boss5L);
    boss5.add(boss5Cb);
    cardGroup.add(boss5);

    JPanel boss6 = new JPanel(new FlowLayout());
    JLabel boss6L = new JLabel("万蛛之后");
    boss6Cb.setPreferredSize(new Dimension(50, 23));
    boss6.add(boss6L);
    boss6.add(boss6Cb);
    cardGroup.add(boss6);
    add(cardGroup, constraints);

    constraints.weighty = 1;
    constraints.gridy = 1;
    add(new JLabel(), constraints);

    JButton start = new JButton("一键执行");
    start.addActionListener(new AsyncActionListener(executorService) {
      @Override
      public void run() {
        oneKeyStart();
      }
    });start.setPreferredSize(new Dimension(120, 50));
    constraints.weighty = 0;
    constraints.gridy = 2;
    constraints.insets = new Insets(5, 20, 5, 20);
    add(start, constraints);

    JPanel schedule = new JPanel(new FlowLayout());
    schedule.add(new JLabel("每天"));
    schedule.add(time13);
    schedule.add(time21);
    schedule.add(auto);
    constraints.insets = new Insets(0, 0, 5, 0);
    constraints.gridy = 3;
    add(schedule, constraints);
  }

  @Override
  protected void save(BossSetting setting) {
    setting.setBoss1(boss1Cb.getCardGroup());
    setting.setBoss2(boss2Cb.getCardGroup());
    setting.setBoss3(boss3Cb.getCardGroup());
    setting.setBoss4(boss4Cb.getCardGroup());
    setting.setBoss5(boss5Cb.getCardGroup());
    setting.setBoss6(boss6Cb.getCardGroup());
    setting.setTime13(time13.isSelected());
    setting.setTime21(time21.isSelected());
    setting.setAuto(auto.isSelected());
  }

  @Override
  protected void load(BossSetting setting) {
    boss1Cb.setCardGroup(setting.getBoss1());
    boss2Cb.setCardGroup(setting.getBoss2());
    boss3Cb.setCardGroup(setting.getBoss3());
    boss4Cb.setCardGroup(setting.getBoss4());
    boss5Cb.setCardGroup(setting.getBoss5());
    boss6Cb.setCardGroup(setting.getBoss6());
    time13.setSelected(setting.isTime13());
    time21.setSelected(setting.isTime21());
    auto.setSelected(setting.isAuto());
  }

  @Override
  protected void oneKeyStart(String username, BossSetting setting) {
    bossService.start(username, setting, new ReportWindow(KEY, username));
  }
}
