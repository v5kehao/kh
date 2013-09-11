package kehao.service;

import kehao.emulator.EmulatorLegion;
import kehao.emulator.EmulatorUser;
import kehao.emulator.game.model.basic.Legions;
import kehao.emulator.game.model.basic.Member;
import kehao.emulator.game.model.basic.UserInfo;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.UserNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.exception.user.RequireChangeNicknameException;
import kehao.model.LegionSetting;
import kehao.util.AppendableReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegionService extends FunctionalServiceProvider<LegionSetting> {

  private EmulatorLegion legion;
  private EmulatorUser user;

  @Autowired
  public LegionService(LockingService locker, EmulatorLegion legion, EmulatorUser user, AssetsService assetsService) {
    super(locker, assetsService);
    this.legion = legion;
    this.user = user;
  }

  @Override
  protected void run(String username, LegionSetting setting, AppendableReport report) throws UserNotAvailableException,
                                                                                                 ServerNotAvailableException,
                                                                                                 WrongCredentialException {
    String src = src(username);
    contribute(username, setting.getTech(), setting.getAmount(), report);
  }

  @Override
  protected String printConfig(String username, LegionSetting setting) {
    return "";
  }

  private void contribute(String username, int tech, int amount, AppendableReport report) throws UserNotAvailableException,
                                                                                                   ServerNotAvailableException,
                                                                                                   WrongCredentialException {
    String src = src(username);
    UserInfo info = user.getUserInfo(username);
    String nick = info.getNickName();
    long gold = info.getCoins();
    if(gold < amount) {
      report.append(src, nick + "拥有金币" + gold + "低于" + amount + "，放弃执行");
      return;
    } else {
      report.append(src, nick + "拥有金币" + gold + "，即将捐赠" + amount + "金币");
    }
    Legions legions = legion.getLegions(username);
    Member me = legions.getMyInfo();
    if(me == null) {
      report.append(src, nick + "暂不属于任何军团，放弃捐赠");
      return;
    }
    long contribution = me.getContribute();
    report.append(src, nick + "当前贡献值为" + contribution);
    if(legion.donate(username, 3, tech, amount)) {
      long newContribution = legion.getLegions(username).getMyInfo().getContribute();
      long diff = newContribution - contribution;
      report.append(src, "捐赠成功，贡献值（+" + diff + "）提高至" + newContribution);
    } else {
      report.append(src, "捐赠失败");
    }
  }
}
