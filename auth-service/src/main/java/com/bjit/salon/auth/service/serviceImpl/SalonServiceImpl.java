package com.bjit.salon.auth.service.serviceImpl;

import com.bjit.salon.auth.service.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import static com.bjit.salon.auth.service.util.ConstraintsUtil.SALON_STAFF_AG_SERVICE_APPLICATION_BASE_URL;

@RequiredArgsConstructor
@Service
public class SalonServiceImpl implements SalonService {

    private final RestTemplate restTemplate;


    @Override
    public Object getSalon(long id) {
        ResponseEntity<Object> response = restTemplate.getForEntity(SALON_STAFF_AG_SERVICE_APPLICATION_BASE_URL+"/salons/"+id,Object.class);
        return response.getBody();
    }

    @Override
    public Object getSalonWithAvailableStaff(long id, boolean isAvailable) {
        ResponseEntity<Object> response = restTemplate.getForEntity(SALON_STAFF_AG_SERVICE_APPLICATION_BASE_URL+"/salons/"+id+"?isAvailable=true",Object.class);
        return response.getBody();
    }
}
