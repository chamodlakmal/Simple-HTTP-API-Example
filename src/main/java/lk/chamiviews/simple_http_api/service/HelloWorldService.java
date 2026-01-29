package lk.chamiviews.simple_http_api.service;

import lk.chamiviews.simple_http_api.constants.ApiConstants;
import lk.chamiviews.simple_http_api.dto.ErrorResponse;
import lk.chamiviews.simple_http_api.dto.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service class containing business logic for hello-world endpoint
 */
@Service
public class HelloWorldService {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

    public HelloWorldService() {
        logger.info("HelloWorldService initialized successfully");
    }

    /**
     * Processes the name parameter and returns appropriate response
     * 
     * @param name the name parameter from the request
     * @return ResponseEntity with either success or error response
     */
    public ResponseEntity<?> processHelloRequest(String name) {
        logger.debug("Processing hello request for name: '{}'", name);
        
        // Check if name is null, empty, or contains only whitespace
        if (name == null || name.trim().isEmpty()) {
            logger.warn("Request rejected: name parameter is null, empty, or contains only whitespace. Input: '{}'", name);
            return ResponseEntity.badRequest().body(new ErrorResponse(ApiConstants.INVALID_INPUT_ERROR));
        }

        // Trim the name to handle leading/trailing spaces
        String trimmedName = name.trim();
        logger.debug("Trimmed name: '{}'", trimmedName);
        
        // Get the first character and convert to uppercase for comparison
        char firstChar = Character.toUpperCase(trimmedName.charAt(0));
        logger.debug("First character (uppercase): '{}'", firstChar);
        
        // Check if the first character is a letter
        if (!Character.isLetter(firstChar)) {
            logger.warn("Request rejected: first character '{}' is not a letter. Input: '{}'", firstChar, name);
            return ResponseEntity.badRequest().body(new ErrorResponse(ApiConstants.INVALID_INPUT_ERROR));
        }
        
        // Check if first letter is in first half of alphabet (A-M)
        if (firstChar >= ApiConstants.FIRST_HALF_START && firstChar <= ApiConstants.FIRST_HALF_END) {
            // Capitalize first letter and keep the rest as-is
            String capitalizedName = Character.toUpperCase(trimmedName.charAt(0)) + 
                                   (trimmedName.length() > 1 ? trimmedName.substring(1) : "");
            
            logger.info("Request accepted: first character '{}' is in range A-M. Returning success response for name: '{}'", 
                       firstChar, capitalizedName);
            logger.debug("Generated capitalized name: '{}'", capitalizedName);
            
            return ResponseEntity.ok(new SuccessResponse(String.format(ApiConstants.HELLO_MESSAGE_TEMPLATE, capitalizedName)));
        } else {
            // First letter is in second half (N-Z)
            logger.warn("Request rejected: first character '{}' is in range N-Z. Input: '{}'", firstChar, name);
            return ResponseEntity.badRequest().body(new ErrorResponse(ApiConstants.INVALID_INPUT_ERROR));
        }
    }
}