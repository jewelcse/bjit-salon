package com.bjit.salon.service.service;

import com.bjit.salon.service.dto.request.SalonStaffDto;
import com.bjit.salon.service.dto.request.SalonCreateDto;

public interface SalonService {
    void create(SalonCreateDto salonCreateDto);

    void addStaffToSalon(SalonStaffDto salonStaffDto);

    void removeStaffFromSalon(SalonStaffDto salonStaffDto);
}
