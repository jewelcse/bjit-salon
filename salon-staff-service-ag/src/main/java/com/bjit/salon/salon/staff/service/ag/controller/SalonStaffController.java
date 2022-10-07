package com.bjit.salon.salon.staff.service.ag.controller;


import com.bjit.salon.salon.staff.service.ag.dto.response.SalonDetailsDto;
import com.bjit.salon.salon.staff.service.ag.dto.response.SalonResponseDto;
import com.bjit.salon.salon.staff.service.ag.dto.response.StaffResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name="salon-service",fallbackMethod = "getSalonDetailsFallback")
    public ResponseEntity<SalonDetailsDto> getSalonDetails(@PathVariable("id") long id, @RequestParam(required= false, defaultValue = "false") boolean isAvailable){
        SalonResponseDto salon = getSalon(id);
        StaffResponseDto[] staffs = getStaffs(id);
        if (isAvailable){
            StaffResponseDto[] availableStaffs = getAvailableStaffs(id);
            return ResponseEntity.ok(SalonDetailsDto.builder()
                            .salon(salon)
                            .staffs(Arrays.asList(Objects.requireNonNull(availableStaffs)))
                    .build());
        }
        return ResponseEntity.ok(SalonDetailsDto.builder()
                .salon(salon)
                .staffs(Arrays.asList(Objects.requireNonNull(staffs)))
                .build());
    }

    public ResponseEntity<SalonDetailsDto> getSalonDetailsFallback(Exception exception){
        return ResponseEntity.ok(new SalonDetailsDto());
    }

    //@CircuitBreaker(name="salon-service",fallbackMethod = "getSalonFallback")
    private SalonResponseDto getSalon(long id){
        ResponseEntity<SalonResponseDto> salon = restTemplate.getForEntity(SALON_SERVICE_APPLICATION_BASE_URL+"/salons/"+id,SalonResponseDto.class);
        return salon.getBody();
    }

    private SalonResponseDto getSalonFallback(Exception exception){
        return new SalonResponseDto();
    }

    //@CircuitBreaker(name="staff-service",fallbackMethod = "getStaffsFallback")
    private StaffResponseDto[] getStaffs(long id){
        ResponseEntity<StaffResponseDto[]> staffs = restTemplate.getForEntity(STAFF_SERVICE_APPLICATION_BASE_URL+"/salons/"+id+"/staffs", StaffResponseDto[].class);
        return staffs.getBody();
    }

    //@CircuitBreaker(name="staff-service",fallbackMethod = "getStaffsFallback")
    private StaffResponseDto[] getAvailableStaffs(long id){
        ResponseEntity<StaffResponseDto[]> availableStaffs = restTemplate.getForEntity(STAFF_SERVICE_APPLICATION_BASE_URL+"/salons/"+id+"/available/staffs", StaffResponseDto[].class);
        return availableStaffs.getBody();
    }

    private StaffResponseDto[] getStaffsFallback(Exception exception){
        return new StaffResponseDto[]{};
    }

}
