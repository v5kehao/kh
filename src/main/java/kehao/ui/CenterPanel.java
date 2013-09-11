package kehao.ui;

import java.awt.*;
import javax.annotation.PostConstruct;
import javax.swing.*;

import kehao.model.UserSetting;
import kehao.service.AdminService;
import kehao.ui.widget.SwingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CenterPanel extends JTabbedPane {

  @Autowired
  private AdminService adminService;
  @Autowired
  private BossPanel bossPanel;
  @Autowired
  private CorruptionPanel corruptionPanel;
  @Autowired
  private EnergyPanel energyPanel;
  @Autowired
  private ExplorePanel explorePanel;
  @Autowired
  private LegionPanel legionPanel;
  @Autowired
  private MazePanel mazePanel;
  @Autowired
  private ThiefPanel thiefPanel;

  @PostConstruct
  public void init() {
    setPreferredSize(new Dimension(400, 500));
    addTab(MazePanel.KEY, mazePanel);
    addTab(EnergyPanel.KEY, energyPanel);
    addTab(ExplorePanel.KEY, explorePanel);
    addTab(CorruptionPanel.KEY, corruptionPanel);
    addTab(LegionPanel.KEY, legionPanel);
    addTab(ThiefPanel.KEY, thiefPanel);
    addTab(BossPanel.KEY, bossPanel);
    unload();
  }

  public void load(String username) {
    UserSetting user = adminService.user(username);
    bossPanel.load(username, user.getBossSetting());
    corruptionPanel.load(username, user.getCorruptionSetting());
    energyPanel.load(username, user.getEnergySetting());
    explorePanel.load(username, user.getExploreSetting());
    legionPanel.load(username, user.getLegionSetting());
    mazePanel.load(username, user.getMazeSetting());
    thiefPanel.load(username, user.getThiefSetting());

    SwingUtils.setEnabled(this, true);
  }

  public void save() {
    bossPanel.save();
    corruptionPanel.save();
    energyPanel.save();
    explorePanel.save();
    legionPanel.save();
    mazePanel.save();
    thiefPanel.save();

    adminService.save();
  }

  public void unload() {
    SwingUtils.setEnabled(this, false);

  }
}
