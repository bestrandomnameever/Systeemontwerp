package be.ugent.student.finance_service;

import be.ugent.student.finance_service.domain.Invoice;
import be.ugent.student.finance_service.persistence.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(InvoiceRepository repository) {
        return (args) -> {

            repository.deleteAll();

            Invoice invoice = new Invoice("goeienID", "kjfkljsdkljfklsd");
            invoice.addListItem("bed", 4786);
            invoice.addListItem("dokter",456787654);
            invoice.pay();
            repository.save(invoice);

            Invoice invoice2 = new Invoice("dslkjflkdsjfjdskjfsld", "uytureoutoieru");
            invoice2.addListItem("chemo", 2);
            invoice2.addListItem("ne plakker",23);
            invoice2.pay();
            repository.save(invoice2);

            Invoice invoice3 = new Invoice("goeienID", "dsjfkjdsjfdsklfjdsdsjfdslfjsd");
            invoice3.addListItem("hart", 4000);
            invoice3.addListItem("koffie",4001);
            repository.save(invoice3);

            log.info("Invoices found with findAll():");
            log.info("-------------------------------");
            for (Invoice voice : repository.findAll()) {
                log.info(voice.toString());
            }
            log.info("");


            log.info("Invoices found with containing a item with name 'bed':");
            log.info("-------------------------------");
            for (Invoice voice : repository.getInvoicesByHasItemWithName("bed")) {
                log.info(voice.toString());
            }
            log.info("");

            log.info("Invoices found with containing a item with a cost higher than 5000");
            log.info("-------------------------------");
            for (Invoice voice : repository.getInvoicesByHasItemWithCostHigherThan(5000)) {
                log.info(voice.toString());
            }
            log.info("");

            log.info("Invoices found with with PatientID 'goeienID' and that were unpaid");
            log.info("-------------------------------");
            for (Invoice voice : repository.getUnpaidInvoicesForPatientId("goeienID")) {
                log.info(voice.toString());
            }
            log.info("");
        };
    }
}
