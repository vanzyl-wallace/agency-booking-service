package za.co.wallacevanzyl.agencybooking.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import za.co.wallacevanzyl.agencybooking.domain.Reservation;
import za.co.wallacevanzyl.agencybooking.repositories.ReservationRepository;

import java.math.BigInteger;
import java.util.Date;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final ReservationRepository reservationRepository;

    public DataInitializer(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing data.");
        reservationRepository.deleteAll();

        Reservation reservation = new Reservation("Jack", "Reaper", new BigInteger("3853499838"), new BigInteger("9519146286199"), 204, new Date(), new Date());
        Reservation reservationSaved = reservationRepository.save(reservation);
        log.info("Initialized reservation id: {}", reservationSaved.getId());
    }

}
