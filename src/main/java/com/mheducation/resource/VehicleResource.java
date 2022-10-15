package com.mheducation.resource;

import com.mheducation.dto.DtoVehicle;
import com.mheducation.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleResource {
    private final VehicleService vehicleService;

    @GetMapping("/{id}")
    private DtoVehicle findVehicleById(@PathVariable Long  id) {
        return vehicleService.findVehicleById(id);
    }


}
