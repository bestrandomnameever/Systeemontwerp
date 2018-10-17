package be.ugent.student.patient_management;

import be.ugent.student.patient_management.domain.Patient;
import be.ugent.student.patient_management.persistence.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientManagementApplication {

    private static final Logger log = LoggerFactory.getLogger(PatientManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PatientManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PatientRepository repository) {

        return(args) -> {
            repository.save(new Patient("432-47-7153", "Cedric", "Vanhaverbeke", Date.from(Instant.now())));
            repository.save(new Patient("432-47-7154", "Simon", "DG", Date.from(Instant.now())));
            repository.save(new Patient("432-47-7155", "Anouk", "Windey", new Date(2000,7,24)));
            repository.save(new Patient("432-47-7156", "Kobe", "Buysse", new Date(2000,7,24)));
            repository.save(new Patient("432-47-7157", "Jansens", "Yoshi", new Date(1995,7,24)));
            repository.save(new Patient("432-47-7158pos", "Baert", "Anthony", new Date(1995,7,24)));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Patient customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("-------------------------------");

            log.info("Customers found that are younger than 21:");
            log.info("-------------------------------");
            for (Patient customer : repository.getPatientsByAgeBelow21()) {
                log.info(customer.toString());
            }
            log.info("-------------------------------");

            log.info("Customers found with last name Baert:");
            log.info("-------------------------------");
            for (Patient customer : repository.getPatientsByLastName("Baert")) {
                log.info(customer.toString());
            }
            log.info("-------------------------------");

            log.info("Customers found with last name strating with 'J':");
            log.info("-------------------------------");
            for (Patient customer : repository.getPatientsByLastNameStartsWithJ()) {
                log.info(customer.toString());
            }
            log.info("-------------------------------");
        };

    }
}
