package kehao.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import kehao.model.UserSetting;
import kehao.service.AdminService;
import kehao.ui.widget.AsyncActionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminPanel extends JPanel {

  @Autowired
  private AccountPanel accountPanel;
  @Autowired
  private StatusPanel statusPanel;
  @Autowired
  private AdminService adminService;
  @Autowired
  private ExecutorService executorService;

  private JTextField usernameTf = new JTextField();
  private JPasswordField passwordTf = new JPasswordField();
  private JButton clrBtn = new JButton("清除");
  private JButton addBtn = new JButton("添加");

  @PostConstruct
  public void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weighty = 1;

    constraints.weightx = 1;
    add(statusPanel, constraints);

    usernameTf.setPreferredSize(new Dimension(100, 23));
    usernameTf.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        setButtons();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        setButtons();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        setButtons();
      }
    });
    constraints.weightx = 0;
    constraints.insets = new Insets(3, 5, 3, 5);
    constraints.gridx = 1;
    add(new JLabel("用户名"), constraints);
    constraints.gridx = 2;
    add(usernameTf, constraints);

    passwordTf.setPreferredSize(new Dimension(100, 23));
    passwordTf.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        setButtons();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        setButtons();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        setButtons();
      }
    });
    constraints.gridx = 3;
    add(new JLabel("密码"), constraints);
    constraints.gridx = 4;
    add(passwordTf, constraints);

    clrBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        clearFields();
      }
    });
    clrBtn.setEnabled(false);
    constraints.gridx = 5;
    add(clrBtn, constraints);
    addBtn.setEnabled(false);
    addBtn.addActionListener(new AsyncActionListener(executorService) {
      @Override
      public void run() {
        UserSetting userSetting = adminService.addAccount(usernameTf.getText(), new String(passwordTf.getPassword()));
        if(userSetting != null) {
          accountPanel.addAccount(userSetting);
          clearFields();
        }
      }
    });
    constraints.gridx = 6;
    add(addBtn, constraints);
  }

  private void setReset(String username, String password) {
    clrBtn.setEnabled(!username.isEmpty() || !password.isEmpty());
  }

  private void setAdd(String username, String password) {
    addBtn.setEnabled(adminService.getAccount(username) == null
                        && StringUtils.isAlphanumeric(username)
                        && StringUtils.isAlphanumeric(password));
  }

  public void setButtons() {
    String username = usernameTf.getText();
    String password = new String(passwordTf.getPassword());
    setReset(username, password);
    setAdd(username, password);
  }

  public void clearFields() {
    usernameTf.setText("");
    passwordTf.setText("");
  }
}
