package org.mad.form_validator.services.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mad.form_validator.dto.RequestDTO;
import org.mad.form_validator.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

private EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
         emailValidator = new EmailValidator();
    }
    @Test
    @DisplayName("Should not throw ValidationException when email is valid")
    void testProcess_shouldNotThrowValidationExceptionWhenEmailIsValid() {
        RequestDTO requestDTO = new RequestDTO("barbara@free.fr", null, null, null, null);

        assertDoesNotThrow(() -> emailValidator.handler(requestDTO));
    }

    @Test
    @DisplayName("Should throw ValidationException when email is invalid")
    void testProcess_shouldThrowValidationExceptionWhenEmailIsInvalid() {
        RequestDTO requestDTO = new RequestDTO("invalid email", null, null, null, null);

        ValidationException validationException = assertThrows(ValidationException.class,
                () -> emailValidator.handler(requestDTO));
        assertEquals("email: must be a well-formed email address", validationException.getMessage());

    }

    @Test
    @DisplayName("Should throw ValidationException when email is null")
    void testProcess_shouldThrowValidationExceptionWhenEmailIsNull() {
        RequestDTO requestDTO = new RequestDTO(null, null, null, null, null);

        ValidationException validationException = assertThrows(ValidationException.class,
                () -> emailValidator.handler(requestDTO));

        assertEquals("email: must be a well-formed email address", validationException.getMessage());

    }
}