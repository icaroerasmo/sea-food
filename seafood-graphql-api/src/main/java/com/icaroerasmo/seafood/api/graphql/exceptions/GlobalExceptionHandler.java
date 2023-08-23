package com.icaroerasmo.seafood.api.graphql.exceptions;

import com.icaroerasmo.seafood.api.graphql.dto.ErrorMessageDTO;
import com.icaroerasmo.seafood.business.exceptions.DataInconsistencyException;
import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.business.exceptions.KafkaMessagesException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataInconsistencyException.class)
    ResponseEntity<ErrorMessageDTO> dataInconsistencyException(DataInconsistencyException exception) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorMessageDTO(status.value(), exception.getMessage()));
    }
    @ExceptionHandler(KafkaMessagesException.class)
    ResponseEntity<ErrorMessageDTO> kafkaMessagesException(KafkaMessagesException exception) {
        log.info("Communication to Kafka failed: {}", exception);
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(new ErrorMessageDTO(status.value(), "Unexpected error occurred"));
    }
    @ExceptionHandler(DataNotFoundException.class)
    ResponseEntity<ErrorMessageDTO> dataNotFoundException(DataNotFoundException exception) {
        log.info("Data not found: {}", exception);
        final HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorMessageDTO(status.value(), exception.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorMessageDTO> unexpected(Exception exception) {
        log.info("Unexpected error occured: {}", exception);
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(new ErrorMessageDTO(status.value(), "Unexpected error occurred"));
    }
}
