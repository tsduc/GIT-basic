package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingSearchReponse;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;


    @Override
    public List<BuildingDTO> findAll() {
        List<BuildingDTO> results = new ArrayList<>();
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        for (BuildingEntity item: buildingEntities){
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
            results.add(buildingDTO);
        }
        return results;
    }

    @Override
    public Map<String, String> getDistrticts() {
        Map<String, String> districts = new HashMap<>();
        for (DistrictsEnum item: DistrictsEnum.values()){
            districts.put(item.toString(), item.getDistrictValue());
        }
        return districts;
    }

    @Override
    public Map<String, String> getBuildingTypes() {
        Map<String, String> buildingTypes = new HashMap<>();
        for (BuildingTypesEnum item: BuildingTypesEnum.values()){
            buildingTypes.put(item.toString(), item.getBuildingTypeValue());
        }
        return buildingTypes;
    }

    @Override
    @Transactional
    public void save(BuildingCreateDTO req) {
//        BuildingEntity buildingEntity = null;
//        buildingRepository.save(buildingEntity);
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(req);
        buildingRepository.save(buildingEntity);
    }

    @Override
    @Transactional
    public void insert(BuildingCreateDTO req) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(req);
        if(req.getType() != null){
            buildingEntity.setType(String.join(",", req.getType()));
        }
        buildingEntity = buildingRepository.save(buildingEntity);

        String[] rentAreaStrings = req.getRentAreas().split(",");
        for (String rentAreaString : rentAreaStrings) {
            RentAreaEntity rentAreaEntity  = new RentAreaEntity();
            rentAreaEntity.setValue(Integer.parseInt(rentAreaString));
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaRepository.save(rentAreaEntity);
        }
    }

    @Override
    @Transactional
    public void upadte(BuildingCreateDTO req, Long id) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(req);
        if(req.getType() != null){
            buildingEntity.setType(String.join(",", req.getType()));
        }
//        buildingRepository.update(buildingEntity, id);
        buildingEntity.setId(id);
        buildingRepository.save(buildingEntity);

        String[] rentAreaStrings = req.getRentAreas().split(", ");
        int dem = 0;
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuilding_Id(id);
        for (RentAreaEntity rentAreaEntity: rentAreaEntities){
            rentAreaEntity.setValue(Integer.parseInt(rentAreaStrings[dem]));
            rentAreaRepository.save(rentAreaEntity);
            dem++;
        }
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
//        buildingRepository.deleteById(id);
        for (Long id: ids){
            List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuilding_Id(id);
            for (RentAreaEntity rentAreaEntity: rentAreaEntities){
                rentAreaRepository.deleteById(rentAreaEntity.getId());
            }
            List<AssignmentBuildingEntity> assignmentBuildingEntities = assignmentBuildingRepository.findByBuilding_Id(id);
            for (AssignmentBuildingEntity assignmentBuildingEntity: assignmentBuildingEntities){
                assignmentBuildingRepository.deleteById(assignmentBuildingEntity.getId());
            }
            buildingRepository.delete(id);
        }
    }

    @Override
    public List<BuildingDTO> findBuilding(BuildingSearchRequestDTO buildingSearchRequest) {
        List<BuildingDTO> results = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = convertToBuilder(buildingSearchRequest);
		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(buildingSearchBuilder);
        for (BuildingEntity item: buildingEntities) {
            BuildingDTO buildingDTO = buildingConverter.convertEntityToBuildingDTO(item);
            results.add(buildingDTO);
        }
        return results;
    }

    private BuildingSearchBuilder convertToBuilder(BuildingSearchRequestDTO buildingSearchRequest) {
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
                .setName(buildingSearchRequest.getName())
                .setFloorArea(buildingSearchRequest.getFloorArea())
                .setDistrict(buildingSearchRequest.getDistrict())
                .setWard(buildingSearchRequest.getWard())
                .setStreet(buildingSearchRequest.getStreet())
                .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
                .setDirection(buildingSearchRequest.getDirection())
                .setLevel(buildingSearchRequest.getLevel())
                .setRentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                .setRentAreaTo(buildingSearchRequest.getRentAreaTo())
                .setRentPirceFrom(buildingSearchRequest.getRentPirceFrom())
                .setRentPirceTo(buildingSearchRequest.getRentPirceTo())
                .setManagerName(buildingSearchRequest.getManagerName())
                .setManagerPhone(buildingSearchRequest.getManagerPhone())
                .setStaffId(buildingSearchRequest.getStaffId())
                .setBuildingTypes(buildingSearchRequest.getBuildingTypes())
                .build();
        return result;
    }


    @Override
    public List<BuildingSearchReponse> findAll(Map<String, Object> params, List<String> types) {
        return null;
    }

    @Override
    public List<BuildingSearchReponse> findBuilding(Map<String, Object> params, List<String> types) {
        List<BuildingSearchReponse> results = new ArrayList<>();

        List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(params, types);

//		BuildingSearchBuilder buildingSearchBuilder = convertToBuildingSearchBuilder(params, types);
//		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(buildingSearchBuilder);
        for (BuildingEntity item: buildingEntities) {
            BuildingSearchReponse buildingSearchReponse = buildingConverter.convertEntityToBuildingSearchReponse(item);
            results.add(buildingSearchReponse);
        }
        return results;
    }

    private BuildingSearchBuilder convertToBuildingSearchBuilder(Map<String, Object> params, List<String> types) {
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(params, "name", String.class))
                .setFloorArea(MapUtils.getObject(params, "floorArea", Integer.class))
                .setDistrict(MapUtils.getObject(params, "district", String.class))
                .setWard(MapUtils.getObject(params, "ward", String.class))
                .setStreet(MapUtils.getObject(params, "street", String.class))
                .setNumberOfBasement(MapUtils.getObject(params, "numberOfBasement", Integer.class))
                .setDirection(MapUtils.getObject(params, "direction", String.class))
                .setLevel(MapUtils.getObject(params, "level", String.class))
                .setRentAreaFrom(MapUtils.getObject(params, "rentAreaFrom", Integer.class))
                .setRentAreaTo(MapUtils.getObject(params, "rentAreaTo", Integer.class))
                .setRentPirceFrom(MapUtils.getObject(params, "rentPirceFrom", Integer.class))
                .setRentPirceTo(MapUtils.getObject(params, "rentPirceTo", Integer.class))
                .setManagerName(MapUtils.getObject(params, "managerName", String.class))
                .setManagerPhone(MapUtils.getObject(params, "managerPhone", String.class))
                .setStaffId(MapUtils.getObject(params, "staffId", Long.class))
                .setBuildingTypes(types)
                .build();
        return result;
    }


    @Override
    public List<RentAreaDTO> findRentAreaByBuilding(Long buildingId) {
        List<RentAreaDTO> results = new ArrayList<>();
        BuildingEntity buildingEntity = buildingRepository.findId(buildingId);
//		List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuilding_Id(buildingId);
        results = rentAreaEntities.stream().map(item -> rentAreaConverter.convertEntityToDto(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        BuildingDTO results = buildingConverter.convertEntityToBuildingDTO(buildingEntity);
//        results.setRentAreas();
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuilding_Id(id);
        List<String> rentAreas =  new ArrayList<>();
        for (RentAreaEntity item: rentAreaEntities){
            rentAreas.add(item.getValue().toString());
        }
        String rentArea = String.join(",", rentAreas.toString());
        results.setRentAreas(rentArea.substring(1, rentArea.length()-1));
        return results;
    }
}
