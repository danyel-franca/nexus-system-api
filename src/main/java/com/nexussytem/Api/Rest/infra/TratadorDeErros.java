package com.nexussytem.Api.Rest.infra;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Pega os erros de todos os controllers e devolve uma resposta clara para o app
@RestControllerAdvice
public class TratadorDeErros {

    // Campo inválido (@NotBlank, @Email, @Size...) -> 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroCampo>> erroValidacao(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors()
                .stream()
                .map(erro -> new DadosErroCampo(erro.getField(), erro.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(erros);
    }

    // JSON mal escrito ou valor inválido, como "status": "URGENTE" -> 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> erroJson() {
        return ResponseEntity.badRequest()
                .body(Map.of("mensagem", "JSON inválido ou com valores não permitidos."));
    }

    // Dado repetido no banco, como e-mail já cadastrado -> 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> erroBanco() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("mensagem", "Já existe um registro com esses dados."));
    }

    public record DadosErroCampo(String campo, String mensagem) {
    }
}