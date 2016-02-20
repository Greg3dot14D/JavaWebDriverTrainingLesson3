package ru.greg3d.util;

import java.lang.reflect.Field;

public class Fields {
	
	// Возвращает значение Объекта типа строка в (приватном) поле
	public static String getValueOfString(Object object, String fieldName){
		return getValueOfObject(object,fieldName).toString();
	}
	
	public static String getValueOfString(Object object, Object fieldObject){
		return getValueOfObject(object,fieldObject.getClass().getName()).toString();
	}	
	
	// Возвращает значение Объекта в (приватном) поле 
	public static Object getValueOfObject(Object object, String fieldName){
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(object);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
