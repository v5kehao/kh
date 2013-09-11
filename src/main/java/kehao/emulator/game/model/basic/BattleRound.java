package kehao.emulator.game.model.basic;

import java.util.ArrayList;
import java.util.List;

public class BattleRound {
  private int Round;
  private boolean isAttack;
  private List<Opp> Opps;

  public class Opp {
    private String UUID;
    private int Opp;
    private List<?> Target;
    private int Value;

    public String getUUID() {
      return UUID;
    }

    public void setUUID(String UUID) {
      this.UUID = UUID;
    }

    public int getOpp() {
      return Opp;
    }

    public void setOpp(int opp) {
      Opp = opp;
    }

    public List<String> getTarget() {
      // hard fix
      List<String> ret = new ArrayList<String>();
      for(Object t : Target) {
        if(t instanceof Iterable) {
          for(Object o : (Iterable) t) {
            ret.add(o.toString());
          }
        } else {
          ret.add(t.toString());
        }
      }
      return ret;
    }

    public void setTarget(List<?> target) {
      Target = target;
    }

    public int getValue() {
      return Value;
    }

    public void setValue(int value) {
      Value = value;
    }
  }



  public int getRound() {
    return Round;
  }

  public void setRound(int round) {
    Round = round;
  }

  public boolean isAttack() {
    return isAttack;
  }

  public void setAttack(boolean attack) {
    isAttack = attack;
  }

  public List<Opp> getOpps() {
    return Opps;
  }

  public void setOpps(List<Opp> opps) {
    Opps = opps;
  }


}
