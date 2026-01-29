package lk.chamiviews.simple_http_api.service;

import lk.chamiviews.simple_http_api.constants.ApiConstants;
import lk.chamiviews.simple_http_api.dto.ErrorResponse;
import lk.chamiviews.simple_http_api.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service class containing business logic for hello-world endpoint
 */
@Service
public class HelloWorldService {

    /**
     * Processes the name parameter and returns appropriate response
     * 
     * @param name the name parameter from the request
     * @return ResponseEntity with either success or error response
     */
    public ResponseEntity<?> processHelloRequest(String name) {
        // Check if name is null, empty, or contains only whitespace
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ApiConstants.INVALID_INPUT_ERROR));
        }

        // Trim the name to handle leading/trailing spaces
        String trimmedName = name.trim();
        
        // Get the first character and convert to uppercase for comparison
        char firstChar = Character.toUpperCase(trimmedName.charAt(0));
        
        // Check if the first character is a letter
        if (!Character.isLetter(firstChar)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ApiConstants.INVALID_INPUT_ERROR));
        }
        
        // Check if first letter is in first half of alphabet (A-M)
        if (firstChar >= ApiConstants.FIRST_HALF_START && firstChar <= ApiConstants.FIRST_HALF_END) {
            // Capitalize first letter and keep the rest as-is
            String capitalizedName = Character.toUpperCase(trimmedName.charAt(0)) + 
                                   (trimmedName.length() > 1 ? trimmedName.substring(1) : "");
            return ResponseEntity.ok(new SuccessResponse(String.format(ApiConstants.HELLO_MESSAGE_TEMPLATE, capitalizedName)));
        } else {
            // First letter is in second half (N-Z)
            return ResponseEntity.badRequest().body(new ErrorResponse(ApiConstants.INVALID_INPUT_ERROR));
        }
    }
}