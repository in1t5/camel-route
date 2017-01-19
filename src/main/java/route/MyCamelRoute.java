package route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("direct:test").errorHandler(deadLetterChannel("direct:error")).to("direct:end");

		from("direct:error").log("Received message on direct:error endpoint.");

		// @formatter:off
		from("direct:end")
			.filter(simple("${body} contains 'test'"))
			.log("Received message on direct:end endpoint: ${body}");
		// @formatter:on
	}
}