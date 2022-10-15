package com.mheducation.service;

import com.mheducation.dto.DtoVehicle;

public interface VehicleService {
    DtoVehicle findVehicleById(Integer id);

    DtoVehicle incrementInventary(Integer id, Integer total);

}
