package cn.edu.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Fsx
 * 
 */
public class ClassUtil {
	/**
	 * 得到类的简单名称<br/>
	 * 如果是封装类 则解包为基础类
	 * 
	 * @param clazz
	 *            类
	 * @return 类的简单名称
	 */
	public static String getClassSimpleName(Class<? extends Object> clazz) {
		clazz = toPrimitive(clazz);
		String name = clazz.getName();
		int index = name.lastIndexOf('.');
		return (index == -1 ? name : name.substring(index + 1)).toLowerCase();
	}

	/**
	 * 如果是8大基本类型的封装类型 则转化为对应的基本类型
	 * 
	 * @param clazz
	 *            原类
	 * @return 如果是8大基本类型之一 则返回对应的基本类型 否则返回原类本身
	 */
	public static Class<? extends Object> toPrimitive(Class<? extends Object> clazz) {
		if (clazz == Byte.class) {
			return byte.class;
		}
		if (clazz == Short.class) {
			return short.class;
		}
		if (clazz == Integer.class) {
			return int.class;
		}
		if (clazz == Long.class) {
			return long.class;
		}
		if (clazz == Float.class) {
			return float.class;
		}
		if (clazz == Double.class) {
			return Double.class;
		}
		if (clazz == Boolean.class) {
			return boolean.class;
		}
		if (clazz == Character.class) {
			return char.class;
		}
		return clazz;
	}

	/**
	 * 新生成一个目标类对象 并复制源类同名字段的值
	 * 
	 * @param sourceObject
	 *            源类对象
	 * @param clazz
	 *            目标类
	 * @return 目标类对象 并且复制了源类同名字段的值
	 */
	public static <T> T clone2Class(Object sourceObject, Class<T> clazz) {
		Field[] sourceFieldArray = sourceObject.getClass().getDeclaredFields();
		Field[] targetFieldArray = clazz.getDeclaredFields();
		Map<Field, Field> map = new HashMap<Field, Field>(sourceFieldArray.length);
		for (Field sourceField : sourceFieldArray) {
			/** final域不会被写入 */
			if (Modifier.isFinal(sourceField.getModifiers())) {
				continue;
			}
			String fieldName = sourceField.getName();
			boolean foundSameField = false;
			for (Field targetField : targetFieldArray) {
				if (fieldName.equals(targetField.getName())) {
					Class<?> sourceFieldType = sourceField.getType();
					Class<?> targetFieldType = targetField.getType();
					if (toPrimitive(sourceFieldType) != toPrimitive(targetFieldType)) {
						throw new RuntimeException(new StringBuilder().append("域").append(fieldName).append("不一致！在源类中是：").append(sourceFieldType.getCanonicalName()).append(", 在目标类中是：").append(targetFieldType.getCanonicalName()).toString());
					}
					map.put(sourceField, targetField);
					foundSameField = true;
					break;
				}
			}
			if (!foundSameField) {
				throw new RuntimeException(new StringBuilder().append("源类").append(sourceObject.getClass().getName()).append("中的域").append(fieldName).append("在目标类").append(clazz.getName()).append("中不存在！").toString());
			}
		}
		T targetObject;
		try {
			targetObject = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(new StringBuilder().append("无法生成目标类").append(clazz.getName()).append("的实例！").toString(), e);
		}

		for (Entry<Field, Field> mapEntry : map.entrySet()) {
			Field sourceField = mapEntry.getKey();
			Field targetField = mapEntry.getValue();
			sourceField.setAccessible(true);
			targetField.setAccessible(true);
			Object value;
			try {
				value = sourceField.get(sourceObject);
			} catch (Exception e) {
				throw new RuntimeException(new StringBuilder().append("无法读取源类").append(sourceObject.getClass().getName()).append("对象中域").append(sourceField.getName()).append("的值！").toString(), e);
			}
			try {
				targetField.set(targetObject, value);
			} catch (Exception e) {
				throw new RuntimeException(new StringBuilder().append("无法将值写入目标类").append(clazz.getName()).append("对象中的域").append(targetField.getName()).append("！").toString(), e);
			}
			sourceField.setAccessible(false);
			targetField.setAccessible(false);
		}
		return targetObject;
	}

	/**
	 * 新生成一个目标类对象 并仅复制与源类同名同类型字段的值
	 * 
	 * @param sourceObject
	 *            源类对象
	 * @param clazz
	 *            目标类
	 * @return 目标类对象 并仅复制了与源类同名同类型字段的值
	 */
	public static <T> T clone2ClassWithSameField(Object sourceObject, Class<T> clazz) {
		Field[] sourceFieldArray = sourceObject.getClass().getDeclaredFields();
		Field[] targetFieldArray = clazz.getDeclaredFields();
		Map<Field, Field> map = new HashMap<Field, Field>(sourceFieldArray.length);
		for (Field sourceField : sourceFieldArray) {
			/** final域不会被写入 */
			if (Modifier.isFinal(sourceField.getModifiers())) {
				continue;
			}
			String fieldName = sourceField.getName();
			for (Field targetField : targetFieldArray) {
				if (fieldName.equals(targetField.getName())) {
					Class<?> sourceFieldType = sourceField.getType();
					Class<?> targetFieldType = targetField.getType();
					if (toPrimitive(sourceFieldType) == toPrimitive(targetFieldType)) {
						map.put(sourceField, targetField);
					}
					break;
				}
			}
		}
		T targetObject;
		try {
			targetObject = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(new StringBuilder().append("无法生成目标类").append(clazz.getName()).append("的实例！").toString(), e);
		}

		for (Entry<Field, Field> mapEntry : map.entrySet()) {
			Field sourceField = mapEntry.getKey();
			Field targetField = mapEntry.getValue();
			sourceField.setAccessible(true);
			targetField.setAccessible(true);
			Object value;
			try {
				value = sourceField.get(sourceObject);
			} catch (Exception e) {
				throw new RuntimeException(new StringBuilder().append("无法读取源类").append(sourceObject.getClass().getName()).append("对象中域").append(sourceField.getName()).append("的值！").toString(), e);
			}
			try {
				targetField.set(targetObject, value);
			} catch (Exception e) {
				throw new RuntimeException(new StringBuilder().append("无法将值写入目标类").append(clazz.getName()).append("对象中的域").append(targetField.getName()).append("！").toString(), e);
			}
			sourceField.setAccessible(false);
			targetField.setAccessible(false);
		}
		return targetObject;
	}
}