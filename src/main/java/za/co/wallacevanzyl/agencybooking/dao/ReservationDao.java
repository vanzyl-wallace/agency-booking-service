package za.co.wallacevanzyl.agencybooking.dao;

import za.co.wallacevanzyl.agencybooking.domain.Reservation;
import za.co.wallacevanzyl.agencybooking.dto.ReservationDto;

import java.util.List;

public interface ReservationDao {
    long getReservationCount();

    List<Reservation> getAllReservations();

    Reservation makeBooking(Reservation reservation);

    void updateBooking(long id, ReservationDto reservationDto);

    void cancelBooking(long id);
}
