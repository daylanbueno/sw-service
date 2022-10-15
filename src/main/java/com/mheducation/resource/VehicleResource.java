package com.mheducation.resource;

import com.mheducation.dto.DtoVehicle;
import com.mheducation.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleResource {
    private final VehicleService vehicleService;

    @GetMapping("/{id}")
    public DtoVehicle findVehicleById(@PathVariable Integer  id) {
        return vehicleService.findVehicleById(id);
    }

    @PostMapping("/{id}/inventory/{total}")
    public DtoVehicle incrementInventary(@PathVariable Integer id, @PathVariable Integer total) {
        return vehicleService.incrementInventary(id,total);
    }


}
