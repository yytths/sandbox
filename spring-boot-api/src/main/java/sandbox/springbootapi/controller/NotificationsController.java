package sandbox.springbootapi.controller;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sandbox.springbootapi.entity.Notifications;
import sandbox.springbootapi.repository.NotificationsRepository;

@RestController
@RequiredArgsConstructor
public class NotificationsController {
  @NonNull
  private NotificationsRepository repository;

  @GetMapping("/notifications")
  public List<Notifications> list() {
    return repository.findAll();
  }
}
