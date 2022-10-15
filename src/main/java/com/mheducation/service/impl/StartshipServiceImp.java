package com.mheducation.service.impl;

import com.mheducation.dto.DtoStarship;
import com.mheducation.entity.Inventory;
import com.mheducation.external.SwApiExternalService;
import com.mheducation.service.InventoryService;
import com.mheducation.service.StarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartshipServiceImp implements StarshipService {

    private final SwApiExternalService swApiExternalService;

    private final InventoryService inventoryService;

    @Override
    public DtoStarship findStarshipById(Integer id) {

        DtoStarship dtoStarship = swApiExternalService.findStarshiById(id);

        Inventory inventory = inventoryService.findInventoryByUrl(dtoStarship.getUrl());

        if (inventory != null) { dtoStarship.setCount(inventory.getCount()); }

        return dtoStarship;
    }

    @Override
    public DtoStarship setTotalInventary(Integer id, Integer total) {
        DtoStarship dtoStarship =  swApiExternalService.findStarshiById(id);

        if (dtoStarship == null) {
            throw new IllegalArgumentException("The vehicle does not exist.");
        }

        Inventory inventory = inventoryService.setTotalInventary(dtoStarship.getUrl(), total);

        dtoStarship.setCount(inventory.getCount());

        return dtoStarship;
    }

    @Override
    public DtoStarship incrementInventary(Integer id, Integer total) {
        DtoStarship dtoStarship =  swApiExternalService.findStarshiById(id);

        if (dtoStarship == null) {
            throw new IllegalArgumentException("The starship does not exist.");
        }

        Inventory inventory = inventoryService.incrementInventory(dtoStarship.getUrl(), total);

        dtoStarship.setCount(inventory.getCount());

        return dtoStarship;
    }

    @Override
    public DtoStarship decrementInventary(Integer id, Integer total) {
        DtoStarship dtoStarship =  swApiExternalService.findStarshiById(id);

        if (dtoStarship == null) {
            throw new IllegalArgumentException("The starship does not exist.");
        }

        Inventory inventory = inventoryService.decrementInventory(dtoStarship.getUrl(), total);

        dtoStarship.setCount(inventory.getCount());

        return dtoStarship;
    }

}
