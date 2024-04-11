package francescoandrisani.u5w2d3.exceptions;

import francescoandrisani.u5w2d3.payloads.ErrorsPayload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload hendleNotFound(NotFound ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }
}
