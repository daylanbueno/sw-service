package com.mheducation.service;

import com.mheducation.dto.DtoStarship;

public interface StarshipService {
    DtoStarship findStarshipById(Integer id);

    DtoStarship incrementInventary(Integer id, Integer total);

    DtoStarship decrementInventary(Integer id, Integer total);

    DtoStarship setTotalInventary(Integer id, Integer total);
}
