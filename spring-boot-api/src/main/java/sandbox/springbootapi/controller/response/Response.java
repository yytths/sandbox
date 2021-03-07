package sandbox.springbootapi.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
  private Status status;
  private T payload;

  public static <T> Response<T> ok() {
    Response<T> response = new Response<>();
    response.setStatus(Status.OK);
    return response;
  }

  public static <T> Response<T> badRequest() {
    Response<T> response = new Response<>();
    response.setStatus(Status.BAD_REQUEST);
    return response;
  }

  public static <T> Response<T> notFound() {
    Response<T> response = new Response<>();
    response.setStatus(Status.NOT_FOUND);
    return response;
  }

  public static <T> Response<T> internalServerError() {
    Response<T> response = new Response<>();
    response.setStatus(Status.INTERNAL_SERVER_ERROR);
    return response;
  }

  public enum Status {
    OK, BAD_REQUEST, NOT_FOUND, INTERNAL_SERVER_ERROR
  }
}
