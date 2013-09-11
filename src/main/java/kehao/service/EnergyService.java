package kehao.service;

import java.util.*;

import kehao.emulator.EmulatorFEnergy;
import kehao.emulator.EmulatorFriend;
import kehao.emulator.EmulatorLegion;
import kehao.emulator.game.model.basic.Friend;
import kehao.emulator.game.model.basic.Friends;
import kehao.emulator.game.model.basic.UserLegion;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.model.EnergySetting;
import kehao.util.AppendableReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyService extends FunctionalServiceProvider<EnergySetting> {

  private EmulatorFEnergy fEnergy;
  private EmulatorFriend friend;
  private EmulatorLegion legion;

  @Autowired
  public EnergyService(LockingService locker, EmulatorFEnergy fEnergy, EmulatorFriend friend, EmulatorLegion legion, AssetsService assetsService) {
    super(locker, assetsService);
    this.fEnergy = fEnergy;
    this.friend = friend;
    this.legion = legion;
  }

  @Override
  protected void run(String username, EnergySetting setting, AppendableReport report) throws ServerNotAvailableException, WrongCredentialException {
    String src = src(username);
    Friends friends = friend.getFriends(username);
    giffgaffEnergy(username, setting.isFavourReturn(), setting.isFavourLegion(), setting.isFavourRank(), friends, report);
  }

  @Override
  protected String printConfig(String username, EnergySetting setting) {
    return "";
  }

  private void giffgaffEnergy(String username, boolean favourReturn, boolean favourLegion, boolean favourRank, Friends friends, AppendableReport report) throws ServerNotAvailableException, WrongCredentialException {
    String src = src(username);
    String legionName = null;
    if(favourLegion) {
      UserLegion userLegion = legion.getUserLegion(username);
      if(userLegion == null || (legionName = userLegion.getLegionName()) == null) {
        report.append(src, username + "不属于任何军团，自动忽略“优先赠予相同军团好友”选项");
        favourLegion = false;
      } else {
        report.append(src, username + "属于" + legionName + "军团，将优先赠予相同军团好友");
      }
    }
    List<Friend> toSend = new ArrayList<>();
    List<Friend> sameLegion = new ArrayList<>();
    List<Friend> pending = new ArrayList<>();
    boolean doneCollection = false;
    for(Friend f : friends.getFriends()) {
      long uid = f.getUid();
      if(f.isCollectable()) {
        if(!doneCollection && !fEnergy.getFEnergy(username, uid)) {
          doneCollection = true;
          report.append(src, "今日领取已达上限");
        } else {
          report.append(src, "已接收" + f.getNickName() + "赠予的一点体力");
        }
        if(favourReturn) {
          report.append(src, "将" + f.getNickName() + "加入优先返赠列表");
          toSend.add(f);
        }
      } else if(favourLegion && legionName.equals(f.getLegionName())) {
        report.append(src, "将相同军团好友" + f.getNickName() + "加入优先赠送列表");
        sameLegion.add(f);
      } else {
        pending.add(f);
      }
    }
    if(favourLegion) {
      toSend.addAll(sameLegion);
    }
    if(favourRank) {
      report.append(src, "正在根据好友的竞技场排名安排赠送列表");
      Collections.sort(pending, new Comparator<Friend>() {
        @Override
        public int compare(Friend f1, Friend f2) {
          return Integer.compare(f1.getRank(), f2.getRank());
        }
      });
      toSend.addAll(pending);
    }

    for(Friend f : toSend) {
      if(!fEnergy.sendFEnergy(username, f.getUid())) {
        report.append(src, "今日赠送已达上限");
        break;
      } else {
        report.append(src, "已赠予" + f.getNickName() + "一点体力");
      }
    }
  }
}
