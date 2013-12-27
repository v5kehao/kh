package kehao.ui;

import java.awt.*;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.service.JourneyService;
import kehao.ui.widget.JourneyBossDifficultyComboBox;
import kehao.ui.widget.NumberField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JourneyWindow extends JFrame {
  private JTextArea beneficiaryAccountsTa = new JTextArea();
  private JTextArea filterAccountsTa = new JTextArea();
  private JTextArea providerAccountsTa = new JTextArea();
  private NumberField beneficiaryAccountRefreshIntervalNf = new NumberField(1, 10800);
  private NumberField filterAccountRefreshIntervalNf = new NumberField(1, 10800);
  private JourneyBossDifficultyComboBox minDifficultyCb = new JourneyBossDifficultyComboBox();
  private JCheckBox beneficiaryAccountExploreCb = new JCheckBox("每");
  private NumberField beneficiaryAccountExploreIntervalNf = new NumberField(1, 480);
  private JCheckBox filterAccountExploreCb = new JCheckBox("每");
  private NumberField filterAccountExploreIntervalNf = new NumberField(1, 480);
  private NumberField providerAccountExploreIntervalNf = new NumberField(1, 480);
  private JRadioButton resetCoolDownRb = new JRadioButton("晶钻重置冷却时间");
  private JRadioButton stopBattleRb = new JRadioButton("停止战斗");
  private NumberField beneficiaryAccountMaxCoolDownNf = new NumberField(1, 60);
  private NumberField filterAccountMaxCoolDownNf = new NumberField(1, 60);
  private NumberField providerAccountMaxCoolDownNf = new NumberField(1, 60);
  @Autowired
  private JourneyService journeyService;

  @PostConstruct
  public void init() {
    setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    add(new JLabel("受益帐号"), constraints);
    constraints.gridy = 2;
    add(new JLabel("过滤帐号"), constraints);
    constraints.gridy = 4;
    add(new JLabel("供给帐号"), constraints);
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.gridy = 1;
    add(beneficiaryAccountsTa, constraints);
    constraints.gridy = 3;
    add(filterAccountsTa, constraints);
    constraints.gridy = 5;
    add(providerAccountsTa, constraints);

    JPanel filterSettings = new JPanel(new FlowLayout());
    filterSettings.add(new JLabel("过滤"));
    filterSettings.add(minDifficultyCb);
    filterSettings.add(new JLabel("及以下难度"));
    filterSettings.add(beneficiaryAccountExploreCb);
    filterSettings.add(filterAccountExploreCb);
    constraints.weighty = 0;
    constraints.gridy = 6;
    add(filterSettings, constraints);

    JPanel beneficiaryAccountsSettings = new JPanel(new GridLayout(0, 1));
    beneficiaryAccountsSettings.setBorder(BorderFactory.createTitledBorder("受益帐号"));
    JPanel beneficiaryExploreSettings = new JPanel(new FlowLayout());
    beneficiaryExploreSettings.add(beneficiaryAccountExploreCb);
    beneficiaryExploreSettings.add(beneficiaryAccountExploreIntervalNf);
    beneficiaryExploreSettings.add(new JLabel("分钟自动进行探索"));
    beneficiaryAccountsSettings.add(beneficiaryExploreSettings);
    JPanel beneficiaryRefreshSettings = new JPanel(new FlowLayout());
    beneficiaryRefreshSettings.add(new JLabel("每"));
    beneficiaryRefreshSettings.add(beneficiaryAccountRefreshIntervalNf);
    beneficiaryRefreshSettings.add(new JLabel("秒刷新列表"));
    beneficiaryAccountsSettings.add(beneficiaryRefreshSettings);
    JPanel beneficiaryCoolDownSettings = new JPanel(new FlowLayout());
    beneficiaryCoolDownSettings.add(new JLabel("冷却时间超过"));
    beneficiaryCoolDownSettings.add(beneficiaryAccountMaxCoolDownNf);
    beneficiaryCoolDownSettings.add(new JLabel("分钟后"));
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(resetCoolDownRb);
    buttonGroup.add(stopBattleRb);
    beneficiaryCoolDownSettings.add(resetCoolDownRb);
    beneficiaryCoolDownSettings.add(stopBattleRb);
    beneficiaryAccountsSettings.add(beneficiaryCoolDownSettings);
    constraints.gridy = 7;
    add(beneficiaryAccountsSettings, constraints);

    JPanel filterAccountsSettings = new JPanel(new GridLayout(0, 1));
    filterAccountsSettings.setBorder(BorderFactory.createTitledBorder("过滤帐号"));
    JPanel filterExploreSettings = new JPanel(new FlowLayout());
    filterExploreSettings.add(filterAccountExploreCb);
    filterExploreSettings.add(filterAccountExploreIntervalNf);
    filterExploreSettings.add(new JLabel("分钟自动进行探索"));
    filterAccountsSettings.add(filterExploreSettings);
    JPanel filterRefreshSettings = new JPanel(new FlowLayout());
    filterRefreshSettings.add(new JLabel("每"));
    filterRefreshSettings.add(filterAccountRefreshIntervalNf);
    filterRefreshSettings.add(new JLabel("秒刷新列表"));
    filterAccountsSettings.add(filterRefreshSettings);
    JPanel filterCoolDownSettings = new JPanel(new FlowLayout());
    filterCoolDownSettings.add(new JLabel("冷却时间超过"));
    filterCoolDownSettings.add(filterAccountMaxCoolDownNf);
    filterCoolDownSettings.add(new JLabel("分钟后停止战斗"));
    filterAccountsSettings.add(filterCoolDownSettings);
    constraints.gridy = 8;
    add(filterAccountsSettings, constraints);

    JPanel providerAccountSettings = new JPanel(new GridLayout(0, 1));
    providerAccountSettings.setBorder(BorderFactory.createTitledBorder("供给帐号"));
    JPanel providerExploreSettings = new JPanel(new FlowLayout());
    providerExploreSettings.add(new JLabel("每"));
    providerExploreSettings.add(providerAccountExploreIntervalNf);
    providerExploreSettings.add(new JLabel("分钟进行自动探索"));
    providerAccountSettings.add(providerExploreSettings);
    JPanel providerCoolDownSettings = new JPanel(new FlowLayout());
    providerCoolDownSettings.add(new JLabel("冷却时间超过"));
    providerCoolDownSettings.add(providerAccountMaxCoolDownNf);
    providerCoolDownSettings.add(new JLabel("分钟后停止战斗"));
    providerAccountSettings.add(providerCoolDownSettings);
    constraints.gridy = 9;
    add(providerAccountSettings, constraints);

    setTitle("-");
    setIconImage(AppFrame.FAVICON);pack();
    setLocationRelativeTo(AppFrame.LOCATION);
  }

}
