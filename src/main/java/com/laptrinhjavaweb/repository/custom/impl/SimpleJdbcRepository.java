package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.mapper.ResultsetMapper;
import com.laptrinhjavaweb.repository.custom.JdbcRepositoryCustom;
import com.laptrinhjavaweb.utils.ConnectionUtils;

import javax.persistence.*;

public class SimpleJdbcRepository<T> implements JdbcRepositoryCustom<T> {
	
	private Class<T> tClass = null;
	public SimpleJdbcRepository() {
		tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public List<T> findByCondition(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
			return resultsetMapper.mapRow(rs, tClass);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}

	@Override
	public T findId(Long id) {
		List<T> results = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String tableName = null;;
			if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			String sql = new String("SELECT * FROM "+tableName+" where id = " + id);
			rs = stmt.executeQuery(sql);

			ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();

			return resultsetMapper.mapRow(rs, tClass).size() > 0 ? results.get(0) : null;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtils.getConnection();
			StringBuilder sql = createSQLInsert();
			stmt = conn.prepareStatement(sql.toString());
			Class<?> zClass = object.getClass();
			Field[] fields = zClass.getDeclaredFields();
			int parameterIndex = 1;
			for(Field field: fields) {
				if(field.isAnnotationPresent((Column.class))){
                    field.setAccessible(true);
                    stmt.setObject(parameterIndex, field.get(object));
                    parameterIndex++;
                }
			}
//			Class<?> parentClass = zClass.getSuperclass();
//			int indexParent = fields.length + 1;
//			while (parentClass != null) {
//				for(Field field: parentClass.getDeclaredFields()) {
//					field.setAccessible(true);
//					stmt.setObject(indexParent, field.get(object));
//					indexParent++;
//				}
//				parentClass = parentClass.getSuperclass();
//			}

            int row = stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (row > 0 ){
                while (rs.next()){
                    return rs.getLong(1);
                }
            }
		} catch (SQLException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private StringBuilder createSQLInsert() {
		String tableName = "";
		if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation((Table.class));
			tableName = table.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder values = new StringBuilder("");
		for(Field field: tClass.getDeclaredFields()) {
			if(fields.length() > 1 && field.isAnnotationPresent((Column.class))) {
				fields.append(",");
				values.append(",");
			}
			if(field.isAnnotationPresent((Column.class))){
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				values.append("?");
			}
		}
//		Class<?> parentClass = tClass.getSuperclass();
////		while (parentClass != null) {
////			for(Field field: parentClass.getDeclaredFields()) {
////				if(fields.length() > 1 && field.isAnnotationPresent((Column.class))) {
////					fields.append(",");
////					values.append(",");
////				}
////				if(field.isAnnotationPresent((Column.class))){
////					Column column = field.getAnnotation(Column.class);
////					fields.append(column.name());
////					values.append("?");
////				}
////			}
////			parentClass = parentClass.getSuperclass();
////		}
		StringBuilder sql = new StringBuilder("insert into "+tableName+"("+fields.toString()+") values (" + values + ")");
		return sql;
	}

	@Override
	public void update(Object object, Long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getConnection();
			StringBuilder sql = createSQLUpdate(id);
			stmt = conn.prepareStatement(sql.toString());
			Class<?> zClass = object.getClass();
			Field[] fields = zClass.getDeclaredFields();
			int parameterIndex = 1;
			for(Field field: fields) {
				if(field.isAnnotationPresent((Column.class))){
                    field.setAccessible(true);
                    stmt.setObject(parameterIndex, field.get(object));
                    parameterIndex++;
                }
			}
			stmt.executeUpdate();
		} catch (SQLException | IllegalAccessException e) {
    			e.printStackTrace();
		}
	}

	private StringBuilder createSQLUpdate(Long id) {
		String tableName = "";
		if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation((Table.class));
			tableName = table.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder parentFields = new StringBuilder("");
		StringBuilder sql = new StringBuilder("update "+tableName+" set ") ;
		for(Field field: tClass.getDeclaredFields()) {
			if(fields.length() > 1 && field.isAnnotationPresent((Column.class))) {
				fields.append(", ");
			}
			if(field.isAnnotationPresent((Column.class))){
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name()).append(" = ").append("?");
			}
		}
		sql.append(fields.toString() + " Where id = " + id );
		return sql;
	}

	@Override
	public void delete(Long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String tableName = "";
		try {
			conn = ConnectionUtils.getConnection();
			if (tClass.isAnnotationPresent(Table.class)) {
				Table tableAnnotation = tClass.getAnnotation(Table.class);
				tableName = tableAnnotation.name();
			}
			StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE id = ?");
			stmt = conn.prepareStatement(sql.toString());
			if (conn != null) {
				stmt.setObject(1, id);
				stmt.executeUpdate();
//				conn.commit();
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteProperty(String where) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String tableName = "";
		try {
			conn = ConnectionUtils.getConnection();
			if (tClass.isAnnotationPresent(Table.class)) {
				Table tableAnnotation = tClass.getAnnotation(Table.class);
				tableName = tableAnnotation.name();
			}
			StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName).append(where);
			stmt = conn.prepareStatement(sql.toString());
			if (conn != null) {
				stmt.executeUpdate();
				conn.commit();
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
