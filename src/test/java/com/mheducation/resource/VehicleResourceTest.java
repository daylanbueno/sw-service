package com.mheducation.resource;

import com.mheducation.dto.DtoVehicle;
import com.mheducation.service.VehicleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VehicleResource.class)
public class VehicleResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    private static String VEHICLE_API = "/api/vehicles";

    @Test
    @DisplayName("should return a vehicle by id")
    public void shouldReturnVehicleById() throws Exception {
        DtoVehicle vehicle = DtoVehicle.builder().url("https://swapi.dev/api/vehicles/3").name("Sand Crawler").build();

        Mockito.when(vehicleService.findVehicleById(Mockito.anyInt())).thenReturn(vehicle);

        RequestBuilder request = MockMvcRequestBuilders.get(VEHICLE_API.concat("/3"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(vehicle.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(vehicle.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("count").value(vehicle.getCount()))
                .andReturn();
    }

    @Test
    @DisplayName("should return a vehicle by id when does not  exist")
    public void shouldReturnVehicleByIdWhenDoesNotExist() throws Exception {
       Mockito.when(vehicleService.findVehicleById(Mockito.anyInt())).thenThrow(new IllegalArgumentException("Does not exist"));

        RequestBuilder request = MockMvcRequestBuilders.get(VEHICLE_API.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DisplayName("should set total inventary to 50")
    public void shouldSetTotalInventary() throws Exception {
        DtoVehicle vehicle = DtoVehicle.builder()
                .url("https://swapi.dev/api/vehicles/3").name("Sand Crawler")
                .count(50)
                .build();

        Mockito.when(vehicleService.setTotalInventary(Mockito.anyInt(), Mockito.anyInt())).thenReturn(vehicle);

        RequestBuilder request = MockMvcRequestBuilders.post(VEHICLE_API.concat("/3/inventory/50"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(vehicle.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("count").value(vehicle.getCount()))
                .andReturn();
    }


    @Test
    @DisplayName("should increment the inventary by 10")
    public void shouldIncrementInventary() throws Exception {
        DtoVehicle vehicle = DtoVehicle.builder()
                .url("https://swapi.dev/api/vehicles/3").name("Sand Crawler")
                .count(10)
                .build();

        Mockito.when(vehicleService.incrementInventary(Mockito.anyInt(), Mockito.anyInt())).thenReturn(vehicle);

        RequestBuilder request = MockMvcRequestBuilders.put(VEHICLE_API.concat("/3/inventory-increment/10"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(vehicle.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(vehicle.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("count").value(vehicle.getCount()))
                .andReturn();
    }

    @Test
    @DisplayName("should decrement the inventary by 10")
    public void shouldDecrementInventary() throws Exception {
        DtoVehicle vehicle = DtoVehicle.builder()
                .url("https://swapi.dev/api/vehicles/3").name("Sand Crawler")
                .count(10)
                .build();

        Mockito.when(vehicleService.decrementInventary(Mockito.anyInt(), Mockito.anyInt())).thenReturn(vehicle);

        RequestBuilder request = MockMvcRequestBuilders.put(VEHICLE_API.concat("/3/inventory-decrement/10"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(vehicle.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(vehicle.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("count").value(vehicle.getCount()))
                .andReturn();
    }

}
