package sandbox.springbootapi.controller.api;

import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import sandbox.springbootapi.controller.request.NotificationsFindRequest;
import sandbox.springbootapi.controller.response.Response;
import sandbox.springbootapi.entity.Notifications;
import sandbox.springbootapi.repository.NotificationsRepository;

@RestController
@RequiredArgsConstructor
public class NotificationsController {
  @NonNull
  private final NotificationsRepository repository;

  @GetMapping("/notifications")
  public Response<List<Notifications>> list(
      @ModelAttribute @Valid NotificationsFindRequest request) {
    PageRequest pageRequest = PageRequest.of(
        request.getOffset(),
        request.getLimit(),
        Sort.by(Sort.Direction.ASC, "id"));
    Page<Notifications> notifications = repository.findAll(pageRequest);
    Response<List<Notifications>> response = Response.ok();
    response.setPayload(notifications.getContent());

    return response;
  }
}
