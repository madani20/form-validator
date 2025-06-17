package org.mad.form_validator.services.registrationService;

import org.mad.form_validator.dto.RequestDTO;
import org.mad.form_validator.services.handlers.EmailValidator;
import org.mad.form_validator.services.handlers.NameValidator;
import org.mad.form_validator.services.handlers.PasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final NameValidator nameValidator;

    public ValidationService(EmailValidator emailValidator, PasswordValidator passwordValidator, NameValidator nameValidator) {
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
        this.nameValidator = nameValidator;

        //***********[    Construction manuelle de la chaine    ]**********************/

        emailValidator.setNext(passwordValidator);
        passwordValidator.setNext(nameValidator);

        //******************************************************************************/
    }

    public void validate(RequestDTO requestDTO) {
        logger.info("Init validate from Validation service");

        emailValidator.handler(requestDTO);

        logger.info("Validated fields");
    }


}
