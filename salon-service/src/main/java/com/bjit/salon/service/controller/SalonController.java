package com.bjit.salon.service.controller;


import com.bjit.salon.service.dto.request.SalonCreateDto;
import com.bjit.salon.service.dto.request.SalonUpdateDto;
import com.bjit.salon.service.dto.response.SalonResponseDto;
import com.bjit.salon.service.service.SalonService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.bjit.salon.service.util.ConstraintsUtil.APPLICATION_BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class SalonController {

    private final SalonService salonService;
    private final Logger log = LoggerFactory.getLogger(SalonController.class);

    @PostMapping("/salons") // admin can create salon
    public ResponseEntity<String> create(@RequestBody SalonCreateDto salonCreateDto){
        salonService.create(salonCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Salon created success");
    }

    @PutMapping("/salons") // admin can update
    public ResponseEntity<String> update(@RequestBody SalonUpdateDto salonUpdateDto){
        salonService.update(salonUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Salon updated success");
    }

    @GetMapping("/salons/{id}") // admin can view
    @CircuitBreaker(name = "salon-service", fallbackMethod = "getSalonFallback")
    public ResponseEntity<SalonResponseDto> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(salonService.getSalon(id));
    }
    public ResponseEntity<SalonResponseDto> getSalonFallback(Exception exception){
        return ResponseEntity.ok(new SalonResponseDto());
    }
    @GetMapping("/salons")
    @CircuitBreaker(name = "salon-service", fallbackMethod = "getAllSalonFallback")
    public ResponseEntity<List<SalonResponseDto>> getAll(){
        log.info("getting salons from salon-service");
        return ResponseEntity.ok(salonService.getAllSalon());
    }
    public ResponseEntity<List<SalonResponseDto>> getAllSalonFallback(Exception exception) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/salons/search")
    @CircuitBreaker(name = "salon-service", fallbackMethod = "searchFallback")
    public ResponseEntity<List<SalonResponseDto>> search(@RequestParam("q") String str){
        return ResponseEntity.ok(salonService.getSalonsByQuery(str));
    }
    public ResponseEntity<List<SalonResponseDto>>searchFallback(Exception exception){
        return ResponseEntity.ok(new ArrayList<>());
    }

}
