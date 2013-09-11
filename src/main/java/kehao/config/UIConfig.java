package kehao.config;

import java.util.Arrays;

import kehao.ui._UIPackageMarker;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = _UIPackageMarker.class)
@EnableCaching
public class UIConfig {

  @Bean
  public org.springframework.cache.CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("info"), new ConcurrentMapCache("card"), new ConcurrentMapCache("rune")));
    return cacheManager;
  }
}
