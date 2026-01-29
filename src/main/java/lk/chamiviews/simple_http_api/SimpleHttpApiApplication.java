package lk.chamiviews.simple_http_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SimpleHttpApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(SimpleHttpApiApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Simple HTTP API application...");
		try {
			SpringApplication.run(SimpleHttpApiApplication.class, args);
			logger.info("Simple HTTP API application started successfully");
		} catch (Exception e) {
			logger.error("Failed to start Simple HTTP API application", e);
			throw e;
		}
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		logger.info("Simple HTTP API is ready to accept requests on /hello-world endpoint");
	}

}
