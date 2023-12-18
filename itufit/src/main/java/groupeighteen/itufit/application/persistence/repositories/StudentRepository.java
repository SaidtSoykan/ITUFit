package groupeighteen.itufit.application.persistence.repositories;

import groupeighteen.itufit.domain.user.Admin;
import groupeighteen.itufit.domain.user.Student;
import groupeighteen.itufit.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
