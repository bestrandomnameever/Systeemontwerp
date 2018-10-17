package be.ugent.student.finance_service.persistence;

import be.ugent.student.finance_service.domain.Invoice;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {
    // public List<Invoice>

    @Query(value = "{ 'items.name': ?0 }")
    List<Invoice> getInvoicesByHasItemWithName(String name);

    @Query(value = "{ 'items.cost' : { $gt: ?0 }}")
    List<Invoice> getInvoicesByHasItemWithCostHigherThan(Integer cost);

    @Query(value = "{ $and: [ { 'patientId': ?0 }, { 'status': 'Paid' } ] }")
    List<Invoice> getUnpaidInvoicesForPatientId(String patientId);
}
