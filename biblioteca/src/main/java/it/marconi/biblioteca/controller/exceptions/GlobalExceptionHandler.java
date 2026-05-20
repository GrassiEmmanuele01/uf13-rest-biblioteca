package it.marconi.biblioteca.controller.exceptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import it.marconi.biblioteca.domain.response.APIResponse;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handle(Exception ex){
        List<String>errors=new ArrayList<>();
        Arrays.stream(ex.getStackTrace())
        .forEach(st->errors.add(st.toString()));

        return ResponseEntity.internalServerError().body(
            APIResponse.error(
                ex.getMessage(), 
                500));
    } 

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<APIResponse<Object>> handleResSts(ResponseStatusException ex){
        log.warn("Errore gestione risposte:", ex.getReason(),ex);
        log.trace("Stack trace completo",ex);
        
        return new ResponseEntity<>(
            APIResponse.fail(ex.getMessage(),ex.getStatusCode().value()),
            ex.getStatusCode()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Object>> handle(MethodArgumentNotValidException ex){
        Map<String,String>errors=new HashMap<>();
        ex.getBindingResult()
        .getFieldErrors()
        .forEach(er -> 
            errors.put(er.getField(),er.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(
            APIResponse.fail(
                errors,
                ex.getMessage(), 
                ex.getStatusCode().value(),
                ex.getErrorCount()));
    }
}
