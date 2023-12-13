package groupeighteen.itufit.application.persistence.repositories;

import groupeighteen.itufit.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
