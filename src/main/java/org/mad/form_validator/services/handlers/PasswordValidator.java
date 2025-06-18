package org.mad.form_validator.services.handlers;

import org.mad.form_validator.dto.RequestDTO;
import org.mad.form_validator.exceptions.ValidationException;
import org.mad.form_validator.services.BaseValidationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator extends BaseValidationHandler {
    private static final Logger logger = LoggerFactory.getLogger(PasswordValidator.class);

    @Override
    protected void process(RequestDTO request) {
        logger.info("Init process validate password");
        String password = request.getPassword();

        if (password == null )
            throw new ValidationException("Password must exist to be validated");

        if (password.length() < 8 )
            throw new ValidationException("Password must contain at least 8 characters");

        if (!password.matches((".*\\d.*"))) //"^(?=.*\\d)" ))
            throw new ValidationException("The password must contain at least 1 digit");

        if (!password.matches(".*[A-Z].*")) //"^(?=.*[A-Z])"))
            throw new ValidationException("The password must contain at least 1 capital letter");

        if (!password.matches( ".*[a-z].*")) //"^(?=.*[a-z])"))
            throw new ValidationException("The password must contain at least 1 lowercase letter");

        if (!password.matches(".*[!@#$%^&*()_+\\-={}|\\[\\]:;\"'<>,.?/~`].*")) // ^(?=.*[^\\w\\s]).*$"))
            throw new ValidationException("The password must contain at least 1 special character");

        logger.info("Validated password");
        }

}

