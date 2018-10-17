package be.ugent.student.patient_management.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.util.Date;

@Entity
public class Patient {
    @Id
    private String ssn;
    private String lastName;
    private String firstName;
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    public Patient() {
        this.ssn = "";
        this.lastName = "";
        this.firstName = "";
        this.birthdate = Date.from(Instant.now());
    }

    public Patient(String ssn, String lastName, String firstName, Date birthdate) {
        this.ssn = ssn;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        return build
                .append('(')
                .append(this.ssn)
                .append(") ")
                .append(this.firstName)
                .append(' ')
                .append(this.lastName)
                .append('(')
                .append(this.birthdate.getDay())
                .append('/')
                .append(this.birthdate.getMonth())
                .append('/')
                .append(this.birthdate.getYear())
                .append(')')
                .toString();
    }
}
