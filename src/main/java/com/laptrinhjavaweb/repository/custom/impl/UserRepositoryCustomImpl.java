package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl extends SimpleJdbcRepository<UserEntity> implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager entiryManager;

	@Override
	public List<UserEntity> findByBuildingId(Long buildingId) {
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM user us INNER JOIN assignmentbuilding ass  on us.id = ass.staffid ");
		if (buildingId != null) {
			sql.append(" where ass.buildingid = " + buildingId);
		}
		return findByCondition(sql.toString());
	}

	@Override
	public List<UserEntity> findByRole(String roleCode) {
		// JPQL
		String sql = "FROM UserEntity as u where u.code = '" + roleCode + "'";
		Query query = entiryManager.createQuery(sql, UserEntity.class);
		return query.getResultList();
	}

}
