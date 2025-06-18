package org.mad.form_validator.services.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mad.form_validator.dto.RequestDTO;
import org.mad.form_validator.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    @DisplayName("Should not throw ValidationException when password is valid")
    void testProcess_shouldNotThrowValidationExceptionWhenPasswordIsValid() {
        RequestDTO requestDTO = new RequestDTO(null, "195HcstM#6kc", null, null, null);

        assertDoesNotThrow( () -> passwordValidator.handler(requestDTO));
    }

    @Test
    @DisplayName("Should throw ValidationException when password is too short")
    void testProcess_shouldThrowValidationExceptionWhenPasswordIsTooShort() {
        RequestDTO requestDTO = new RequestDTO(null, "16kcMHB", null, null, null);

        ValidationException validationException = assertThrows( ValidationException.class,
                () -> passwordValidator.handler(requestDTO));

        assertEquals("Password must contain at least 8 characters", validationException.getMessage());
    }

    @Test
    @DisplayName("Should throw ValidationException when password is missing digit")
    void testProcess_shouldThrowValidationExceptionWhenPasswordIsMissingDigit() {
        RequestDTO requestDTO = new RequestDTO(null, "Mpd!kcMHB", null, null, null);

        ValidationException validationException = assertThrows( ValidationException.class,
                () -> passwordValidator.handler(requestDTO));

        assertEquals("The password must contain at least 1 digit", validationException.getMessage());
    }
    @Test
    @DisplayName("Should throw ValidationException when password is missing uppercase")
    void testProcess_shouldThrowValidationExceptionWhenPasswordIsMissingUppercase() {
        RequestDTO requestDTO = new RequestDTO(null, "3pd!nstcgb", null, null, null);

        ValidationException validationException = assertThrows( ValidationException.class,
                () -> passwordValidator.handler(requestDTO));

        assertEquals("The password must contain at least 1 capital letter", validationException.getMessage());
    }

    @Test
    @DisplayName("Should throw ValidationException when password is missing lowercase")
    void testProcess_shouldThrowValidationExceptionWhenPasswordIsMissingLowercase() {
        RequestDTO requestDTO = new RequestDTO(null, "35JCT45JCXW", null, null, null);

        ValidationException validationException = assertThrows( ValidationException.class,
                () -> passwordValidator.handler(requestDTO));

        assertEquals("The password must contain at least 1 lowercase letter", validationException.getMessage());
    }

    @Test
    @DisplayName("Should throw ValidationException when password is missing special character")
    void testProcess_shouldThrowValidationExceptionWhenPasswordIsMissingSpecialCharacter() {
        RequestDTO requestDTO = new RequestDTO(null, "3nJCT4fXW", null, null, null);

        ValidationException validationException = assertThrows( ValidationException.class,
                () -> passwordValidator.handler(requestDTO));

        assertEquals("The password must contain at least 1 special character", validationException.getMessage());
    }
}