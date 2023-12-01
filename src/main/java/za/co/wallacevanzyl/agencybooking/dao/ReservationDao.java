package za.co.wallacevanzyl.agencybooking.dao;

import org.springframework.data.domain.Pageable;
import za.co.wallacevanzyl.agencybooking.domain.Reservation;
import za.co.wallacevanzyl.agencybooking.dto.ReservationDto;

import java.util.List;

public interface ReservationDao {
    long getReservationCount();

    List<Reservation> getAllReservations();

    List<Reservation> getAllReservations(Pageable pageable);

    void makeBooking(ReservationDto reservationDto);

    void updateBooking(long id, ReservationDto reservationDto);

    void cancelBooking(long id);
}
