package com.mheducation.service.impl;

import com.mheducation.dto.DtoVehicle;
import com.mheducation.external.SwApiExternalService;
import com.mheducation.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImp implements VehicleService {

    private final SwApiExternalService swApiExternalService;
    @Override
    public DtoVehicle findVehicleById(Long id) {
        return swApiExternalService.findVehicleById(id);
    }
}
