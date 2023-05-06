package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingSearchReponse;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingDTO> findAll();

    Map<String, String> getDistrticts();

    Map<String, String> getBuildingTypes();

    void  save(BuildingCreateDTO req);

    void  insert(BuildingCreateDTO req);

    void  upadte(BuildingCreateDTO req, Long id);

//    void delete(Long id);

    void delete(Long[] ids);

    List<BuildingDTO> findBuilding(BuildingSearchRequestDTO buildingSearchRequest);

    List<BuildingSearchReponse> findAll(Map<String, Object> params, List<String> types);

    List<BuildingSearchReponse> findBuilding(Map<String, Object> params, List<String> types);

    List<RentAreaDTO> findRentAreaByBuilding(Long buildingId);

    BuildingDTO findById(Long id);
}
