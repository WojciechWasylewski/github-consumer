package com.example.github.controller.error;

import com.example.github.controller.dto.response.ErrorGithubResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2
public class GithubErrorHandler {

    @ExceptionHandler(GithubUserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorGithubResponseDto> handleException(GithubUserNotFoundException exception) {
        log.error("Provided user does not exist on GitHub");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorGithubResponseDto(HttpStatus.NOT_FOUND,exception.getMessage()));
    }

    @ExceptionHandler(InvalidFormatResponseError.class)
    @ResponseBody
    public ResponseEntity<ErrorGithubResponseDto> handleException(InvalidFormatResponseError exception) {
        log.error("Requested media type 'application/xml' is not supported");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorGithubResponseDto(HttpStatus.NOT_ACCEPTABLE, exception.getMessage()));
    }
}
