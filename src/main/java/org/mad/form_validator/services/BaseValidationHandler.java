package org.mad.form_validator.services;

import org.mad.form_validator.dto.RequestDTO;

public abstract class BaseValidationHandler implements ValidationHandler{

    protected ValidationHandler next;

    @Override
    public void setNext(ValidationHandler next) {
        this.next = next;
    }

    @Override
    public void handler(RequestDTO requestDTO) {

        process(requestDTO);

        if(next != null) {
            next.handler(requestDTO);
        }
    }

    protected abstract void process(RequestDTO request);
}
