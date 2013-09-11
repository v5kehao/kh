package kehao;

import com.beust.jcommander.Parameter;


public class CmdArgs {
  @Parameter(names = {"-h", "--help"}, arity = 0, description = "")
  private boolean help;

  @Parameter(names = {"-b", "--background"}, arity = 0, description = "")
  private boolean backgroundMode;

  @Parameter(names = {"-u", "--username"}, arity = 1, description = "")
  private String username;

  @Parameter(names = {"-us", "--usernames"}, variableArity = true, description = "")
  private String usernames;

  @Parameter(names = {"-p", "--password"}, arity = 1, description = "")
  private String password;

  @Parameter(names = {"-l", "--lazy"}, arity = 0, description = "")
  private boolean lazyMode;

  @Parameter(names = {"-s", "--single"}, arity = 0, description = "")
  private boolean singleRun;

  @Parameter(names = {"-a", "--all"}, arity = 0, description = "")
  private boolean runAllTasks;

  @Parameter(names = {"-ae", "--all-energy"}, arity = 0, description = "")
  private boolean runAllEnergyTasks;

  @Parameter(names = {"-bt", "--boss"}, arity = 0, description = "")
  private boolean runBossTask;

  @Parameter(names = {"-ct", "--crpt"}, arity = 0, description = "")
  private boolean runCorruptionTask;

  @Parameter(names = {"-et", "--engy"}, arity = 0, description = "")
  private boolean runEnergyTask;

  @Parameter(names = {"-xt", "--xplr"}, arity = 0, description = "")
  private boolean runExploreTask;

  @Parameter(names = {"-lt", "--legn"}, arity = 0, description = "")
  private boolean runLegionTask;

  @Parameter(names = {"-mt", "--maze"}, arity = 0, description = "")
  private boolean runMazeTask;

  @Parameter(names = {"-tt", "--thif"}, arity = 0, description = "")
  private boolean runThiefTask;

  public boolean isHelp() {
    return help;
  }

  public boolean isBackgroundMode() {
    return backgroundMode;
  }

  public String getUsername() {
    return username;
  }

  public String getUsernames() {
    return usernames;
  }

  public String getPassword() {
    return password;
  }

  public boolean isLazyMode() {
    return lazyMode;
  }

  public boolean isSingleRun() {
    return singleRun;
  }

  public boolean isRunAllTasks() {
    return runAllTasks;
  }

  public boolean isRunAllEnergyTasks() {
    return runAllEnergyTasks;
  }

  public boolean isRunBossTask() {
    return runBossTask;
  }

  public boolean isRunCorruptionTask() {
    return runCorruptionTask;
  }

  public boolean isRunEnergyTask() {
    return runEnergyTask;
  }

  public boolean isRunExploreTask() {
    return runExploreTask;
  }

  public boolean isRunLegionTask() {
    return runLegionTask;
  }

  public boolean isRunMazeTask() {
    return runMazeTask;
  }

  public boolean isRunThiefTask() {
    return runThiefTask;
  }
}
