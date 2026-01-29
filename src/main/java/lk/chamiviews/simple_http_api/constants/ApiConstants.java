package lk.chamiviews.simple_http_api.constants;

/**
 * Constants used throughout the API
 */
public final class ApiConstants {
    
    // Error messages
    public static final String INVALID_INPUT_ERROR = "Invalid Input";
    
    // Response message templates
    public static final String HELLO_MESSAGE_TEMPLATE = "Hello %s";
    
    // Alphabet boundaries
    public static final char FIRST_HALF_START = 'A';
    public static final char FIRST_HALF_END = 'M';
    
    // Private constructor to prevent instantiation
    private ApiConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}