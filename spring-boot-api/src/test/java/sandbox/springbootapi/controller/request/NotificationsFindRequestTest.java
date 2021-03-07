package sandbox.springbootapi.controller.request;


import static org.assertj.core.api.Assertions.assertThat;


import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotificationsFindRequestTest {

  private static Validator validator;

  @BeforeAll
  static void setup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  public void testNormalDefault() {
    NotificationsFindRequest request = new NotificationsFindRequest();

    Set<ConstraintViolation<NotificationsFindRequest>> validation = validator.validate(request);

    assertThat(request.getLimit()).isEqualTo(30);
    assertThat(request.getOffset()).isEqualTo(0);
    assertThat(validation.size()).isEqualTo(0);
  }

  @ParameterizedTest
  @CsvSource({
      "1, 0",
      "100, 0",
      "100, 1"
  })
  public void testNormal(int limit, int offset) {
    NotificationsFindRequest request = new NotificationsFindRequest();
    request.setLimit(limit);
    request.setOffset(offset);

    Set<ConstraintViolation<NotificationsFindRequest>> validation = validator.validate(request);

    assertThat(validation.size()).isEqualTo(0);
  }

  @Test
  public void testAbnormalMinLimit() {
    NotificationsFindRequest request = new NotificationsFindRequest();
    request.setLimit(0);

    Set<ConstraintViolation<NotificationsFindRequest>> validation = validator.validate(request);

    assertThat(validation.size()).isEqualTo(1);
    validation.forEach(violation -> assertThat(violation.getMessage()).isEqualTo("1 以上の値にしてください"));
  }

  @Test
  public void testAbnormalMaxLimit() {
    NotificationsFindRequest request = new NotificationsFindRequest();
    request.setLimit(101);

    Set<ConstraintViolation<NotificationsFindRequest>> validation = validator.validate(request);

    assertThat(validation.size()).isEqualTo(1);
    validation.forEach(violation -> assertThat(violation.getMessage()).isEqualTo("100 以下の値にしてください"));
  }

  @Test
  public void testAbnormalOffset() {
    NotificationsFindRequest request = new NotificationsFindRequest();
    request.setOffset(-1);

    Set<ConstraintViolation<NotificationsFindRequest>> validation = validator.validate(request);

    assertThat(validation.size()).isEqualTo(1);
    validation.forEach(violation -> assertThat(violation.getMessage()).isEqualTo("0 以上の値にしてください"));
  }

}