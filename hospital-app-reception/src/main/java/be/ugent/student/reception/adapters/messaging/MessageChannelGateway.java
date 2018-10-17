package be.ugent.student.reception.adapters.messaging;

import be.ugent.student.reception.domain.HospitalStay;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageChannelGateway {

    @Gateway(requestChannel = ProducerChannels.ASSIGN_BED)
    void assignBed(HospitalStay stay);

    @Gateway(requestChannel = ProducerChannels.CHECK_IN_RESULT)
    void emitHospitalCheckInCompleted(HospitalStay stay);
}
