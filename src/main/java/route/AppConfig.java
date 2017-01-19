package route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig extends SingleRouteCamelConfiguration {

	@Override
	public RouteBuilder route() {
		return new MyCamelSpringRoute();
	}
}