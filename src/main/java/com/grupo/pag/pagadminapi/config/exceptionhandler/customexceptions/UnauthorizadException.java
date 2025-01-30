package com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizadException extends RuntimeException {

    public UnauthorizadException(String mensagem){
        super(mensagem);
    }

}
