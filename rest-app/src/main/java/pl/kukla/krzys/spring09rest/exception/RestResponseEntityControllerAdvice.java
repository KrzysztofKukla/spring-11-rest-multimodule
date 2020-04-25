package pl.kukla.krzys.spring09rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Krzysztof Kukla
 */
// this will be invoke when the following exceptions raise
//this is result provided for Web on UI
@ControllerAdvice
public class RestResponseEntityControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CategoryNotFoundException.class, CustomerNotFoundException.class})
    public ResponseEntity<Object> resourceNotFoundHandler(Exception exception, WebRequest webRequest) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource Not Found");
    }

}
