package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;

public interface RentAreaRepositoryCustom extends JdbcRepositoryCustom<RentAreaEntity>{
	List<RentAreaEntity> findByBuildingId(Long buildingId);
}
