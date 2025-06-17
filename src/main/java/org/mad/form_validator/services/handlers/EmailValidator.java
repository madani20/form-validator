package org.mad.form_validator.services.handlers;

import jakarta.validation.ValidationException;
import org.mad.form_validator.api.ValidationController;
import org.mad.form_validator.dto.RequestDTO;
import org.mad.form_validator.services.BaseValidationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator extends BaseValidationHandler {
    private static final Logger logger = LoggerFactory.getLogger(EmailValidator.class);


    @Override
    protected void process(RequestDTO requestDTO) {
        logger.info("Init process validate email");
        String email = requestDTO.getEmail();

        if(email ==null || !email.matches("^\\S+@\\S+\\.\\S+$")) {
            throw new ValidationException("Invalid email");
        }
        logger.info("Validated email");
    }
}
