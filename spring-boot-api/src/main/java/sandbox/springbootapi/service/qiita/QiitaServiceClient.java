package sandbox.springbootapi.service.qiita;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sandbox.springbootapi.handler.RestTemplateResponseErrorHandler;

@Service
public class QiitaServiceClient {
  private final RestTemplate restTemplate;
  private final String userUrl = "/api/v2/users/{userId}";

  public QiitaServiceClient(RestTemplateBuilder builder,
                            QiitaContext qiitaContext) {

    this.restTemplate = builder
        .rootUri(qiitaContext.getUrl())
        .errorHandler(new RestTemplateResponseErrorHandler())
        .build();
  }

  public QiitaUserResponse getUserForObject(String userId) {
    // getForObject: レスポンスボディだけ取得したい場合.
    return this.restTemplate.getForObject(userUrl, QiitaUserResponse.class, userId);
  }

  public ResponseEntity<QiitaUserResponse> getUserForEntity(String userId) {
    // getForEntity: HTTPステータスコード、レスポンスヘッダ、レスポンスボディを取得する必要がある場合
    return this.restTemplate.getForEntity(userUrl, QiitaUserResponse.class, userId);
  }

  public ResponseEntity<QiitaUserResponse> getUserExchange(String userId) {
    RequestEntity<Void> request = RequestEntity
        .get(userUrl, userId)
        .build();
    // exchange: リクエストヘッダを指定する必要がある場合
    return this.restTemplate.exchange(request, QiitaUserResponse.class);
  }
}
