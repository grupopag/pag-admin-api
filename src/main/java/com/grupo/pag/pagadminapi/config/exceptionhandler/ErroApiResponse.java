package com.grupo.pag.pagadminapi.config.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroApiResponse {
    private String status;
    private Long statusCode;
    private LocalDateTime timeStamp;
    private String mensagemUsuario;
    private String mensagemDesenvolvedor;
    private List<FieldErro> erros;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class  FieldErro{
         private String nome;
         private String mensagem;
    }

}
