package kehao.ui;

import javax.swing.*;

import kehao.model.UserFunctionalSetting;

public abstract class FunctionalInterfacePanel<S extends UserFunctionalSetting> extends JPanel {

  protected String username;
  protected S setting;

  protected abstract void save(S setting);

  public void save() {
    save(setting);
  }

  protected abstract void load(S setting);

  public void load(String username, S setting) {
    this.username = username;
    this.setting = setting;
    load(setting);
  }

  protected abstract void oneKeyStart(String username, S setting);

  public void oneKeyStart() {
    oneKeyStart(username, setting);
  }
}
