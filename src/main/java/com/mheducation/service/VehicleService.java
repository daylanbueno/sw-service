package com.mheducation.service;

import com.mheducation.dto.DtoVehicle;

public interface VehicleService {
    DtoVehicle findVehicleById(Long id);
}
