package kehao.config;

import kehao.emulator.UnknownErrorHandler;
import kehao.util.ConsoleNotificationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackgroundModeConfig {
  @Bean
  public UnknownErrorHandler getNotificationProvider() {
    return new ConsoleNotificationProvider();
  }
}
