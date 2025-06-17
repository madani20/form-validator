package org.mad.form_validator.services.handlers;

import jakarta.validation.ValidationException;
import org.mad.form_validator.dto.RequestDTO;
import org.mad.form_validator.services.BaseValidationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NameValidator extends BaseValidationHandler {
    private static final Logger logger = LoggerFactory.getLogger(NameValidator.class);


    @Override
    protected void process(RequestDTO request) {
        logger.info("Init process first and last name");

    if(request.getFirstName() == null || request.getLastName() == null) {
        throw new ValidationException("Missing first or last name");
    }
    logger.info("Validated first and last name");
    }
}
