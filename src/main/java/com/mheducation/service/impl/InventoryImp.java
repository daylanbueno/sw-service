package com.mheducation.service.impl;

import com.mheducation.entity.Inventory;
import com.mheducation.enums.InventoryType;
import com.mheducation.exceptions.BusinessException;
import com.mheducation.repository.InventoryRepository;
import com.mheducation.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryImp implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory setTotalInventary(String  url, Integer total) {
        Inventory inventory = inventoryRepository.findByUrl(url);

        if (inventory == null) { return  saveNewInventary(url,total);}

        inventory.setCount(total);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory incrementInventory(String  url, Integer total) {
        Inventory inventory = inventoryRepository.findByUrl(url);

        if (inventory == null) {
            throw new BusinessException("There is no inventary to increment!");
        }

        inventory.setCount(inventory.getCount() + total);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory decrementInventory(String  url, Integer total) {
        Inventory inventory = inventoryRepository.findByUrl(url);

        if (inventory == null) {
            throw new BusinessException("There is no inventary to decrement!");
        }

        if ((inventory.getCount() - total) < 0) {
            throw new BusinessException("Cannot decrease because the value passed is greater than total inventory!");
        }

        inventory.setCount(inventory.getCount() - total);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory findInventoryByUrl(String url) {
        return inventoryRepository.findByUrl(url);
    }

    private Inventory saveNewInventary(String url, Integer total) {

        Inventory entity = Inventory.builder().count(total).url(url).tipo(InventoryType.VEHICLE).build();

        return inventoryRepository.save(entity);
    }
}
