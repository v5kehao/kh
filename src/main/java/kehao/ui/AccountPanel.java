package kehao.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import kehao.io.ResourcesIO;
import kehao.model.UserSetting;
import kehao.service.AdminService;
import kehao.service.SchedulingService;
import kehao.ui.widget.BorderlessButton;
import kehao.ui.widget.ReadOnlyGrid;
import kehao.ui.widget.ReadOnlyTableModel;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountPanel extends JPanel {

  private ReadOnlyGrid grid = new ReadOnlyGrid(new String[]{"用户名", "昵称", "服务器"});
  private int selection = -1;

  @Autowired
  private AdminService adminService;
  @Autowired
  private CenterPanel centerPanel;
  @Autowired
  private SchedulingService schedulingService;

  private String username;
  private JCheckBox backgroundSchedule;

  @PostConstruct
  public void init() {
    setLayout(new BorderLayout());

    JMenuBar menu = new JMenuBar();
    final JButton delete = new BorderlessButton(ResourcesIO.getIcon(ResourcesIO.DELETE2));
    delete.setEnabled(false);
    delete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String username = (String)grid.getValueAt(selection, 0);
        adminService.removeAccount(username);
        grid.removeRow(selection);
      }
    });
    menu.add(delete);
    final JButton save = new BorderlessButton(ResourcesIO.getIcon(ResourcesIO.DISK_BLUE));
    save.setEnabled(false);
    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        centerPanel.save();
        try {
          schedulingService.scheduleUser(username);
        } catch(SchedulerException se) {
          se.printStackTrace();
        }
      }
    });
    menu.add(save);
    add(menu, BorderLayout.NORTH);

    grid.onSelect(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        selection = ((DefaultListSelectionModel)e.getSource()).getMinSelectionIndex();
        if(selection == -1) {
          delete.setEnabled(false);
          save.setEnabled(false);
          centerPanel.unload();
        } else {
          delete.setEnabled(true);
          save.setEnabled(true);
          username = (String)grid.getValueAt(selection, 0);
          centerPanel.load(username);
        }
      }
    });
    add(new JScrollPane(grid), BorderLayout.CENTER);
    load();
  }

  public void load() {
    grid.clear();
    for(UserSetting userSetting : adminService.users()) {
      addAccount(userSetting);
    }
  }

  public void addAccount(UserSetting userSetting) {
    grid.addRow(new String[]{userSetting.getUsername(), userSetting.getNickname(), userSetting.getServer()});
  }

  public void checkBackgroundSchedule() {

  }

}
