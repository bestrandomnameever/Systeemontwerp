package be.ugent.student.reception.application_logic;

import be.ugent.student.reception.domain.CheckInSaga;
import be.ugent.student.reception.domain.HospitalStayStatus;
import be.ugent.student.reception.exceptions.BookedHospitalStayNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import be.ugent.student.reception.domain.HospitalStay;
import be.ugent.student.reception.persistence.HospitalStayRepository;

@Component
public class ReceptionService {
	@Autowired
	private HospitalStayRepository hospitalStayRepository;
	@Autowired
	private CheckInSaga checkInSaga;
		
	
	//----------------------------------------
	//------       Lab session 1        ------
    public Iterable<HospitalStay> getHospitalStays() {
        return hospitalStayRepository.findAll();
    }
	
	public HospitalStay getPatientBooking(@RequestParam String patientId) {
		return hospitalStayRepository.findByPatientIdAndStatusBooked(patientId);				
	}
	//------------------------------------------
	
	public void setBookedHospitalStayStatusToPending(String patientId) {
		HospitalStay stay = hospitalStayRepository.findByPatientIdAndStatusBooked(patientId);
		stay.setStatus(HospitalStayStatus.CHECK_IN_PENDING);
		hospitalStayRepository.save(stay);
	}

	public void checkIn(String patientId) throws BookedHospitalStayNotFoundException {
    	HospitalStay stay = hospitalStayRepository.findByPatientIdAndStatusBooked(patientId);
    	if (stay == null) {
			throw new BookedHospitalStayNotFoundException();
		} else {
			checkInSaga.startPatientCheckInSaga(stay);
		}
	}
}
