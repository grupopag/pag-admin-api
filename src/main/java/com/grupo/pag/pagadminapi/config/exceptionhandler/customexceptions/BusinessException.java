package com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String mensagem) {
        super(mensagem);
    }
}
