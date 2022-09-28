package com.bjit.salon.service.serviceImpl;

import com.bjit.salon.service.dto.request.SalonStaffDto;
import com.bjit.salon.service.dto.request.SalonCreateDto;
import com.bjit.salon.service.entiry.Salon;
import com.bjit.salon.service.entiry.User;
import com.bjit.salon.service.exception.SalonNotFoundException;
import com.bjit.salon.service.repository.SalonRepository;
import com.bjit.salon.service.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public void create(SalonCreateDto salonCreateDto) {
        Salon newSalon = new Salon();

        // todo: find the user from user service by user id then add to the staff array list
        User salonUser = new User();
        salonUser.setId(1L);

        newSalon.setUser(salonUser);
        newSalon.setName(salonCreateDto.getName());
        newSalon.setReviews(0.0);
        salonRepository.save(newSalon);
    }

    @Override
    public void addStaffToSalon(SalonStaffDto salonStaffDto) {
        Optional<Salon> salon = salonRepository.findById(salonStaffDto.getSalonId());
        if (salon.isEmpty()){
            throw new SalonNotFoundException("Salon not found for id: " + salonStaffDto.getSalonId());
        }

        List<User> staffs = salon.get().getStaffs();
        // todo: perform rest call to get the (staff) user object
        User salonUser = new User();
        salonUser.setId(salonUser.getId());

        staffs.add(salonUser);
        salon.get().setStaffs(staffs);

        salonRepository.save(salon.get());
    }

    @Override
    public void removeStaffFromSalon(SalonStaffDto salonStaffDto) {
        Optional<Salon> salon = salonRepository.findById(salonStaffDto.getSalonId());
        if (salon.isEmpty()){
            throw new SalonNotFoundException("Salon not found for id: " + salonStaffDto.getSalonId());
        }

        List<User> staffs = salon.get().getStaffs();
        // todo: perform rest call to get the user object
        User salonUser = new User();
        salonUser.setId(salonUser.getId());

        // todo: found some issues while trying to remove the staff(user)
        staffs.remove(salonUser);
        salon.get().setStaffs(staffs);
        salonRepository.save(salon.get());
    }
}
