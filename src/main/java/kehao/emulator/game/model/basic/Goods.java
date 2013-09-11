package kehao.emulator.game.model.basic;

import java.util.Map;

public class Goods {
  private int GoodsId;
  private String StartDate;
  private String EndDate;
  private String StartTime;
  private String EndTime;
  private String Name;
  private String Content;
  private String ExtraContent;
  private int Coins;
  private int Cash;
  private int Ticket;
  private int Add;
  private int Max;
  private int Num;
  private Map<Integer,Map<Integer, Double>> Color;
  private int Count;

  public int getGoodsId() {
    return GoodsId;
  }

  public String getStartDate() {
    return StartDate;
  }

  public String getEndDate() {
    return EndDate;
  }

  public String getStartTime() {
    return StartTime;
  }

  public String getEndTime() {
    return EndTime;
  }

  public String getName() {
    return Name;
  }

  public String getContent() {
    return Content;
  }

  public String getExtraContent() {
    return ExtraContent;
  }

  public int getCoins() {
    return Coins;
  }

  public int getCash() {
    return Cash;
  }

  public int getTicket() {
    return Ticket;
  }

  public int getAdd() {
    return Add;
  }

  public int getMax() {
    return Max;
  }

  public int getNum() {
    return Num;
  }

  public Map<Integer, Map<Integer, Double>> getColor() {
    return Color;
  }

  public int getCount() {
    return Count;
  }

  @Override
  public String toString() {
    return "Goods " + GoodsId + " " + Name;
  }

  public String getCostDescription() {
    String cost = "";
    if(Cash > 0) {
      cost += Cash + " cash";
    }
    if(Coins > 0) {
      if(!cost.isEmpty()) {
        cost += ", ";
      }
      cost += Coins + " coins";
    }
    if(Ticket > 0) {
      if(!cost.isEmpty()) {
        cost += ", ";
      }
      cost += Ticket + " ticket";
    }
    return cost;
  }
}
