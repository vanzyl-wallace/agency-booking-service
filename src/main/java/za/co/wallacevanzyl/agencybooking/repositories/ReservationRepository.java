package za.co.wallacevanzyl.agencybooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.wallacevanzyl.agencybooking.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
