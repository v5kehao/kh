package kehao.model;

public class JourneySettings {

  private String beneficiaryAccounts;
  private String filterAccounts;
  private String providerAccounts;
  private int refreshInterval;
  private int minDifficulty;
  private boolean beneficiaryAccountExplore;
  private boolean filterAccountExplore;
  private boolean resetCoolDown;
  private int beneficiaryAccountMaxCoolDown;
  private int filterAccountMaxCoolDown;
  private int providerAccountMaxCoolDown;

  public static JourneySettings getDefault() {
    JourneySettings ret = new JourneySettings();
    ret.setBeneficiaryAccounts("");
    ret.setFilterAccounts("");
    ret.setProviderAccounts("");
    ret.setRefreshInterval(5);
    ret.setMinDifficulty(1);
    ret.setBeneficiaryAccountExplore(true);
    ret.setFilterAccountExplore(true);
    ret.setResetCoolDown(false);
    ret.setBeneficiaryAccountMaxCoolDown(49);
    ret.setFilterAccountMaxCoolDown(49);
    ret.setProviderAccountMaxCoolDown(49);
    return ret;
  }

  public String getBeneficiaryAccounts() {
    return beneficiaryAccounts;
  }

  public void setBeneficiaryAccounts(String beneficiaryAccounts) {
    this.beneficiaryAccounts = beneficiaryAccounts;
  }

  public String getFilterAccounts() {
    return filterAccounts;
  }

  public void setFilterAccounts(String filterAccounts) {
    this.filterAccounts = filterAccounts;
  }

  public String getProviderAccounts() {
    return providerAccounts;
  }

  public void setProviderAccounts(String providerAccounts) {
    this.providerAccounts = providerAccounts;
  }

  public int getRefreshInterval() {
    return refreshInterval;
  }

  public void setRefreshInterval(int refreshInterval) {
    this.refreshInterval = refreshInterval;
  }

  public int getMinDifficulty() {
    return minDifficulty;
  }

  public void setMinDifficulty(int minDifficulty) {
    this.minDifficulty = minDifficulty;
  }

  public boolean isBeneficiaryAccountExplore() {
    return beneficiaryAccountExplore;
  }

  public void setBeneficiaryAccountExplore(boolean beneficiaryAccountExplore) {
    this.beneficiaryAccountExplore = beneficiaryAccountExplore;
  }

  public boolean isFilterAccountExplore() {
    return filterAccountExplore;
  }

  public void setFilterAccountExplore(boolean filterAccountExplore) {
    this.filterAccountExplore = filterAccountExplore;
  }

  public boolean isResetCoolDown() {
    return resetCoolDown;
  }

  public void setResetCoolDown(boolean resetCoolDown) {
    this.resetCoolDown = resetCoolDown;
  }

  public int getBeneficiaryAccountMaxCoolDown() {
    return beneficiaryAccountMaxCoolDown;
  }

  public void setBeneficiaryAccountMaxCoolDown(int beneficiaryAccountMaxCoolDown) {
    this.beneficiaryAccountMaxCoolDown = beneficiaryAccountMaxCoolDown;
  }

  public int getFilterAccountMaxCoolDown() {
    return filterAccountMaxCoolDown;
  }

  public void setFilterAccountMaxCoolDown(int filterAccountMaxCoolDown) {
    this.filterAccountMaxCoolDown = filterAccountMaxCoolDown;
  }

  public int getProviderAccountMaxCoolDown() {
    return providerAccountMaxCoolDown;
  }

  public void setProviderAccountMaxCoolDown(int providerAccountMaxCoolDown) {
    this.providerAccountMaxCoolDown = providerAccountMaxCoolDown;
  }
}
