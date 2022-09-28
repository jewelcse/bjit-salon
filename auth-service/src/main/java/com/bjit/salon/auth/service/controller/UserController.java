package com.bjit.salon.auth.service.controller;

import com.bjit.salon.auth.service.dto.request.UserIdDto;
import com.bjit.salon.auth.service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.bjit.salon.auth.service.util.ConstraintsUtil.APPLICATION_BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class UserController {
    private final UserService userService;

    @PostMapping("/activateDeactivate")
    public ResponseEntity<String> activateDeactivateUser(@Valid @RequestBody UserIdDto userIdDto) {
        boolean isUserAccountActive=userService.activateDeactivateUserAccount(userIdDto.getId());
        if (isUserAccountActive){
            return ResponseEntity.status(HttpStatus.OK).body("User account activated");
        }
        return ResponseEntity.status(HttpStatus.OK).body("User Account deactivated");
    }


}
