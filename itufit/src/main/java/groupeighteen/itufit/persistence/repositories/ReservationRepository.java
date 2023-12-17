package groupeighteen.itufit.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import groupeighteen.itufit.domain.reservation.Reservation;

import java.time.LocalDateTime;
import java.util.List;



public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    List<Reservation> findByStartTimeAndFacilityId(LocalDateTime startTime, Long facilityId);

}
