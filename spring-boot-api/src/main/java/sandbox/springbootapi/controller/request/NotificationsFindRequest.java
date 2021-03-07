package sandbox.springbootapi.controller.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

@Getter
public class NotificationsFindRequest {

  @Min(value = 1)
  @Max(value = 100)
  private Integer limit;
  @Min(value = 0)
  private Integer offset;

  public NotificationsFindRequest() {
    // デフォルト値を投入
    this.limit = 30;
    this.offset = 0;
  }

  public void setLimit(Integer limit) {
    if (ObjectUtils.isEmpty(limit)) {
      return;
    }
    this.limit = limit;
  }

  public void setOffset(Integer offset) {
    if (ObjectUtils.isEmpty(offset)) {
      return;
    }
    this.offset = offset;
  }
}
