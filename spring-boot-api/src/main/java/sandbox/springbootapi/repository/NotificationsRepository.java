package sandbox.springbootapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandbox.springbootapi.entity.Notifications;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Integer> {
}
