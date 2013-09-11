package kehao.ui;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.model.LegionSetting;
import kehao.service.LegionService;
import kehao.ui.widget.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LegionPanel extends FunctionalInterfacePanel<LegionSetting> {
  public static String KEY = "军团";

  private NumberField hour = new NumberField(23);
  private NumberField minute = new NumberField(59);
  private NumberField amountTf = new NumberField();
  private LegionTechComboBox techCb = new LegionTechComboBox();
  private JCheckBox auto = new JCheckBox("自动执行");

  @Autowired
  private LegionService legionService;
  @Autowired
  private ExecutorService executorService;

  @PostConstruct
  public void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1;

    JPanel donate = new JPanel(new FlowLayout());
    amountTf.setPreferredSize(new Dimension(80, 23));
    donate.add(new JLabel("捐赠"));
    donate.add(amountTf);
    donate.add(new JLabel("金币"));
    donate.add(new JLabel("至"));
    donate.add(techCb);
    add(donate, constraints);

    constraints.weighty = 1;
    constraints.gridy = 1;
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
    constraints.gridy = 2;
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
    constraints.gridy = 3;
    constraints.insets = new Insets(0, 0, 5, 0);
    add(schedule, constraints);
  }

  @Override
  protected void save(LegionSetting setting) {
    setting.setAmount(amountTf.getIntegerValue());
    setting.setTech(techCb.getTech());
    setting.setHour(hour.getIntegerValue());
    setting.setMinute(minute.getIntegerValue());
    setting.setAuto(auto.isSelected());
  }

  @Override
  protected void load(LegionSetting setting) {
    amountTf.setIntegerValue(setting.getAmount());
    techCb.setTech(setting.getTech());
    hour.setIntegerValue(setting.getHour());
    minute.setIntegerValue(setting.getMinute());
    auto.setSelected(setting.isAuto());
  }

  @Override
  protected void oneKeyStart(String username, LegionSetting setting) {
    legionService.start(username, setting, new ReportWindow(KEY, username));
  }
}
