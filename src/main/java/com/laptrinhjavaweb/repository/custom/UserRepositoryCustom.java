package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {
	List<UserEntity> findByBuildingId(Long buildingId);
	
	List<UserEntity> findByRole(String roleCode);
}
