package com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions;


public class EntidadeNaoEncontradaException extends RuntimeException{
   public  EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
