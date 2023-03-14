package dev.be.loansystem.exception;

import dev.be.loansystem.dto.ResponseDTO;
import dev.be.loansystem.dto.ResultObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class APIExceptionHandler extends RuntimeException {

    @ExceptionHandler(BaseException.class)
    protected ResponseDTO<ResultObject> handlerBaseException(BaseException e, HttpServletRequest request, HttpServletResponse response){
        log.error(e.getMessage(), e);
        return new ResponseDTO<>(e);
    }
}
