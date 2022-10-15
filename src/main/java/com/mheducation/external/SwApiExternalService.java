package com.mheducation.external;

import com.mheducation.dto.DtoVehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${SW_API_BASE_URL}", name = "swapi-service")
public interface SwApiExternalService {
    @GetMapping(value = "/api/vehicles/{id}")
    DtoVehicle findVehicleById(@PathVariable Long id);
}
