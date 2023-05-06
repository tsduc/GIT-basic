package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.repository.custom.RoleRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RoleRepositoryCustomImpl implements RoleRepositoryCustom{
	@PersistenceContext
	private EntityManager entiryManager;
	
	@Override
	public RoleEntity findByCode(String code) {
		// SQL native
		String sql = "select * FROM role as r where r.code = '" +code+ "";
		Query query = entiryManager.createNativeQuery(sql, RoleEntity.class);

		return (RoleEntity) query.getSingleResult();
	}

}
