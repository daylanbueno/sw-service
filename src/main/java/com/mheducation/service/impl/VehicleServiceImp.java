package com.mheducation.service.impl;

import com.mheducation.dto.DtoVehicle;
import com.mheducation.entity.Inventory;
import com.mheducation.external.SwApiExternalService;
import com.mheducation.repository.InventoryRepository;
import com.mheducation.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImp implements VehicleService {

    private final SwApiExternalService swApiExternalService;
    private final InventoryRepository inventoryRepository;
    @Override
    public DtoVehicle findVehicleById(Long id) {

        DtoVehicle dtoVehicle = swApiExternalService.findVehicleById(id);

        Inventory inventory = inventoryRepository.findByUrl(dtoVehicle.getUrl());

        if (inventory != null) { dtoVehicle.setCount(inventory.getCount()); }

        return dtoVehicle;
    }
}
