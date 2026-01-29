package lk.chamiviews.simple_http_api.controller;

import lk.chamiviews.simple_http_api.service.HelloWorldService;
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

    private final HelloWorldService helloWorldService;

    @Autowired
    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    /**
     * GET /hello-world endpoint
     * 
     * @param name the name parameter from query string
     * @return ResponseEntity with appropriate response based on business logic
     */
    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld(@RequestParam(value = "name", required = false) String name) {
        return helloWorldService.processHelloRequest(name);
    }
}