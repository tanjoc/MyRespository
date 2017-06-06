package cn.edu.tools;

import java.io.InputStream;
import java.util.Properties;

/**
 * 解析配置文件(jdbc配置文件和config配置文件)
 * 
 * @author Fsx
 * 
 */
public class ConfigUtil {
	/** 配置文件 */
	private static Properties properties;

	/** 时间关系 先静态读取 以后考虑做成懒加载实现 */
	// TODO
	static {
		properties = new Properties();
		/** jdbc.properties文件 */
		InputStream inputStream4Jdbc = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/jdbc.properties");
		/** config.properties文件 */
		InputStream inputStream4Config = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/config.properties");
		try {
			properties.load(inputStream4Jdbc);
			properties.load(inputStream4Config);
		} catch (Exception e) {
			throw new RuntimeException("无法读取JDBC配置文件!");
		}
	}

	/**
	 * 读取配置文件中的属性值
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static String getValue(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 读取配置文件中的属性值
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return 值
	 */
	public static String getValue(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	/**
	 * 读取数据库驱动
	 * 
	 * @return 数据库驱动
	 */
	public static String getDriver() {
		return getValue("driver");
	}

	/**
	 * 读取数据库连接地址
	 * 
	 * @return 数据库连接地址
	 */
	public static String getUrl() {
		return getValue("url");
	}

	/**
	 * 读取用户名
	 * 
	 * @return 用户名
	 */
	public static String getUser() {
		return getValue("user");
	}

	/**
	 * 读取密码
	 * 
	 * @return 密码
	 */
	public static String getPassword() {
		return getValue("password");
	}

	/**
	 * 读取数据库名称
	 * 
	 * @return 数据库名称
	 */
	public static String getSchema() {
		String url = getUrl();
		int indexOfParameter = url.indexOf('?');
		return indexOfParameter == -1 ? url.substring(url.lastIndexOf('/') + 1) : url.substring(url.lastIndexOf('/') + 1, indexOfParameter);
	}

	/**
	 * 读取默认的每页显示记录数量
	 * 
	 * @return 默认的每页显示记录数量
	 */
	public static Integer getDefaultPageSize() {
		return Integer.parseInt(getValue("defaultPageSize"), 10);
	}
}