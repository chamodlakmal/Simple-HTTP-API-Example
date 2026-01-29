package lk.chamiviews.simple_http_api.service;

import lk.chamiviews.simple_http_api.constants.ApiConstants;
import lk.chamiviews.simple_http_api.dto.ErrorResponse;
import lk.chamiviews.simple_http_api.dto.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HelloWorldService
 */
public class HelloWorldServiceTest {

    private HelloWorldService helloWorldService;

    @BeforeEach
    void setUp() {
        helloWorldService = new HelloWorldService();
    }

    // Tests for valid names (A-M range) - should return 200 OK
    
    @Test
    void testProcessHelloRequest_ValidNameStartingWithA_ReturnsSuccess() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("alice");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello Alice", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_ValidNameStartingWithM_ReturnsSuccess() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("mary");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello Mary", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_ValidNameUppercaseA_ReturnsSuccess() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("Alice");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello Alice", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_ValidNameUppercaseM_ReturnsSuccess() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("MARY");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello MARY", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_ValidNameMidRange_ReturnsSuccess() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("john");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello John", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_SingleCharacterValidName_ReturnsSuccess() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("a");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello A", successResponse.getMessage());
    }

    // Tests for invalid names (N-Z range) - should return 400 Bad Request

    @Test
    void testProcessHelloRequest_InvalidNameStartingWithN_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("nancy");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_InvalidNameStartingWithZ_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("zoe");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_InvalidNameUppercaseN_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("Nancy");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_InvalidNameUppercaseZ_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("ZOE");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_InvalidNameMidRange_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("peter");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_SingleCharacterInvalidName_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("z");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    // Tests for null and empty inputs

    @Test
    void testProcessHelloRequest_NullName_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest(null);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_EmptyName_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_WhitespaceOnlyName_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("   ");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_TabAndSpaceName_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("\t \n");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    // Tests for whitespace handling (leading/trailing spaces)

    @Test
    void testProcessHelloRequest_ValidNameWithLeadingSpaces_ReturnsSuccess() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("  alice");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello Alice", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_ValidNameWithTrailingSpaces_ReturnsSuccess() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("alice  ");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello Alice", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_ValidNameWithLeadingAndTrailingSpaces_ReturnsSuccess() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("  alice  ");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello Alice", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_InvalidNameWithSpaces_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("  nancy  ");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    // Tests for non-letter inputs

    @Test
    void testProcessHelloRequest_NameStartingWithNumber_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("123alice");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_NameStartingWithSpecialCharacter_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("@alice");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    @Test
    void testProcessHelloRequest_NameStartingWithUnderscore_ReturnsBadRequest() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("_alice");
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertEquals(ApiConstants.INVALID_INPUT_ERROR, errorResponse.getError());
    }

    // Tests for name capitalization behavior

    @Test
    void testProcessHelloRequest_LowercaseName_CapitalizesFirstLetter() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("bob");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello Bob", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_MixedCaseName_PreservesOriginalCasing() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("bOb");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello BOb", successResponse.getMessage());
    }

    @Test
    void testProcessHelloRequest_AllUppercaseName_PreservesOriginalCasing() {
        ResponseEntity<?> response = helloWorldService.processHelloRequest("BOB");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        assertEquals("Hello BOB", successResponse.getMessage());
    }
}