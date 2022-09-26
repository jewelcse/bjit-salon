package com.bjit.salon.auth.service.serviceImpl;


import com.bjit.salon.auth.service.repository.UserRepository;
import com.bjit.salon.auth.service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


}
