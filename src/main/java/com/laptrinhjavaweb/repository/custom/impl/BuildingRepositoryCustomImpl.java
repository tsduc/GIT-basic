package com.laptrinhjavaweb.repository.custom.impl;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.MapUtils;
import com.laptrinhjavaweb.utils.NumberUtils;
import com.laptrinhjavaweb.utils.StringUtils;

@Repository
//@Primary
public class BuildingRepositoryCustomImpl extends SimpleJdbcRepository<BuildingEntity> implements BuildingRepositoryCustom {

	@PersistenceContext
	private EntityManager entiryManager;

	@Override
	public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> types) {
		List<BuildingEntity> results = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building as b ");
		sql = buildSqlJoining(params, types, sql);
		sql.append(SystemConstant.ONE_EQUAL_ONE);
		sql = buildSqlCommon(params, sql);
		sql = buildSqlSpecial(params, types, sql);
		sql.append(" group by b.id");

//		return findByCondition(sql.toString());

		Query query = entiryManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}

	private StringBuilder buildSqlJoining(Map<String, Object> params, List<String> types, StringBuilder sql) {
		Long staffId = MapUtils.getObject(params, "staffId", Long.class);
		if (staffId != null) {
			sql.append(" inner join assignmentbuilding as ab on ab.buildingid = b.id ");
		}
		return sql;
	}

	private StringBuilder buildSqlCommon(Map<String, Object> params, StringBuilder sql) {
		for (Entry<String, Object> item : params.entrySet()) {
			if (!item.getKey().equals("types") && !item.getKey().startsWith("rentArea")
					&& !item.getKey().equals("staffid") && !item.getKey().startsWith("rentPirce")) {
				String value = item.getValue().toString();
				if (NumberUtils.isInteger(value)) {
					sql.append(" and b." + item.getKey().toLowerCase() + " = " + Integer.parseInt(value) + "");
				} else {
					if (!StringUtils.isNullOrEmpty(value)) {
						sql.append(" and b." + item.getKey().toLowerCase() + " like '%" + value + "%'");

					}
				}
			}
		}
		return sql;
	}

	private StringBuilder buildSqlSpecial(Map<String, Object> params, List<String> types, StringBuilder sql) {
		// java <8
		Integer rentPirceFrom = MapUtils.getObject(params, "rentPirceFrom", Integer.class);
		Integer rentPirceTo = MapUtils.getObject(params, "rentPirceTo", Integer.class);
		Integer rentAreaFrom = MapUtils.getObject(params, "rentAreaFrom", Integer.class);
		Integer rentAreaTo = MapUtils.getObject(params, "rentAreaTo", Integer.class);
		Long staffId = MapUtils.getObject(params, "staffId", Long.class);

		if (rentPirceFrom != null) {
			sql.append(" and b.rentpirce >= " + rentPirceFrom + "");
		}
		if (rentPirceTo != null) {
			sql.append(" and b.rentpirce <= " + rentPirceTo + "");
		}
		if (rentAreaFrom != null || rentAreaTo != null) {
			sql.append(" and EXISTS (select value from rentarea r where r.buildingid = b.id");
			if (rentAreaFrom != null) {
				sql.append(" and r.value >= ").append(rentAreaFrom);
			}
			if (rentAreaTo != null) {
				sql.append(" and r.value <= ").append(rentAreaTo);
			}
			sql.append(")");
		}
		if (staffId != null) {
			sql.append(" and ab.staffid = " + staffId + "");
		}
//		if(types != null && types.size() > 0) {
//			sql.append(" and (");
//			for(String type: types) {
//				types.add("rt.code = '"+type+"'");
//			}
//			String sqlJoin = String.join(" or ", types);
//			sql.append(sqlJoin);
//			sql.append(")");
//		}

		// java 8
		if (types != null && types.size() > 0) {
			sql.append(" and (");
			String sqlJoin = types.stream().map(item -> "type like '%" + item + "%'").collect(Collectors.joining(" or "));
			sql.append(sqlJoin);
			sql.append(")");
		}
		return sql;
	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> types) {
		// TODO Auto-generated method stub
		return null;
	}

	// Builder partern
	@Override
	public List<BuildingEntity> findBuilding(BuildingSearchBuilder builder) {
		List<BuildingEntity> results = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building as b ");
		sql = buildSqlJoiningUsingBuilder(builder, sql);
		sql.append(SystemConstant.ONE_EQUAL_ONE);
		sql = buildSqlCommonUsingBuilder(builder, sql);
		sql = buildSqlSpeciaUsingBuilderl(builder, sql);
		sql.append(" group by b.id");
//		return results;
//		return findByCondition(sql.toString());

		Query query = entiryManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}


	private StringBuilder buildSqlJoiningUsingBuilder(BuildingSearchBuilder builder, StringBuilder sql) {
		if (builder.getStaffId() != null) {
			sql.append(" inner join assignmentbuilding as ab on ab.buildingid = b.id ");
		}
		return sql;
	}
	
	private StringBuilder buildSqlCommonUsingBuilder(BuildingSearchBuilder builder, StringBuilder sql) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if (!fieldName.equals("types") && !fieldName.startsWith("rentarea")
						&& !fieldName.equals("staffid") && !fieldName.startsWith("rentpirce")) {
					Object objectValue = field.get(builder);
					if(objectValue != null && !objectValue.equals("")) {
						if(field.getType().getName().equals("java.lang.String")) {
							sql.append(" and b."+fieldName.toLowerCase()+" like '%"+objectValue+"%'");
						} else if(field.getType().getName().equals("java.lang.Integer")) {
							sql.append(" and b."+fieldName.toLowerCase()+" = "+objectValue+"" );
						}
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return sql;
	}
	
	private StringBuilder buildSqlSpeciaUsingBuilderl(BuildingSearchBuilder builder, StringBuilder sql) {
		Integer rentPirceFrom = builder.getRentPirceFrom();
		Integer rentPirceTo = builder.getRentPirceTo();
		Integer rentAreaFrom = builder.getRentAreaFrom();
		Integer rentAreaTo = builder.getRentAreaTo();
		Long staffId = builder.getStaffId();
		List<String> types = builder.getBuildingTypes();

		if (rentPirceFrom != null) {
			sql.append(" and b.rentpirce >= " + rentPirceFrom + "");
		}
		if (rentPirceTo != null) {
			sql.append(" and b.rentpirce <= " + rentPirceTo + "");
		}
		if (rentAreaFrom != null || rentAreaTo != null) {
			sql.append(" and EXISTS (select value from rentarea r where r.buildingid = b.id");
			if (rentAreaFrom != null) {
				sql.append(" and r.value >= ").append(rentAreaFrom);
			}
			if (rentAreaTo != null) {
				sql.append(" and r.value <= ").append(rentAreaTo);
			}
			sql.append(")");
		}
		if (staffId != null) {
			sql.append(" and ab.staffid = " + staffId + "");
		}
		// java 8
		if (types != null && types.size() > 0) {
			sql.append(" and (");
			String sqlJoin = types.stream().map(item -> "type like '%" + item + "%'").collect(Collectors.joining(" or "));
			sql.append(sqlJoin);
			sql.append(")");
		}
		return sql;
	}
	
	

//	@Override
//	public BuildingEntity findById(Long id) {
//		// SQL native
//		String sql = "select * FROM building as b where b.id = " + id + "";
//		Query query = entiryManager.createNativeQuery(sql, BuildingEntity.class);
//
//		return (BuildingEntity) query.getSingleResult();
//	}
//
//	@Override
//	public void save(BuildingEntity newBuilding) {
//		if(newBuilding.getId() != null) {
//			//update
//			entiryManager.merge(newBuilding);	
//		}else {
//			//insert
//			entiryManager.persist(newBuilding);
//			
//		}
//		
//		//delete
//		//entiryManager.remove(newBuilding);
//		
//	}
//
//	@Override
//	public void delete(BuildingEntity buildingEntity) {
//		entiryManager.remove(buildingEntity);
//		
//	}

}
