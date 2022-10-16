package com.mheducation.resource;

import com.mheducation.dto.DtoStarship;
import com.mheducation.service.StarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/starships")
@RequiredArgsConstructor
public class StarshipResource {
    private final StarshipService starshipService;

    @GetMapping("/{id}")
    public DtoStarship findStarshipById(@PathVariable Integer  id) {
        return starshipService.findStarshipById(id);
    }


    @PostMapping("/{id}/inventory/{total}")
    @ResponseStatus(HttpStatus.CREATED)
    public DtoStarship setTotalInventary(@PathVariable Integer id, @PathVariable Integer total) {
        return starshipService.setTotalInventary(id,total);
    }


    @PutMapping("/{id}/inventory-increment/{total}")
    public DtoStarship incrementInventary(@PathVariable Integer id, @PathVariable Integer total) {
        return starshipService.incrementInventary(id,total);
    }

    @PutMapping("/{id}/inventory-decrement/{total}")
    public DtoStarship decrementInventary(@PathVariable Integer id, @PathVariable Integer total) {
        return starshipService.decrementInventary(id,total);
    }

}
