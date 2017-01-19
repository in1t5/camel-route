package route;

import static org.assertj.core.api.Assertions.*;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(CamelSpringRunner.class)
@ContextConfiguration(classes = { AppConfig.class },
		// Since Camel 2.11.0
		loader = CamelSpringDelegatingTestContextLoader.class)
@MockEndpoints
public class MyCamelSpringRouteTest {

	@EndpointInject(uri = "mock:direct:end")
	protected MockEndpoint endEndpoint;

	@EndpointInject(uri = "mock:direct:error")
	protected MockEndpoint errorEndpoint;

	@Produce(uri = "direct:test")
	protected ProducerTemplate testProducer;

	@Test
	public void testRoute() throws InterruptedException {
		endEndpoint.expectedMessageCount(1);
		errorEndpoint.expectedMessageCount(0);

		testProducer.sendBody("<name>test</name>");

		endEndpoint.assertIsSatisfied();
		errorEndpoint.assertIsSatisfied();

		assertThat(endEndpoint.getExchanges()).size().isEqualTo(1);

	}
}
