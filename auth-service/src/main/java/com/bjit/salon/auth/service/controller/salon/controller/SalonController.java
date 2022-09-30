package com.bjit.salon.auth.service.controller.salon.controller;


import com.bjit.salon.auth.service.service.SalonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;

import static com.bjit.salon.auth.service.util.ConstraintsUtil.APPLICATION_BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class SalonController {

    private final SalonService salonService;

    @GetMapping("/salons/{id}")
    public ResponseEntity<Object> getSalon(@PathVariable("id") long id, @RequestParam(required= false, defaultValue = "false") boolean isAvailable){
        if (isAvailable){
            return ResponseEntity.ok(salonService.getSalonWithAvailableStaff(id,true));
        }
        return ResponseEntity.ok(salonService.getSalon(id));
    }

}
