package com.bjit.salon.staff.service.controller;

import com.bjit.salon.staff.service.dto.request.StaffCreateDto;
import com.bjit.salon.staff.service.dto.request.StaffUpdateDto;
import com.bjit.salon.staff.service.dto.response.StaffResponseDto;
import com.bjit.salon.staff.service.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bjit.salon.staff.service.util.ConstraintsUtil.APPLICATION_BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class StaffController {

    private final StaffService staffService;


    @PostMapping("/staffs") // add staff
    public ResponseEntity<String> create(@RequestBody StaffCreateDto staffCreateDto){
        staffService.createNewStaff(staffCreateDto);
        return ResponseEntity.ok("Staff created success");
    }

    @PutMapping("/staffs") // update staff
    public ResponseEntity<String> update(@RequestBody StaffUpdateDto staffUpdateDto){
        staffService.updateStaff(staffUpdateDto);
        return ResponseEntity.ok("Staff updated success");
    }

    @GetMapping("/staffs/{id}")
    public ResponseEntity<StaffResponseDto> get(@PathVariable long id){
        return ResponseEntity.ok(staffService.getStaff(id));
    }

    @GetMapping("/staffs")
    public ResponseEntity<List<StaffResponseDto>> getAll(){
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/staffs/salons/{id}")
    public ResponseEntity<List<StaffResponseDto>> getSalonStaffs(@PathVariable long id){
        return ResponseEntity.ok(staffService.getListOfStaffBySalon(id));
    }


}
