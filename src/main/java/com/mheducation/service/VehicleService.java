package com.mheducation.service;

import com.mheducation.dto.DtoVehicle;

public interface VehicleService {
    DtoVehicle findVehicleById(Integer id);

    DtoVehicle incrementInventary(Integer id, Integer total);

    DtoVehicle decrementInventary(Integer id, Integer total);

    DtoVehicle setTotalInventary(Integer id, Integer total);
}
