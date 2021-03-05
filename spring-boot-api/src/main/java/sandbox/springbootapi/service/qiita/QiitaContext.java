package sandbox.springbootapi.service.qiita;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "qiita")
public class QiitaContext {
  private String url;
}
