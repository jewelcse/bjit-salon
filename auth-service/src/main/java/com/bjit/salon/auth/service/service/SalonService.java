package com.bjit.salon.auth.service.service;

public interface SalonService {

    Object getSalon(long id);

    Object getSalonWithAvailableStaff(long id, boolean isAvailable);
}
