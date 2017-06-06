package cn.edu.tools;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 集合相关的工具类
 * 
 * @author Fsx
 * 
 */
public class CollectionUtil {
	/**
	 * 判断一个集合是否不为空且元素个数大于0
	 * 
	 * @param collection
	 *            集合
	 * @return 集合是否不为空且元素个数大于0
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return collection != null && collection.size() > 0;
	}

	/**
	 * 判断一个集合是否为空或元素个数等于0
	 * 
	 * @param collection
	 *            集合
	 * @return 集合是否为空或元素个数等于0
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}

	/**
	 * 将列表反序 并返回它自身
	 * 
	 * @param list
	 *            列表
	 * @return 反序后的数组自身
	 */
	public static List<?> reverse(List<?> list) {
		Collections.reverse(list);
		return list;
	}
}