package za.co.wallacevanzyl.agencybooking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import za.co.wallacevanzyl.agencybooking.dao.ReservationDao;
import za.co.wallacevanzyl.agencybooking.domain.Reservation;
import za.co.wallacevanzyl.agencybooking.dto.ReservationDto;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.List;


@RestController
@RequestMapping("/v1/reservation")
public class HotelBookingController {

    private static final Logger log = LoggerFactory.getLogger(HotelBookingController.class);

    private final ReservationDao reservationDao;

    public HotelBookingController(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Secured("ROLE_GUEST")
    @GetMapping(value = "")
    public ResponseEntity<List<Reservation>> getReservations() {
        log.info("Retrieving all reservations.");
        List<Reservation> reservations = reservationDao.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseEntity<Reservation> makeReservation(@RequestBody Reservation reservation) {
        log.info("Making a reservation.");
        Reservation reservationCreated = reservationDao.makeBooking(reservation);
        return new ResponseEntity<>(reservationCreated, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseEntity<String> updateReservation(@PathVariable("id") long id, @RequestBody ReservationDto reservationDto) {
        log.info("Updating a reservation.");
        try {
            reservationDao.updateBooking(id, reservationDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EntityNotFoundException entityNotFoundException) {
            log.warn(entityNotFoundException.getMessage());
            return new ResponseEntity<>("Cannot update booking: Unable to find entity with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> removeReservation(@PathVariable("id") long id) {
        log.info("Cancelling a reservation.");
        try {
            reservationDao.cancelBooking(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException entityNotFoundException) {
            log.warn(entityNotFoundException.getMessage());
            return new ResponseEntity<>("Cannot cancel booking: Unable to find entity with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

}
