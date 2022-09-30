package com.bjit.salon.service.controller;


import com.bjit.salon.service.dto.request.SalonCreateDto;
import com.bjit.salon.service.dto.request.SalonUpdateDto;
import com.bjit.salon.service.dto.response.SalonResponseDto;
import com.bjit.salon.service.service.SalonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bjit.salon.service.util.ConstraintsUtil.APPLICATION_BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class SalonController {

    private final SalonService salonService;

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
    public ResponseEntity<SalonResponseDto> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(salonService.getSalon(id));
    }

    @GetMapping("/salons") // super admin can view
    public ResponseEntity<List<SalonResponseDto>> getAll(){
        return ResponseEntity.ok(salonService.getAllSalon());
    }

    @GetMapping("/salons/search")
    public ResponseEntity<List<SalonResponseDto>> search(@RequestParam("q") String str){
        return ResponseEntity.ok(salonService.getSalonsByQuery(str));
    }

}
