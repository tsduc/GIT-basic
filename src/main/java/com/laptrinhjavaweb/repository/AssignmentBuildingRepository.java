package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    AssignmentBuildingEntity findByBuilding_IdAndUser_Id(Long id, Long staffId);
    List<AssignmentBuildingEntity> findByBuilding_Id(Long buildingId);
}
