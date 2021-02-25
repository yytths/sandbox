package sandbox.springbootapi.controller.api;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
  public Response<List<Notifications>> list(@ModelAttribute NotificationsFindRequest request) {
    List<Notifications> notifications = repository.findAll();

    Response<List<Notifications>> response = Response.ok();
    response.setPayload(notifications);

    return response;
  }
}
