package be.ugent.student.reception.domain;

import be.ugent.student.reception.adapters.messaging.MessageChannelGateway;
import be.ugent.student.reception.application_logic.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckInSaga {

    @Autowired
    MessageChannelGateway gateway;
    @Autowired
    ReceptionService service;


    public void startPatientCheckInSaga(HospitalStay stay) {
        stay.setStatus(HospitalStayStatus.CHECK_IN_PENDING);
        service.setBookedHospitalStayStatusToPending(stay.getPatientId());
        gateway.assignBed(stay);
    }
}
