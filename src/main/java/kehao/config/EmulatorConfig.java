package kehao.config;

import kehao.emulator._EmulatorPackageMarker;
import kehao.emulator.model.GameVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackageClasses = _EmulatorPackageMarker.class)
@PropertySource("classpath:/emulator.properties")
public class EmulatorConfig {

  public static final String PLATFORM = "platform";
  public static final String LANGUAGE = "language";
  public static final String VERSION_CLIENT = "versionClient";
  public static final String VERSION_BUILD = "versionBuild";
  public static final String MAP_MAX = "mapMax";

  @Autowired Environment env;

  @Bean
  public GameVersion getGameVersion() {
    return new GameVersion(env.getProperty(PLATFORM),
                            env.getProperty(LANGUAGE),
                            env.getProperty(VERSION_CLIENT),
                            env.getProperty(VERSION_BUILD),
                            env.getProperty(MAP_MAX, Integer.class));
  }

}
