package com.mheducation.service;

import com.mheducation.entity.Inventory;

public interface InventoryService {
    Inventory setTotalInventary (String url, Integer total);
    Inventory incrementInventory (String url, Integer total);
    Inventory decrementInventory (String url, Integer total);
    Inventory findInventoryByUrl(String url);
}
