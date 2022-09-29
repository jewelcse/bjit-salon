package com.bjit.salon.service.controller;


import com.bjit.salon.service.dto.request.SalonStaffDto;
import com.bjit.salon.service.dto.request.SalonCreateDto;
import com.bjit.salon.service.service.SalonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bjit.salon.service.util.ConstraintsUtil.APPLICATION_BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class SalonController {

    private final SalonService salonService;

    @PostMapping("/salons")
    public ResponseEntity<String> createNewSalon(@RequestBody SalonCreateDto salonCreateDto){
        salonService.create(salonCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Salon created success");
    }

    @PostMapping("/salons/staff")
    public ResponseEntity<String> addStaff(@RequestBody SalonStaffDto salonStaffDto){
        salonService.addStaffToSalon(salonStaffDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Add staff success");
    }

    @DeleteMapping("/salons/staff")
    public ResponseEntity<String> removeStaff(@RequestBody SalonStaffDto salonStaffDto){
        salonService.removeStaffFromSalon(salonStaffDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Remove staff success");
    }
}
