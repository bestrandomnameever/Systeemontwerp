package be.ugent.student.patient_management.application_logic;

import be.ugent.student.patient_management.domain.Patient;
import be.ugent.student.patient_management.persistence.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientManagementService {
    @Autowired
    PatientRepository repository;

    public Iterable<Patient> getPatients() {
        return repository.findAll();
    }

    public Optional<Patient> getPatientWithSsn(String ssn) {
        return repository.findById(ssn);
    }

    public Iterable<Patient> getPatientsWithFirstName(String firstName) {
        return repository.getPatientsByFirstName(firstName);
    }

    public Iterable<Patient> getYoungPatients() {
        return repository.getPatientsByAgeBelow21();
    }

    public void addPatient(Patient patient) {
        repository.save(patient);
    }
}
