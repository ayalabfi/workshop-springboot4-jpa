package com.educandoweb.course.resources.exceptions;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    // Indica que esse método será executado sempre que essa exceção for lançada em qualquer controller
    @ExceptionHandler(ResourceNotFoundException.class)
    // ResourceNotFoundException e: A própria exceção é capturada, permitindo acessar a mensagem com e.getMessage().
    //HttpServletRequest request: Representa a requisição HTTP que permite objet dados como URI, método HTTP e Headers.
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        // Descrição do erro.
        String error = "Resource not found!";
        // O status HTTP 404.
        HttpStatus status = HttpStatus.NOT_FOUND;
        // Montado o objeto que será convertido em JSON, que retornará os seguintes dados:
        StandardError err = new StandardError(
                // Data/hora atual
                Instant.now(),
                // 404
                status.value(),
                // "Resource not found!"
                error,
                // Mensagem da exceção
                e.getMessage(),
                // URI da requisição
                request.getRequestURI());
        // Constrói a resposta HTTP com status 404 e o Body retorna o objeto StandardError que é convertido para JSON automáticamente pelo Jackson
        return ResponseEntity.status(status).body(err);
    }

    // Indica que esse método será executado sempre que essa exceção for lançada em qualquer controller
    @ExceptionHandler(DatabaseException.class)
    // ResourceNotFoundException e: A própria exceção é capturada, permitindo acessar a mensagem com e.getMessage().
    //HttpServletRequest request: Representa a requisição HTTP que permite objet dados como URI, método HTTP e Headers.
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        // Descrição do erro.
        String error = "Database error!";
        // O status HTTP 400.
        HttpStatus status = HttpStatus.BAD_REQUEST;
        // Montado o objeto que será convertido em JSON, que retornará os seguintes dados:
        StandardError err = new StandardError(
                // Data/hora atual
                Instant.now(),
                // 400
                status.value(),
                // "Resource not found!"
                error,
                // Mensagem da exceção
                e.getMessage(),
                // URI da requisição
                request.getRequestURI());
        // Constrói a resposta HTTP com status 400 e o Body retorna o objeto StandardError que é convertido para JSON automáticamente pelo Jackson
        return ResponseEntity.status(status).body(err);
    }

}
