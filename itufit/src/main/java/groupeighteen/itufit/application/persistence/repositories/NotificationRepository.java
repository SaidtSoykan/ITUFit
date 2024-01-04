package groupeighteen.itufit.application.persistence.repositories;

import groupeighteen.itufit.domain.notification.Notification;
import groupeighteen.itufit.domain.user.AdminKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

