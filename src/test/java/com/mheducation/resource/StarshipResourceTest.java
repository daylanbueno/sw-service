package com.mheducation.resource;

import com.mheducation.dto.DtoStarship;
import com.mheducation.service.StarshipService;
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
@WebMvcTest(StarshipResource.class)
public class StarshipResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StarshipService starshipService;

    private static String STARSHIP_API = "/api/starships";

    @Test
    @DisplayName("should return a starship by id")
    public void shouldReturnStarshipById() throws Exception {
        DtoStarship starship = DtoStarship.builder().url("https://swapi.dev/api/starships/9").name("Death Star").build();

        Mockito.when(starshipService.findStarshipById(Mockito.anyInt())).thenReturn(starship);

        RequestBuilder request = MockMvcRequestBuilders.get(STARSHIP_API.concat("/9"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(starship.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(starship.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("count").value(starship.getCount()))
                .andReturn();
    }

    @Test
    @DisplayName("should return a starship by id when does not  exist")
    public void shouldReturnStarshipByIdWhenDoesNotExist() throws Exception {
       Mockito.when(starshipService.findStarshipById(Mockito.anyInt())).thenThrow(new IllegalArgumentException("Does not exist"));

        RequestBuilder request = MockMvcRequestBuilders.get(STARSHIP_API.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DisplayName("should set total inventary to 50")
    public void shouldSetTotalInventary() throws Exception {

        DtoStarship starship = DtoStarship.builder()
                .url("https://swapi.dev/api/starships/9")
                .name("Death Star")
                .count(50).build();

        Mockito.when(starshipService.setTotalInventary(Mockito.anyInt(), Mockito.anyInt())).thenReturn(starship);

        RequestBuilder request = MockMvcRequestBuilders.post(STARSHIP_API.concat("/10/inventory/50"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(starship.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(starship.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("count").value(starship.getCount()))
                .andReturn();
    }


    @Test
    @DisplayName("should increment the inventary by 10")
    public void shouldIncrementInventary() throws Exception {

        DtoStarship starship = DtoStarship.builder()
                .url("https://swapi.dev/api/starships/9")
                .name("Death Star")
                .count(10).build();

        Mockito.when(starshipService.incrementInventary(Mockito.anyInt(), Mockito.anyInt())).thenReturn(starship);

        RequestBuilder request = MockMvcRequestBuilders.put(STARSHIP_API.concat("/9/inventory-increment/10"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(starship.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(starship.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("count").value(starship.getCount()))
                .andReturn();
    }

    @Test
    @DisplayName("should decrement the inventary by 10")
    public void shouldDecrementInventary() throws Exception {
        DtoStarship starship = DtoStarship.builder()
                .url("https://swapi.dev/api/starships/9")
                .name("Death Star")
                .count(10).build();


        Mockito.when(starshipService.decrementInventary(Mockito.anyInt(), Mockito.anyInt())).thenReturn(starship);

        RequestBuilder request = MockMvcRequestBuilders.put(STARSHIP_API.concat("/9/inventory-decrement/10"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(starship.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(starship.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("count").value(starship.getCount()))
                .andReturn();
    }

}
