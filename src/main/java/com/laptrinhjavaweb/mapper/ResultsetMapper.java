package com.laptrinhjavaweb.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Column;


public class ResultsetMapper<T> {
	public List<T> mapRow(ResultSet rs, Class<T> tClass){
		List<T> rerults = new ArrayList<>();
		try {
			Field[] fields = tClass.getDeclaredFields();
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			while(rs.next()){		
				T object = tClass.newInstance();
				for (int i = 0 ; i<resultSetMetaData.getColumnCount(); i++) {
					String columnName = resultSetMetaData.getColumnName(i+1);
					Object columnValue = rs.getObject(i + 1);
					for(Field field: fields) {
						if( field.isAnnotationPresent(Column.class)) {
							Column column = field.getAnnotation(Column.class);
							if(column.name().equals(columnName) && columnValue != null) {
								BeanUtils.setProperty(object, field.getName(), columnValue);
								break;
							}
						}
					}
					Class<?> parrentClass = tClass.getSuperclass();
					while( parrentClass != null) {
						Field[] fieldParents = parrentClass.getDeclaredFields();
						for(Field field: fieldParents) {
							if( field.isAnnotationPresent(Column.class)) {
								Column column = field.getAnnotation(Column.class);
								if(column.name().equals(columnName) && columnValue != null) {
									BeanUtils.setProperty(object, field.getName(), columnValue);
									break;
								}
							}
						}
						parrentClass = parrentClass.getSuperclass();
					}
					
				}
				rerults.add(object);
	         }
			return rerults;
		} catch (SQLException | IllegalAccessException | InstantiationException |InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
