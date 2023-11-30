package za.co.wallacevanzyl.agencybooking.dao;

import org.springframework.stereotype.Component;
import za.co.wallacevanzyl.agencybooking.domain.Reservation;
import za.co.wallacevanzyl.agencybooking.dto.ReservationDto;
import za.co.wallacevanzyl.agencybooking.mapper.ReservationMapper;
import za.co.wallacevanzyl.agencybooking.repositories.ReservationRepository;

import java.util.List;

@Component
public class ReservationDaoImpl implements ReservationDao {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationDaoImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public long getReservationCount() {
        return reservationRepository.count();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public void makeBooking(ReservationDto reservationDto) {
        reservationRepository.save(reservationMapper.reservationDtoToReservation(reservationDto));
    }

    @Override
    public void updateBooking(long id, ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        reservationMapper.updateReservationFromDto(reservationDto, reservation);
        reservationRepository.save(reservation);
    }

    @Override
    public void cancelBooking(long id) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        reservationRepository.deleteById(reservation.getId());
    }

}
