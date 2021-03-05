package sandbox.springbootapi.handler;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;


import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import sandbox.springbootapi.exception.NotFoundException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return response.getStatusCode().series() == CLIENT_ERROR
        || response.getStatusCode().series() == SERVER_ERROR;
  }

  @Override
  public void handleError(URI url, HttpMethod method, ClientHttpResponse response)
      throws IOException {
    this.handleError(response);
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
      // handle SERVER_ERROR
    } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
      // handle CLIENT_ERROR
      if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
        throw new NotFoundException("api failed with 404.");
      }
    }
  }
}
