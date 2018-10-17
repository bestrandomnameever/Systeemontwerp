package be.ugent.student.reception.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannels {
    String ASSIGN_BED = "assign_bed";
    String CHECK_IN_RESULT = "check_in_result";

    @Output(ASSIGN_BED)
    MessageChannel bedAssignment();

    @Output(CHECK_IN_RESULT)
    MessageChannel checkInResult();
}
