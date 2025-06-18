package org.mad.form_validator.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ValidationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/validate should return 'Validation successful' with valid fields")
    public void testValidationForm_shouldReturnValidationSuccessfulWithValidFields() throws Exception {

        String validJson = """
                    {
                      "email": "john.doe@example.com",
                      "password": "StrongP@ss1!",
                      "firstName": "John",
                      "lastName": "Doe"
                    }
                """;

        mockMvc.perform(
                post("/api/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Validation successful"));
    }



    @Test
    @DisplayName("POST /api/validate should return 400 for invalid email")
    public void testValidationForm_shouldReturnBadRequestForInvalidEmail() throws Exception {
        String invalidEmailJson = """
        {
          "email": "invalid-email",
          "password": "StrongP@ss1!",
          "firstName": "John",
          "lastName": "Doe"
        }
    """;

        mockMvc.perform(post("/api/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEmailJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("email: must be a well-formed email address"));
    }

}
