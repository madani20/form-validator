package org.mad.form_validator.services.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mad.form_validator.dto.RequestDTO;
import org.mad.form_validator.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class NameValidatorTest {

    private  NameValidator nameValidator;

    @BeforeEach
    void setUp() {
        nameValidator = new NameValidator();
    }
    @Test
    @DisplayName("Should not throw ValidationException when first and last name are valid")
    void testProcess_shouldNotThrowValidationExceptionWhenFirstAndLastNameAreValid() {
        RequestDTO requestDTO = new RequestDTO(null, null, "Jack", "London", null);

        assertDoesNotThrow( () -> nameValidator.handler(requestDTO));
    }

    @Test
    @DisplayName("Should throw ValidationException when first or last name is null")
    void testProcess_shouldThrowValidationExceptionWhenFirstOrLastNameIsNull() {
        RequestDTO firstRequestDTO = new RequestDTO(null, null, null, "London", null);
        RequestDTO lastRequestDTO = new RequestDTO(null, null, "Jack", null, null);

        ValidationException validationExceptionFirst = assertThrows( ValidationException.class,
                () -> nameValidator.handler(firstRequestDTO));

        ValidationException validationExceptionLast = assertThrows( ValidationException.class,
                () -> nameValidator.handler(lastRequestDTO));

        assertEquals("Missing first or last name", validationExceptionFirst.getMessage());
        assertEquals("Missing first or last name", validationExceptionLast.getMessage());

    }
}