package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ReservationRepository rr) {
        return args -> {
            Arrays.asList("Les,Josh,Phil,Saasha,Peter".split(",")).forEach(n -> rr.save(new Reservation(n)));
            rr.findAll().forEach(System.out::println);
            System.out.println();
            rr.findByReservationName("Les").forEach(System.out::println);
        };
        /*return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {

			}
		};*/
    }
}

// Make REST controller
//@RestController
//class ReservationRestController {
//    @RequestMapping("/reservations")
//    Collection<Reservation> reservations() {
//        return this.reservationRepository.findAll();
//    }
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//}


@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {

    //	@Query("select * from reservations where reservation_name = :rn")
    Collection<Reservation> findByReservationName(@Param("rn") String rn);
}

@Entity
class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    private String reservationName;

    public Reservation() {
    }

    public Reservation(String reservationName) {
        this.reservationName = reservationName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation{");
        sb.append("id=").append(id);
        sb.append(", reservationName='").append(reservationName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public String getReservationName() {
        return reservationName;
    }
}