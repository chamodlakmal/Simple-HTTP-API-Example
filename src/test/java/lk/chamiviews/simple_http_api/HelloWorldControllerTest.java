package lk.chamiviews.simple_http_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloAlice_Success() throws Exception {
        mockMvc.perform(get("/hello-world?name=alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Alice"));
    }

    @Test
    public void testHelloBob_Success() throws Exception {
        mockMvc.perform(get("/hello-world?name=Bob"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Bob"));
    }

    @Test
    public void testHelloZoe_BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world?name=zoe"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    public void testHelloNoName_BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    public void testHelloEmptyName_BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world?name="))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    public void testHelloNumericName_BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world?name=123"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }
}
