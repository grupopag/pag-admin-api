package com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EnvioEmailException extends RuntimeException {

    public EnvioEmailException(String mensagem){
        super(mensagem);
    }

}
