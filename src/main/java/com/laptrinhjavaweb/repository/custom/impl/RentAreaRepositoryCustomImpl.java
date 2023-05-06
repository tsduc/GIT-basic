package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RentAreaRepositoryCustomImpl extends SimpleJdbcRepository<RentAreaEntity> implements RentAreaRepositoryCustom  {

	@PersistenceContext
	private EntityManager entiryManager;

	@Override
	public List<RentAreaEntity> findByBuildingId(Long buildingId) {
		//JPQL
		String sql = "FROM RentAreaEntityR WHERE building.id = " + buildingId + "";
		Query query = entiryManager.createQuery(sql, RentAreaEntity.class);
		return query.getResultList();
	}
	
	
//	@Override
//	public List<RentAreaEntity> findByBuildingId(Long buildingId) {
//		StringBuilder sql = new StringBuilder("SELECT ra.* FROM rentarea ra ");
//		if (buildingId != null) {
//			sql.append(" where ra.buildingid = " + buildingId);
//		}
//		return findByCondition(sql.toString());
//	}
	
	

}
