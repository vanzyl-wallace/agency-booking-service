package za.co.wallacevanzyl.agencybooking;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import za.co.wallacevanzyl.agencybooking.dao.ReservationDao;
import za.co.wallacevanzyl.agencybooking.dao.ReservationDaoImpl;
import za.co.wallacevanzyl.agencybooking.domain.Reservation;
import za.co.wallacevanzyl.agencybooking.dto.ReservationDto;
import za.co.wallacevanzyl.agencybooking.mapper.ReservationMapper;
import za.co.wallacevanzyl.agencybooking.repositories.ReservationRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest//This will not load the Bootstrapped DataInitializer context i.e. in memory will be empty. Tests against the database context only (DB, Entities etc). Brings up hibernate and autoconfigures in memory db etc.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//This prevents Spring Boot from overriding the MYSQL properties specified in application-local.properties profile with an h2 db instance
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = {"za.co.wallacevanzyl.agencybooking.bootstrap"})// This will bring in the DataInitializer into the test and execute its functionality.
class ReservationJpaTest {

    @Autowired
    ReservationRepository reservationRepository;

    ReservationDao reservationDao;

    @BeforeEach
    void setup(){
        reservationDao = new ReservationDaoImpl(reservationRepository, ReservationMapper.INSTANCE);
    }

    @Order(1)
    @Test
    void when_data_initialize_then_1_record() {
        long countBefore = reservationDao.getReservationCount();
        assertThat(countBefore).isEqualTo(1);
    }

    @Commit
    @Order(2)
    @Test
    void when_make_reservation_then_2_records() {
        ReservationDto reservationDto = new ReservationDto("Jack", "Frost", new BigInteger("0742388727"), new BigInteger("8428235375288"), 104, new Date(), new Date());
        reservationDao.makeBooking(reservationDto);
        long countAfter = reservationDao.getReservationCount();
        assertThat(countAfter).isEqualTo(2);

    }

    @Commit
    @Order(3)
    @Test
    void when_remove_reservation_then_record() {
        List<Reservation> reservationList = reservationDao.getAllReservations();

        long id = reservationList.get(0).getId();
        reservationDao.cancelBooking(id);

        long countAfter = reservationDao.getReservationCount();
        assertThat(countAfter).isEqualTo(1);
    }

    @Commit
    @Order(4)
    @Test
    void when_get_all_reservations_then_1_record() {
        List<Reservation> reservationList = reservationDao.getAllReservations();
        assertThat(reservationList.size()).isEqualTo(1);
    }

    @Commit
    @Order(5)
    @Test
    void when_update_reservation() {
        List<Reservation> reservationList = reservationRepository.findAll();
        assertThat(reservationList.size()).isEqualTo(1);
        assertThat(reservationList.get(0).getRoomNumber()).isEqualTo(104);

        ReservationDto reservationDto = ReservationMapper.INSTANCE.reservationToReservationDto(reservationList.get(0));
        reservationDto.setRoomNumber(201);
        ReservationMapper.INSTANCE.updateReservationFromDto(reservationDto, reservationList.get(0));
        reservationRepository.save(reservationList.get(0));

        List<Reservation> reservationListUpdated = reservationRepository.findAll();
        assertThat(reservationListUpdated.get(0).getRoomNumber()).isEqualTo(201);
        assertThat(reservationList.size()).isEqualTo(1);
    }

}
