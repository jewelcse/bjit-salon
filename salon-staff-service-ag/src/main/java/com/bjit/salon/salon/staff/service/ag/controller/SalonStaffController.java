package com.bjit.salon.salon.staff.service.ag.controller;


import com.bjit.salon.salon.staff.service.ag.dto.response.SalonDetailsDto;
import com.bjit.salon.salon.staff.service.ag.dto.response.SalonResponseDto;
import com.bjit.salon.salon.staff.service.ag.dto.response.StaffResponseDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.bjit.salon.salon.staff.service.ag.util.ConstraintsUtil.*;

@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class SalonStaffController {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SalonStaffController.class);


    @GetMapping("/salons/{id}")
    public ResponseEntity<SalonDetailsDto> getSalons(@PathVariable("id") long id, @RequestParam(required= false, defaultValue = "false") boolean isAvailable){
        ResponseEntity<SalonResponseDto> salon = restTemplate.getForEntity(SALON_SERVICE_APPLICATION_BASE_URL+"/salons/"+id,SalonResponseDto.class);
        ResponseEntity<StaffResponseDto[]> staffs = restTemplate.getForEntity(STAFF_SERVICE_APPLICATION_BASE_URL+"/salons/"+id+"/staffs", StaffResponseDto[].class);
        if (isAvailable){
            ResponseEntity<StaffResponseDto[]> availableStaffs = restTemplate.getForEntity(STAFF_SERVICE_APPLICATION_BASE_URL+"/salons/"+id+"/available/staffs", StaffResponseDto[].class);
            logger.info("In filter "+ Objects.requireNonNull(availableStaffs.getBody()).length + " staffs found");
            return ResponseEntity.ok(SalonDetailsDto.builder()
                            .salon(salon.getBody())
                            .staffs(Arrays.asList(Objects.requireNonNull(availableStaffs.getBody())))
                    .build());
        }
        logger.info("Out filter "+ Objects.requireNonNull(staffs.getBody()).length + " staffs found");
        return ResponseEntity.ok(SalonDetailsDto.builder()
                .salon(salon.getBody())
                .staffs(Arrays.asList(Objects.requireNonNull(staffs.getBody())))
                .build());
    }
}
