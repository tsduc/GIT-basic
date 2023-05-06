package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long>, RentAreaRepositoryCustom {
	List<RentAreaEntity> findByBuilding_Id(Long buildingId);
}

