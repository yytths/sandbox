package sandbox.springbootapi.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sandbox.springbootapi.controller.response.Response;
import sandbox.springbootapi.service.qiita.QiitaServiceClient;
import sandbox.springbootapi.service.qiita.QiitaUserResponse;

@RestController
@RequiredArgsConstructor
public class QiitaController {
  private final QiitaServiceClient client;

  @GetMapping("/qiita/{userId}")
  public Response<QiitaUserResponse> getId(@PathVariable("userId") String userId) {
    ResponseEntity<QiitaUserResponse> item = this.client.getUserExchange(userId);
    Response<QiitaUserResponse> response = Response.ok();
    response.setPayload(item.getBody());

    return response;
  }
}
