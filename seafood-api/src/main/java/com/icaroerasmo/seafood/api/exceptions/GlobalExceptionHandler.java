package com.icaroerasmo.seafood.api.exceptions;

import com.icaroerasmo.seafood.api.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataInconsistencyException.class)
    ResponseEntity<ErrorMessageDTO> dataInconsistencyException(DataInconsistencyException exception) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorMessageDTO(status.value(), exception.getMessage()));
    }
    @ExceptionHandler(KafkaMessagesException.class)
    ResponseEntity<ErrorMessageDTO> kafkaMessagesException(KafkaMessagesException exception) {
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(new ErrorMessageDTO(status.value(), exception.getMessage()));
    }
}
