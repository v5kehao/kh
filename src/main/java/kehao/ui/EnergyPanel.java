package kehao.ui;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.model.EnergySetting;
import kehao.service.EnergyService;
import kehao.ui.widget.AsyncActionListener;
import kehao.ui.widget.NumberField;
import kehao.ui.widget.ReportWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EnergyPanel extends FunctionalInterfacePanel<EnergySetting> {

  public static String KEY = "体力";

  private JCheckBox returnCb = new JCheckBox("优先返赠");
  private JCheckBox legionCb = new JCheckBox("优先赠予相同军团好友");
  private JCheckBox activeCb = new JCheckBox("优先赠予竞技场排名靠前好友");
  private NumberField hour = new NumberField(24);
  private NumberField minute = new NumberField(59);
  private JCheckBox auto = new JCheckBox("自动执行");

  @Autowired
  private EnergyService energyService;
  @Autowired
  private ExecutorService executorService;

  @PostConstruct
  public void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1;

    add(returnCb, constraints);
    constraints.gridy = 1;
    add(legionCb, constraints);
    constraints.gridy = 2;
    add(activeCb, constraints);
    constraints.gridy = 3;

    constraints.weighty = 1;
    constraints.gridy = 4;
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
    constraints.gridy = 5;
    constraints.insets = new Insets(5, 20, 5, 20);
    add(start, constraints);

    JPanel schedule = new JPanel(new FlowLayout());
    JLabel hourL = new JLabel("每");
    schedule.add(hourL);
    hour.setPreferredSize(new Dimension(50, 23));
    schedule.add(hour);
    JLabel hourR = new JLabel("小时");
    schedule.add(hourR);
    JLabel minuteL = new JLabel("第");
    schedule.add(minuteL);
    minute.setPreferredSize(new Dimension(50, 23));
    schedule.add(minute);
    JLabel minuteR = new JLabel("分钟");
    schedule.add(minuteR);
    schedule.add(auto);
    constraints.insets = new Insets(0, 0, 5, 0);
    constraints.gridy = 6;
    add(schedule, constraints);
  }

  @Override
  protected void save(EnergySetting setting) {
    setting.setFavourReturn(returnCb.isSelected());
    setting.setFavourLegion(legionCb.isSelected());
    setting.setFavourRank(activeCb.isSelected());
    setting.setHour(hour.getIntegerValue());
    setting.setMinute(minute.getIntegerValue());
    setting.setAuto(auto.isSelected());
  }

  @Override
  protected void load(EnergySetting setting) {
    returnCb.setSelected(setting.isFavourReturn());
    legionCb.setSelected(setting.isFavourLegion());
    activeCb.setSelected(setting.isFavourRank());
    hour.setIntegerValue(setting.getHour());
    minute.setIntegerValue(setting.getMinute());
    auto.setSelected(setting.isAuto());
  }

  @Override
  protected void oneKeyStart(String username, EnergySetting setting) {
    energyService.start(username, setting, new ReportWindow(KEY, username));
  }
}
