package com.bjit.salon.staff.service.listener;


import com.bjit.salon.staff.service.dto.listener.NewActivityListenerDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.bjit.salon.staff.service.util.ConstraintsUtil.STAFF_NEW_ACTIVITY_GROUP;
import static com.bjit.salon.staff.service.util.ConstraintsUtil.STAFF_NEW_ACTIVITY_TOPIC;

@Service
public class NewActivityCreateListener {


    @KafkaListener(
            topics = STAFF_NEW_ACTIVITY_TOPIC,
            groupId = STAFF_NEW_ACTIVITY_GROUP
    )
    public void newActivityCreateListener(NewActivityListenerDto activityListenerDto) {

        System.out.println(activityListenerDto.toString());

    }
}
