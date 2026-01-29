package lk.chamiviews.simple_http_api.controller;

import lk.chamiviews.simple_http_api.service.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for hello-world endpoint
 */
@RestController
public class HelloWorldController {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    private final HelloWorldService helloWorldService;

    @Autowired
    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
        logger.info("HelloWorldController initialized successfully");
    }

    /**
     * GET /hello-world endpoint
     * 
     * @param name the name parameter from query string
     * @return ResponseEntity with appropriate response based on business logic
     */
    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld(@RequestParam(value = "name", required = false) String name) {
        logger.info("Received GET request to /hello-world with name parameter: '{}'", name);
        
        long startTime = System.currentTimeMillis();
        
        try {
            ResponseEntity<?> response = helloWorldService.processHelloRequest(name);
            long processingTime = System.currentTimeMillis() - startTime;
            
            logger.info("Request processed successfully in {}ms. Response status: {}", 
                       processingTime, response.getStatusCode());
            logger.debug("Response body: {}", response.getBody());
            
            return response;
        } catch (Exception e) {
            long processingTime = System.currentTimeMillis() - startTime;
            logger.error("Error processing request for name '{}' after {}ms", name, processingTime, e);
            throw e;
        }
    }
}