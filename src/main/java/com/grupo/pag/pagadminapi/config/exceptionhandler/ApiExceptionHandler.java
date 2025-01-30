package com.grupo.pag.pagadminapi.config.exceptionhandler;


import com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions.BadRequestException;
import com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions.BusinessException;
import com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions.EntidadeNaoEncontradaException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@ControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler {
    private static final String MENSAGEM_GENERICA = "Desculpe, um erro interno aconteceu, " +
            "caso o erro persista entre em contato com o administrador do sistema através do email feliperp321@gmail.com ";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if(ex.getErrorCount() > 0){
            return formatErroFormulario(ex, headers, status, request);
        }
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex ){
//        Sentry.captureException(ex);
        ErroApiResponse erroApiResponse =   createBuilderDefauld(ex,HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .mensagemDesenvolvedor(ex.getMessage())
                .mensagemUsuario(MENSAGEM_GENERICA)
                .build();
        return handleExceptionInternal(ex, erroApiResponse,  new HttpHeaders(), HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),null);
    }

//    @ExceptionHandler(UnauthorizadException.class)
//    public ResponseEntity<Object> handleException(UnauthorizadException ex ){
//        Sentry.captureException(ex);
//        ErroApiResponse erroApiResponse =   createBuilderDefauld(ex,HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()))
//                .mensagemDesenvolvedor(ex.getMessage())
//                .mensagemUsuario(ex.getMessage())
//                .build();
//        return handleExceptionInternal(ex, erroApiResponse,  new HttpHeaders(), HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()),null);
//    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex ){
        //        Sentry.captureException(ex);
        ErroApiResponse erroApiResponse =   createBuilderDefauld(ex,HttpStatusCode.valueOf(HttpStatus.CONFLICT.value()))
                .mensagemDesenvolvedor(ex.getMessage())
                .mensagemUsuario(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, erroApiResponse,  new HttpHeaders(), HttpStatusCode.valueOf(HttpStatus.CONFLICT.value()),null);
    }



    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex ){
//        Sentry.captureException(ex);
        ErroApiResponse erroApiResponse =   createBuilderDefauld(ex,HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()))
                .mensagemDesenvolvedor(ex.getMessage())
                .mensagemUsuario(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, erroApiResponse,  new HttpHeaders(), HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()),null);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex ){
//        Sentry.captureException(ex);
        ErroApiResponse erroApiResponse =   createBuilderDefauld(ex,HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()))
                .mensagemDesenvolvedor(ex.getMessage())
                .mensagemUsuario(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, erroApiResponse,  new HttpHeaders(), HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),null);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex, "Não foi possível deserializar o conteúdo da requisição, verifique a estrutura da request e tente novamente. " ,headers, status, request);
    }

    private ResponseEntity<Object> formatErroFormulario(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        List<ErroApiResponse.FieldErro> erros =  new ArrayList<>();
        ex.getAllErrors().forEach(erro-> erros.add(new ErroApiResponse.FieldErro(getFieldNameByErroCode(((FieldError) erro).getCodes()),erro.getDefaultMessage())));
      ErroApiResponse erroApiResponse =   createBuilderDefauld(ex,status).
                erros(erros)
                .mensagemUsuario("Um ou mais campos estão inválidos, verifique e envie novamente.")
              .mensagemDesenvolvedor("Um ou mais campos estão inválidos, verifique e envie novamente.")
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroApiResponse);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        log.error(ex.getMessage());
        if(body ==  null){
            return ResponseEntity.
                    status(HttpStatus.valueOf(statusCode.value()) ).
                    body(createBuildBodyNull(ex,statusCode));
        }
        if(body instanceof  String){
            return ResponseEntity.
                    status(HttpStatus.valueOf(statusCode.value()) ).
                    body(createBuildBodyString(ex,body,statusCode));
        }

        return  ResponseEntity.
                status(HttpStatus.valueOf(statusCode.value()) ).
                body(body);
    }

    private ErroApiResponse createBuildBodyString(Exception exception, Object body , HttpStatusCode httpStatusCode ){
        return ErroApiResponse.builder().
        timeStamp(LocalDateTime.now())
                .mensagemDesenvolvedor(exception.getMessage())
                .mensagemUsuario((String) body)
                .status(HttpStatus.valueOf(httpStatusCode.value()).getReasonPhrase())
                .statusCode(Long.parseLong(String.valueOf(httpStatusCode.value()))).build();
    }

    private ErroApiResponse createBuildBodyNull(Exception exception , HttpStatusCode httpStatusCode ){
        return ErroApiResponse.builder().
                timeStamp(LocalDateTime.now())
                .mensagemDesenvolvedor(exception.getMessage())
                .mensagemUsuario(MENSAGEM_GENERICA)
                .status(HttpStatus.valueOf(httpStatusCode.value()).getReasonPhrase())
                .statusCode(Long.parseLong(String.valueOf(httpStatusCode.value()))).build();
    }

    private ErroApiResponse.ErroApiResponseBuilder createBuilderDefauld(Exception exception , HttpStatusCode httpStatusCode ){
        return ErroApiResponse.builder().
                timeStamp(LocalDateTime.now())
                .mensagemDesenvolvedor(exception.getMessage())
                .status(HttpStatus.valueOf(httpStatusCode.value()).getReasonPhrase())
                .statusCode(Long.parseLong(String.valueOf(httpStatusCode.value())));
    }

    private String getFieldNameByErroCode(String[] codes){
        if(codes != null && codes.length > 0){
            return Stream.of(codes[0].split("\\.")).skip(2).collect(Collectors.joining("."));
        }
        return "";
    }

}
