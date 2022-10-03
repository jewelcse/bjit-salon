package com.bjit.salon.reservation.service.producer;


import com.bjit.salon.reservation.service.dto.producer.StaffActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.bjit.salon.reservation.service.util.ConstraintsUtil.STAFF_NEW_ACTIVITY_TOPIC;

@RequiredArgsConstructor
@Service
public class ReservationProducer {

    private final KafkaTemplate<String, StaffActivity> kafkaTemplate;

    public void createNewActivity(StaffActivity staffActivity){
        Message<StaffActivity> message = MessageBuilder
                .withPayload(staffActivity)
                .setHeader(KafkaHeaders.TOPIC,STAFF_NEW_ACTIVITY_TOPIC)
                .build();
        kafkaTemplate.send(message);
    }
}
