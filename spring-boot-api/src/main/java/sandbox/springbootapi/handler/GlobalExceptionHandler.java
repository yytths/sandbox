package sandbox.springbootapi.handler;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sandbox.springbootapi.controller.response.Response;
import sandbox.springbootapi.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  private final MessageSource messageSource;

  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                           HttpHeaders headers, HttpStatus status,
                                                           WebRequest request) {
    if (!(body instanceof Response<?>)) {
      Response<String> response;
      if (status.series() == CLIENT_ERROR) {
        response = Response.badRequest();
      } else if (status.series() == SERVER_ERROR) {
        response = Response.internalServerError();
      } else {
        response = new Response<>();
      }
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

  @Override
  protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
                                                       HttpStatus status, WebRequest request) {
    // クエリパラメータに対する入力値チェックエラー
    List<String> errorMessages = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(fieldError -> fieldError.getField() + ":" +
            messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()))
        .collect(Collectors.toList());

    Response<List<String>> body = Response.badRequest();
    body.setPayload(errorMessages);
    return this.handleExceptionInternal(ex, body, headers, status, request);
  }
}
