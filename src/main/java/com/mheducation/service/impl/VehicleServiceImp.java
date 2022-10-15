package com.mheducation.service.impl;

import com.mheducation.dto.DtoVehicle;
import com.mheducation.entity.Inventory;
import com.mheducation.enums.InventoryType;
import com.mheducation.exceptions.BusinessException;
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
    public DtoVehicle findVehicleById(Integer id) {

        DtoVehicle dtoVehicle = swApiExternalService.findVehicleById(id);

        Inventory inventory = inventoryRepository.findByUrl(dtoVehicle.getUrl());

        if (inventory != null) { dtoVehicle.setCount(inventory.getCount()); }

        return dtoVehicle;
    }

    @Override
    public DtoVehicle setTotalInventary(Integer id, Integer total) {

        DtoVehicle dtoVehicle =  swApiExternalService.findVehicleById(id);

        if (dtoVehicle == null) {
            throw new IllegalArgumentException("The vehicle does not exist.");
        }

        Inventory inventory = inventoryRepository.findByUrl(dtoVehicle.getUrl());


        if (inventory == null) {
            inventory =  saveNewInventary(dtoVehicle.getUrl(), total);
            dtoVehicle.setCount(inventory.getCount());
            return dtoVehicle;
        }

        inventory.setCount(total);

        inventoryRepository.save(inventory);

        dtoVehicle.setCount(inventory.getCount());

        return dtoVehicle;
    }


    @Override
    public DtoVehicle incrementInventary(Integer id, Integer total) {

       DtoVehicle dtoVehicle =  swApiExternalService.findVehicleById(id);

       if (dtoVehicle == null) {
           throw new IllegalArgumentException("The vehicle does not exist.");
       }

       Inventory inventory = inventoryRepository.findByUrl(dtoVehicle.getUrl());

       if (inventory == null) {
           throw new BusinessException("There is no inventary to increment!");
       }

       inventory.setCount(inventory.getCount() + total);

       inventoryRepository.save(inventory);

       dtoVehicle.setCount(inventory.getCount());

       return dtoVehicle;
    }

    @Override
    public DtoVehicle decrementInventary(Integer id, Integer total) {

        DtoVehicle dtoVehicle =  swApiExternalService.findVehicleById(id);

        if (dtoVehicle == null) {
            throw new IllegalArgumentException("The vehicle does not exist.");
        }

        Inventory inventory = inventoryRepository.findByUrl(dtoVehicle.getUrl());

        if (inventory == null) {
            throw new BusinessException("There is no inventary to decrement!");
        }

        if ((inventory.getCount() - total) < 0) {
            throw new BusinessException("Cannot decrease because the value passed is greater than total inventory!");
        }

        inventory.setCount(inventory.getCount() - total);

        inventoryRepository.save(inventory);

        dtoVehicle.setCount(inventory.getCount());

        return dtoVehicle;
    }


    private Inventory saveNewInventary(String url, Integer total) {

        Inventory entity = Inventory.builder().count(total).url(url).tipo(InventoryType.VEHICLE).build();

        return inventoryRepository.save(entity);
    }
}
