package cn.edu.tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 将JSON字符串转换为目标类
	 * 
	 * @param jsonString
	 *            JSON字符串
	 * @param clazz
	 *            目标类
	 * @return 目标类
	 */
	public static <T> T toBean(String jsonString, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将JSON字符串转换为目标对象
	 * 
	 * @param jsonString
	 *            JSON字符串
	 * @param typeReference
	 *            目标对象类型
	 * @return 目标对象
	 */
	public static <T> T toBean(String jsonString, TypeReference<T> typeReference) {
		try {
			return objectMapper.readValue(jsonString, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}