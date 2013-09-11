package kehao.emulator.game.model.basic;

public class Boss {
  private int BossCardId;
  private int BossCardLevel;
  private int[] FeebleCardId;
  private Award WinAward;
  private Award[] HonorAwards;
  private int[][] Times;
  private Award[] RankAwards;
  private int RankEnd;
  private int HonorEnd;
  private Award JoinAward;
  private int[] FeebleCardIds;
  private int BossHp;
  private int BossCurrentHp;

  public int getBossCardId() {
    return BossCardId;
  }

  public int getBossCardLevel() {
    return BossCardLevel;
  }

  public int[] getFeebleCardId() {
    return FeebleCardId;
  }

  public Award getWinAward() {
    return WinAward;
  }

  public Award[] getHonorAwards() {
    return HonorAwards;
  }

  public int[][] getTimes() {
    return Times;
  }

  public Award[] getRankAwards() {
    return RankAwards;
  }

  public int getRankEnd() {
    return RankEnd;
  }

  public int getHonorEnd() {
    return HonorEnd;
  }

  public Award getJoinAward() {
    return JoinAward;
  }

  public int[] getFeebleCardIds() {
    return FeebleCardIds;
  }

  public int getBossHp() {
    return BossHp;
  }

  public int getBossCurrentHp() {
    return BossCurrentHp;
  }

  public class HonorAward extends Award {
    private int Honor;
    private int EndHonor;

    public int getHonor() {
      return Honor;
    }

    public int getEndHonor() {
      return EndHonor;
    }
  }

  public class RankAward extends Award {
    private int StartRank;
    private int EndRank;

    public int getStartRank() {
      return StartRank;
    }

    public int getEndRank() {
      return EndRank;
    }
  }


  public class Award {
    private int AwardType;
    private int AwardValue;

    public int getAwardType() {
      return AwardType;
    }

    public int getAwardValue() {
      return AwardValue;
    }
  }

}
