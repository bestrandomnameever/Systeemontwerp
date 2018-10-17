package be.ugent.student.patient_management.persistence;

import be.ugent.student.patient_management.domain.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient,String> {
    List<Patient> getPatientsByFirstName(String first);
    List<Patient> getPatientsByLastName(String last);

    @Query("select p from Patient p where p.lastName like 'J%'")
    List<Patient> getPatientsByLastNameStartsWithJ();

    @Query("select p from Patient p where TIMESTAMPDIFF(YEAR,NOW(),p.birthdate) < 21")
    List<Patient> getPatientsByAgeBelow21();
}
