package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingSearchReponse;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchReponse convertEntityToBuildingSearchReponse(BuildingEntity entity) {
        BuildingSearchReponse result = modelMapper.map(entity, BuildingSearchReponse.class);
        result.setAddress(entity.getStreet() + " " + entity.getWard() + " " + entity.getName());
        return result;
    }

    public BuildingDTO convertEntityToBuildingDTO(BuildingEntity entity) {
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        return result;
    }

    public BuildingDTO convertToDto (BuildingEntity entity){
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        return result;
    }

    public BuildingEntity convertToEntity (BuildingCreateDTO req){
        BuildingEntity result = modelMapper.map(req, BuildingEntity.class);
        return result;
    }

    public BuildingSearchBuilder convertToBuilder (BuildingSearchRequestDTO req){
        BuildingSearchBuilder result = modelMapper.map(req, BuildingSearchBuilder.class);
        return result;
    }

}
