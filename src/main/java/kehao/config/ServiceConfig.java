package kehao.config;

import kehao.service._ServicePackageMarker;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = _ServicePackageMarker.class)
public class ServiceConfig {
}
