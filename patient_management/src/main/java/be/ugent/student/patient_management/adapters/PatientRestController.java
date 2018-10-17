package be.ugent.student.patient_management.adapters;

import be.ugent.student.patient_management.application_logic.PatientManagementService;
import be.ugent.student.patient_management.domain.Patient;
import javafx.scene.shape.PathElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientRestController {

    @Autowired
    PatientManagementService service;

    @RequestMapping("/patient")
    public Iterable<Patient> getPatients() {
        return service.getPatients();
    }

    @RequestMapping("/patient/{ssn}")
    public Optional<Patient> getPatientBySsn(@PathVariable(value = "ssn") String ssn) {
        return service.getPatientWithSsn(ssn);
    }

    @RequestMapping("/patient/search")
    public Iterable<Patient> getPatientsByFirstName(@RequestParam(value = "name") String name) {
        return service.getPatientsWithFirstName(name);
    }

    @RequestMapping("/patient/young_patients")
    public Iterable<Patient> getYoungPatients() {
        return service.getYoungPatients();
    }

    @RequestMapping(value = "/patient", method = RequestMethod.PUT)
    public Iterable<Patient> addPatient(@RequestBody Patient patient) {
        service.addPatient(patient);
        return getPatients();
    }
}
