package com.mheducation.service.impl;

import com.mheducation.dto.DtoVehicle;
import com.mheducation.entity.Inventory;
import com.mheducation.external.SwApiExternalService;
import com.mheducation.service.InventoryService;
import com.mheducation.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImp implements VehicleService {

    private final SwApiExternalService swApiExternalService;
    private final InventoryService inventoryService;
    @Override
    public DtoVehicle findVehicleById(Integer id) {

        DtoVehicle dtoVehicle = swApiExternalService.findVehicleById(id);

        Inventory inventory = inventoryService.findInventoryByUrl(dtoVehicle.getUrl());

        if (inventory != null) { dtoVehicle.setCount(inventory.getCount()); }

        return dtoVehicle;
    }

    @Override
    public DtoVehicle setTotalInventary(Integer id, Integer total) {

        DtoVehicle dtoVehicle =  swApiExternalService.findVehicleById(id);

        Inventory inventory = inventoryService.setTotalInventary(dtoVehicle.getUrl(), total);

        dtoVehicle.setCount(inventory.getCount());

        return dtoVehicle;
    }


    @Override
    public DtoVehicle incrementInventary(Integer id, Integer total) {

       DtoVehicle dtoVehicle =  swApiExternalService.findVehicleById(id);

       Inventory inventory = inventoryService.incrementInventory(dtoVehicle.getUrl(), total);

       dtoVehicle.setCount(inventory.getCount());

       return dtoVehicle;
    }

    @Override
    public DtoVehicle decrementInventary(Integer id, Integer total) {

        DtoVehicle dtoVehicle =  swApiExternalService.findVehicleById(id);

        Inventory inventory = inventoryService.decrementInventory(dtoVehicle.getUrl(), total);

        dtoVehicle.setCount(inventory.getCount());

        return dtoVehicle;
    }

}
