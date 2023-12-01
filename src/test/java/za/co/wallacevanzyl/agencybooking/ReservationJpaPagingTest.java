package za.co.wallacevanzyl.agencybooking;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = {"za.co.wallacevanzyl.agencybooking.bootstrap"})
class ReservationJpaPagingTest {

    @Autowired
    ReservationRepository reservationRepository;

    ReservationDao reservationDao;

    @BeforeEach
    void setup() {
        reservationDao = new ReservationDaoImpl(reservationRepository, ReservationMapper.INSTANCE);
    }

    @Order(1)
    @Test
    void when_data_initialize_then_1_record() {
        long countBefore = reservationDao.getReservationCount();
        assertThat(countBefore).isEqualTo(1);
    }

    /**
     * Paging Tests
     */
    @Order(2)
    @Test
    void when_paging_all_reservations_page_1() {
        setupPagingData();

        long countAfter = reservationDao.getReservationCount();
        assertThat(countAfter).isEqualTo(31);

        List<Reservation> reservationList = reservationDao.getAllReservations(PageRequest.of(0, 10));
        assertThat(reservationList).isNotNull();
        assertThat(reservationList.size()).isEqualTo(10);
        assertThat(reservationList.get(0).getRoomNumber()).isEqualTo(204);
        assertThat(reservationList.get(9).getRoomNumber()).isEqualTo(109);

    }

    @Order(3)
    @Test
    void when_paging_all_reservations_page_2() {
        setupPagingData();

        long countAfter = reservationDao.getReservationCount();
        assertThat(countAfter).isEqualTo(31);

        List<Reservation> reservationList = reservationDao.getAllReservations(PageRequest.of(1, 10));
        assertThat(reservationList).isNotNull();
        assertThat(reservationList.size()).isEqualTo(10);
        assertThat(reservationList.get(0).getRoomNumber()).isEqualTo(200);
        assertThat(reservationList.get(9).getRoomNumber()).isEqualTo(504);

    }

    @Order(4)
    @Test
    void when_paging_all_reservations_page_10() {
        setupPagingData();

        long countAfter = reservationDao.getReservationCount();
        assertThat(countAfter).isEqualTo(31);

        List<Reservation> reservationList = reservationDao.getAllReservations(PageRequest.of(10, 100));
        assertThat(reservationList).isNotNull();
        assertThat(reservationList.size()).isEqualTo(0);

    }

    private void setupPagingData() {
        ReservationDto reservation = new ReservationDto("Mik", "Jagger", new BigInteger("3853779838"), new BigInteger("9519146666199"), 101, new Date(), new Date());
        reservationDao.makeBooking(reservation);

        ReservationDto reservation1 = new ReservationDto("Tom", "Denver", new BigInteger("3853779838"), new BigInteger("9519146666199"), 102, new Date(), new Date());
        reservationDao.makeBooking(reservation1);

        ReservationDto reservation2 = new ReservationDto("Jack", "Ripper", new BigInteger("3853779838"), new BigInteger("9519146666199"), 103, new Date(), new Date());
        reservationDao.makeBooking(reservation2);

        ReservationDto reservation3 = new ReservationDto("Nicole", "Kidman", new BigInteger("3853779838"), new BigInteger("9519146666199"), 104, new Date(), new Date());
        reservationDao.makeBooking(reservation3);

        ReservationDto reservation4 = new ReservationDto("Thomos", "Engine", new BigInteger("3853779838"), new BigInteger("9519146666199"), 105, new Date(), new Date());
        reservationDao.makeBooking(reservation4);

        ReservationDto reservation5 = new ReservationDto("William", "Wallace", new BigInteger("3853779838"), new BigInteger("9519146666199"), 106, new Date(), new Date());
        reservationDao.makeBooking(reservation5);

        ReservationDto reservation6 = new ReservationDto("Sue", "Ellen", new BigInteger("3853779838"), new BigInteger("9519146666199"), 107, new Date(), new Date());
        reservationDao.makeBooking(reservation6);

        ReservationDto reservation7 = new ReservationDto("Alan", "Bradley", new BigInteger("3853779838"), new BigInteger("9519146666199"), 108, new Date(), new Date());
        reservationDao.makeBooking(reservation7);

        ReservationDto reservation8 = new ReservationDto("Dug", "Digger", new BigInteger("3853779838"), new BigInteger("9519146666199"), 109, new Date(), new Date());
        reservationDao.makeBooking(reservation8);

        ReservationDto reservation9 = new ReservationDto("Cheslin", "Kolbe", new BigInteger("3853779838"), new BigInteger("9519146666199"), 200, new Date(), new Date());
        reservationDao.makeBooking(reservation9);

        ReservationDto reservation10 = new ReservationDto("Theo", "Loubser", new BigInteger("3853779838"), new BigInteger("9519146666199"), 202, new Date(), new Date());
        reservationDao.makeBooking(reservation10);

        ReservationDto reservation11 = new ReservationDto("Gillian", "Loubser", new BigInteger("3853779838"), new BigInteger("9519146666199"), 203, new Date(), new Date());
        reservationDao.makeBooking(reservation11);

        ReservationDto reservation12 = new ReservationDto("Kim", "Van Zyl", new BigInteger("3853779838"), new BigInteger("9519146666199"), 206, new Date(), new Date());
        reservationDao.makeBooking(reservation12);

        ReservationDto reservation13 = new ReservationDto("Quinton", "VD Merwe", new BigInteger("3853779838"), new BigInteger("9519146666199"), 207, new Date(), new Date());
        reservationDao.makeBooking(reservation13);

        ReservationDto reservation14 = new ReservationDto("Paul", "Van Zyl", new BigInteger("3853779838"), new BigInteger("9519146666199"), 208, new Date(), new Date());
        reservationDao.makeBooking(reservation14);

        ReservationDto reservation15 = new ReservationDto("Jaco", "Theron", new BigInteger("3853779838"), new BigInteger("9519146666199"), 501, new Date(), new Date());
        reservationDao.makeBooking(reservation15);

        ReservationDto reservation16 = new ReservationDto("Ronald", "Mc Donal", new BigInteger("3853779838"), new BigInteger("9519146666199"), 502, new Date(), new Date());
        reservationDao.makeBooking(reservation16);

        ReservationDto reservation17 = new ReservationDto("Chester", "Williams", new BigInteger("3853779838"), new BigInteger("9519146666199"), 503, new Date(), new Date());
        reservationDao.makeBooking(reservation17);

        ReservationDto reservation18 = new ReservationDto("Michael", "Angelo", new BigInteger("3853779838"), new BigInteger("9519146666199"), 504, new Date(), new Date());
        reservationDao.makeBooking(reservation18);

        ReservationDto reservation19 = new ReservationDto("Tomos", "Edison", new BigInteger("3853779838"), new BigInteger("9514857166199"), 505, new Date(), new Date());
        reservationDao.makeBooking(reservation19);

        ReservationDto reservation20 = new ReservationDto("Dick", "Tracey", new BigInteger("3878459838"), new BigInteger("9519146361299"), 506, new Date(), new Date());
        reservationDao.makeBooking(reservation20);

        ReservationDto reservation21 = new ReservationDto("Keanu", "Reeves", new BigInteger("3853483538"), new BigInteger("9519199996199"), 507, new Date(), new Date());
        reservationDao.makeBooking(reservation21);

        ReservationDto reservation22 = new ReservationDto("Dirty", "Harry", new BigInteger("7985679838"), new BigInteger("9519261548199"), 508, new Date(), new Date());
        reservationDao.makeBooking(reservation22);

        ReservationDto reservation23 = new ReservationDto("Mel", "Gibson", new BigInteger("3818499838"), new BigInteger("9519154866199"), 509, new Date(), new Date());
        reservationDao.makeBooking(reservation23);

        ReservationDto reservation24 = new ReservationDto("Barry", "Allan", new BigInteger("3484879838"), new BigInteger("9559486666199"), 601, new Date(), new Date());
        reservationDao.makeBooking(reservation24);

        ReservationDto reservation25 = new ReservationDto("Iris", "West", new BigInteger("3853111138"), new BigInteger("9519636266199"), 602, new Date(), new Date());
        reservationDao.makeBooking(reservation25);

        ReservationDto reservation26 = new ReservationDto("Ralph", "Dibny", new BigInteger("3978779838"), new BigInteger("9519146669749"), 603, new Date(), new Date());
        reservationDao.makeBooking(reservation26);

        ReservationDto reservation27 = new ReservationDto("Harrison", "Wells", new BigInteger("3853779548"), new BigInteger("9519178466199"), 604, new Date(), new Date());
        reservationDao.makeBooking(reservation27);

        ReservationDto reservation28 = new ReservationDto("Cathy", "Nieilson", new BigInteger("3856549838"), new BigInteger("9519777666199"), 605, new Date(), new Date());
        reservationDao.makeBooking(reservation28);

        ReservationDto reservation29 = new ReservationDto("Liam", "Jordan", new BigInteger("3853444838"), new BigInteger("9519174566199"), 607, new Date(), new Date());
        reservationDao.makeBooking(reservation29);

    }
}
