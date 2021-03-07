package sandbox.springbootapi.service.qiita;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

@ExtendWith(SpringExtension.class)
@RestClientTest(QiitaServiceClient.class)
@EnableConfigurationProperties(value = QiitaContext.class)
class QiitaServiceClientTest {
  @Autowired
  private QiitaServiceClient client;
  @Autowired
  private MockRestServiceServer server;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void getUserForObjectTest() throws Exception {
    // Arrange
    QiitaUserResponse item = new QiitaUserResponse();
    item.setId("id");
    item.setDescription("description");

    // Act
    this.server.expect(requestTo("/api/v2/users/id"))
        .andRespond(withSuccess(objectMapper.writeValueAsString(item), MediaType.APPLICATION_JSON));
    QiitaUserResponse actual = this.client.getUserForObject("id");

    // Assertion
    assertThat(actual.getId()).isEqualTo("id");
    assertThat(actual.getDescription()).isEqualTo("description");
  }

  @Test
  public void getUserForEntityTest() throws Exception {
    // Arrange
    QiitaUserResponse item = new QiitaUserResponse();
    item.setId("id");
    item.setDescription("description");

    // Act
    this.server.expect(requestTo("/api/v2/users/id"))
        .andRespond(withSuccess(objectMapper.writeValueAsString(item), MediaType.APPLICATION_JSON));
    ResponseEntity<QiitaUserResponse> actual = this.client.getUserForEntity("id");

    // Assertion
    assertThat(Objects.requireNonNull(actual.getBody()).getId()).isEqualTo("id");
    assertThat(actual.getBody().getDescription()).isEqualTo("description");
  }

  @Test
  public void getUserExchangeTest() throws Exception {
    // Arrange
    QiitaUserResponse item = new QiitaUserResponse();
    item.setId("id");
    item.setDescription("description");

    // Act
    this.server.expect(requestTo("/api/v2/users/id"))
        .andRespond(withSuccess(objectMapper.writeValueAsString(item), MediaType.APPLICATION_JSON));
    ResponseEntity<QiitaUserResponse> actual = this.client.getUserExchange("id");

    // Assertion
    assertThat(Objects.requireNonNull(actual.getBody()).getId()).isEqualTo("id");
    assertThat(actual.getBody().getDescription()).isEqualTo("description");
  }

}