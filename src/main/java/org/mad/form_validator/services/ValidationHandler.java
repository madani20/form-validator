package org.mad.form_validator.services;

import org.mad.form_validator.dto.RequestDTO;

public interface ValidationHandler {

    void setNext(ValidationHandler next);
    void handler(RequestDTO requestDTO);
}
