package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom extends JdbcRepositoryCustom<BuildingEntity> {
//	List<BuildingEntity> findAll();
//	
	List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> types);
	
	List<BuildingEntity> findBuilding(BuildingSearchBuilder builder);
	
//	BuildingEntity findById(Long id);

//	void insert(BuildingEntity newBuilding);
//
//	void update(BuildingEntity building, Long id);
//
//	void delete(Long id);
	
//	BuildingEntity findById(Long id);
	
	List<BuildingEntity> findAll(Map<String, Object> params, List<String> types);
}
