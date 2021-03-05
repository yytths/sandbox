package sandbox.springbootapi.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sandbox.springbootapi.controller.response.Response;
import sandbox.springbootapi.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                           HttpHeaders headers, HttpStatus status,
                                                           WebRequest request) {
    if (!(body instanceof Response<?>)) {
      Response<String> response = new Response<>();
      response.setPayload(status.getReasonPhrase());
      body = response;
    }

    return new ResponseEntity<>(body, headers, status);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
    HttpHeaders headers = new HttpHeaders();
    Response<String> body = Response.notFound();
    body.setPayload(ex.getMessage());
    HttpStatus status = HttpStatus.NOT_FOUND;

    return this.handleExceptionInternal(ex, body, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                 HttpHeaders headers,
                                                                 HttpStatus status,
                                                                 WebRequest request) {
    Response<String> body = Response.notFound();
    body.setPayload(ex.getMessage());
    return this.handleExceptionInternal(ex, body, headers, status, request);
  }
}
