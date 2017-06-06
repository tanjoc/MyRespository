package cn.edu.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串相关的工具类
 * 
 * @author Fsx
 * 
 */
public class StringUtil {
	/**
	 * 判断一个字符串是否为空或等于空字符串
	 * 
	 * @param s
	 *            字符串
	 * @return 是否为空或空字符串
	 */
	public static final boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * 判断一个字符串是否不为空且不等于空字符串
	 * 
	 * @param s
	 *            字符串
	 * @return 是否不为空且不等于空字符串
	 */
	public static final boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	/**
	 * 判断一个字符串是否不为空且不等于空字符串 也不等于"0"
	 * 
	 * @param s
	 *            字符串
	 * @return 是否不为空且不等于空字符串 也不等于"0"
	 */
	public static final boolean isNotEmptyOr0(String s) {
		return isNotEmpty(s) && !s.trim().equals("0");
	}

	/**
	 * 去除字符串的头尾空格(包括中文空格)
	 * 
	 * @param input
	 *            输入的字符串
	 * @return 去掉头尾空格后的字符串
	 */
	public static String trimAll(String input) {
		return input.replaceAll("(^\\pZ+)|(\\pZ+$)", "");
	}

	/**
	 * 去除字符串的头部空格(包括中文空格)
	 * 
	 * @param input
	 *            输入的字符串
	 * @return 去掉头部空格后的字符串
	 */
	public static String trimAllHead(String input) {
		return input.replaceAll("^\\pZ+", "");
	}

	/**
	 * 去除字符串的尾部空格(包括中文空格)
	 * 
	 * @param input
	 *            输入的字符串
	 * @return 去掉尾部空格后的字符串
	 */
	public static String trimAllTail(String input) {
		return input.replaceAll("\\pZ+$", "");
	}

	/**
	 * 去掉字符串中的单引号和双引号 为了避免输入法中自带的分词符号和自动完成组件同时使用时导致SQL错误的BUG
	 * 
	 * @param input
	 *            输入的字符串
	 * @return 去掉单引号和双引号后的字符串
	 */
	public static final String removeQuote(String input) {
		if (input == null) {
			return null;
		}
		return input.replace("\'", "").replace("\"", "");
	}

	/**
	 * 将一个对象转化为字符串(为了避免JAVA本身的空对象转换为null这种情况)
	 * 
	 * @param object
	 *            要转化成字符串的对象
	 * @return 转化成的字符串
	 */
	public static final String toString(Object object) {
		return object != null ? object.toString() : "";
	}

	/**
	 * 将字符串数组拼接成"a,b,c,d"的形式 默认以逗号拼接
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @return 拼接后的字符串
	 */
	public static final String join(String[] stringArray) {
		return join(stringArray, ',');
	}

	/**
	 * 将字符串数组拼接成"a b c d"的形式 中间以空格拼接
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @return 拼接后的字符串
	 */
	public static final String joinWithSpace(String[] stringArray) {
		return join(stringArray, ' ');
	}

	/**
	 * 将字符串数组拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @param separator
	 *            分隔符
	 * @return 拼接后的字符串
	 */
	public static final String join(String[] stringArray, char separator) {
		return join(stringArray, String.valueOf(separator));
	}

	/**
	 * 将字符串数组拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @param separator
	 *            分隔字符串
	 * @return 拼接后的字符串
	 */
	public static final String join(String[] stringArray, String separator) {
		if (stringArray == null || stringArray.length == 0) {
			return "";
		}
		if (stringArray.length == 1) {
			return stringArray[0];
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(stringArray[0]);
		for (int i = 1; i < stringArray.length; i++) {
			if (stringArray[i] == null) {
				continue;
			}
			stringBuilder.append(separator).append(stringArray[i]);
		}
		return stringBuilder.toString();
	}

	/**
	 * 将字符串列表拼接成"a,b,c,d"的形式 默认以逗号拼接
	 * 
	 * @param stringList
	 *            字符串列表
	 * @return 拼接后的字符串
	 */
	public static final String join(List<String> stringList) {
		return join(stringList, ',');
	}

	/**
	 * 将字符串列表拼接成"a b c d"的形式 中间以空格拼接
	 * 
	 * @param stringList
	 *            字符串列表
	 * @return 拼接后的字符串
	 */
	public static final String joinWithSpace(List<String> stringList) {
		return join(stringList, ' ');
	}

	/**
	 * 将字符串列表拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param stringList
	 *            字符串列表
	 * @param separator
	 *            分隔符
	 * @return 拼接后的字符串
	 */
	public static final String join(List<String> stringList, char separator) {
		return join(stringList, String.valueOf(separator));
	}

	/**
	 * 将字符串列表拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param stringList
	 *            字符串列表
	 * @param separator
	 *            分隔字符串
	 * @return 拼接后的字符串
	 */
	public static final String join(List<String> stringList, String separator) {
		if (stringList == null || stringList.size() == 0) {
			return "";
		}
		if (stringList.size() == 1) {
			return stringList.get(0);
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(stringList.get(0));
		for (int i = 1; i < stringList.size(); i++) {
			if (stringList.get(i) == null) {
				continue;
			}
			stringBuilder.append(separator).append(stringList.get(i));
		}
		return stringBuilder.toString();
	}

	/**
	 * 将长整数数组拼接成"a,b,c,d"的形式 默认以逗号拼接
	 * 
	 * @param longArray
	 *            长整数数组
	 * @return 拼接后的字符串
	 */
	public static final String joinLong(Long[] longArray) {
		return joinLong(longArray, ',');
	}

	/**
	 * 将长整数数组拼接成"a b c d"的形式 中间以空格拼接
	 * 
	 * @param longArray
	 *            长整数数组
	 * @return 拼接后的字符串
	 */
	public static final String joinLongWithSpace(Long[] longArray) {
		return joinLong(longArray, ' ');
	}

	/**
	 * 将长整数数组拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param longArray
	 *            长整数数组
	 * @param separator
	 *            分隔符
	 * @return 拼接后的字符串
	 */
	public static final String joinLong(Long[] longArray, char separator) {
		return joinLong(longArray, String.valueOf(separator));
	}

	/**
	 * 将长整数数组拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param longArray
	 *            长整数数组
	 * @param separator
	 *            分隔字符串
	 * @return 拼接后的字符串
	 */
	public static final String joinLong(Long[] longArray, String separator) {
		if (longArray == null || longArray.length == 0) {
			return "";
		}
		if (longArray.length == 1) {
			return longArray[0].toString();
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(longArray[0]);
		for (int i = 1; i < longArray.length; i++) {
			if (longArray[i] == null) {
				continue;
			}
			stringBuilder.append(separator).append(longArray[i].longValue());
		}
		return stringBuilder.toString();
	}

	/**
	 * 将长整数列表拼接成"a,b,c,d"的形式 默认以逗号拼接
	 * 
	 * @param longList
	 *            长整数列表
	 * @return 拼接后的字符串
	 */
	public static final String joinLong(List<Long> longList) {
		return joinLong(longList, ',');
	}

	/**
	 * 将长整数列表拼接成"a b c d"的形式 中间以空格拼接
	 * 
	 * @param longList
	 *            长整数列表
	 * @return 拼接后的字符串
	 */
	public static final String joinLongWithSpace(List<Long> longList) {
		return joinLong(longList, ' ');
	}

	/**
	 * 将长整数列表拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param longList
	 *            长整数列表
	 * @param separator
	 *            分隔符
	 * @return 拼接后的字符串
	 */
	public static final String joinLong(List<Long> longList, char separator) {
		return joinLong(longList, String.valueOf(separator));
	}

	/**
	 * 将长整数列表拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param longList
	 *            长整数列表
	 * @param separator
	 *            分隔字符串
	 * @return 拼接后的字符串
	 */
	public static final String joinLong(List<Long> longList, String separator) {
		if (longList == null || longList.size() == 0) {
			return "";
		}
		if (longList.size() == 1) {
			return longList.get(0).toString();
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(longList.get(0));
		for (int i = 1; i < longList.size(); i++) {
			if (longList.get(i) == null) {
				continue;
			}
			stringBuilder.append(separator).append(longList.get(i).longValue());
		}
		return stringBuilder.toString();
	}

	/**
	 * 将整数数组拼接成"a,b,c,d"的形式 默认以逗号拼接
	 * 
	 * @param integerArray
	 *            整数数组
	 * @return 拼接后的字符串
	 */
	public static final String joinInteger(Integer[] integerArray) {
		return joinInteger(integerArray, ',');
	}

	/**
	 * 将整数数组拼接成"a b c d"的形式 中间以空格拼接
	 * 
	 * @param integerArray
	 *            整数数组
	 * @return 拼接后的字符串
	 */
	public static final String joinIntegerWithSpace(Integer[] integerArray) {
		return joinInteger(integerArray, ' ');
	}

	/**
	 * 将整数数组拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param integerArray
	 *            整数数组
	 * @param separator
	 *            分隔符
	 * @return 拼接后的字符串
	 */
	public static final String joinInteger(Integer[] integerArray, char separator) {
		return joinInteger(integerArray, String.valueOf(separator));
	}

	/**
	 * 将整数数组拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param integerArray
	 *            整数数组
	 * @param separator
	 *            分隔字符串
	 * @return 拼接后的字符串
	 */
	public static final String joinInteger(Integer[] integerArray, String separator) {
		if (integerArray == null || integerArray.length == 0) {
			return "";
		}
		if (integerArray.length == 1) {
			return integerArray[0].toString();
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(integerArray[0]);
		for (int i = 1; i < integerArray.length; i++) {
			if (integerArray[i] == null) {
				continue;
			}
			stringBuilder.append(separator).append(integerArray[i].intValue());
		}
		return stringBuilder.toString();
	}

	/**
	 * 将整数列表拼接成"a,b,c,d"的形式 默认以逗号拼接
	 * 
	 * @param integerList
	 *            整数列表
	 * @return 拼接后的字符串
	 */
	public static final String joinInteger(List<Integer> integerList) {
		return joinInteger(integerList, ',');
	}

	/**
	 * 将整数列表拼接成"a b c d"的形式 中间以空格拼接
	 * 
	 * @param integerList
	 *            整数列表
	 * @return 拼接后的字符串
	 */
	public static final String joinIntegerWithSpace(List<Integer> integerList) {
		return joinInteger(integerList, ' ');
	}

	/**
	 * 将整数列表拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param integerList
	 *            整数列表
	 * @param separator
	 *            分隔符
	 * @return 拼接后的字符串
	 */
	public static final String joinInteger(List<Integer> integerList, char separator) {
		return joinInteger(integerList, String.valueOf(separator));
	}

	/**
	 * 将整数列表拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param integerList
	 *            整数列表
	 * @param separator
	 *            分隔字符串
	 * @return 拼接后的字符串
	 */
	public static final String joinInteger(List<Integer> integerList, String separator) {
		if (integerList == null || integerList.size() == 0) {
			return "";
		}
		if (integerList.size() == 1) {
			return integerList.get(0).toString();
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(integerList.get(0));
		for (int i = 1; i < integerList.size(); i++) {
			if (integerList.get(i) == null) {
				continue;
			}
			stringBuilder.append(separator).append(integerList.get(i).intValue());
		}
		return stringBuilder.toString();
	}

	/**
	 * 将短整数数组拼接成"a,b,c,d"的形式 默认以逗号拼接
	 * 
	 * @param shortArray
	 *            短整数数组
	 * @return 拼接后的字符串
	 */
	public static final String joinShort(Short[] shortArray) {
		return joinShort(shortArray, ',');
	}

	/**
	 * 将短整数数组拼接成"a b c d"的形式 中间以空格拼接
	 * 
	 * @param shortArray
	 *            短整数数组
	 * @return 拼接后的字符串
	 */
	public static final String joinShortWithSpace(Short[] shortArray) {
		return joinShort(shortArray, ' ');
	}

	/**
	 * 将短整数数组拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param shortArray
	 *            短整数数组
	 * @param separator
	 *            分隔符
	 * @return 拼接后的字符串
	 */
	public static final String joinShort(Short[] shortArray, char separator) {
		return joinShort(shortArray, String.valueOf(separator));
	}

	/**
	 * 将短整数数组拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param shortArray
	 *            短整数数组
	 * @param separator
	 *            分隔字符串
	 * @return 拼接后的字符串
	 */
	public static final String joinShort(Short[] shortArray, String separator) {
		if (shortArray == null || shortArray.length == 0) {
			return "";
		}
		if (shortArray.length == 1) {
			return shortArray[0].toString();
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(shortArray[0]);
		for (int i = 1; i < shortArray.length; i++) {
			if (shortArray[i] == null) {
				continue;
			}
			stringBuilder.append(separator).append(shortArray[i].intValue());
		}
		return stringBuilder.toString();
	}

	/**
	 * 将短整数列表拼接成"a,b,c,d"的形式 默认以逗号拼接
	 * 
	 * @param shortList
	 *            短整数列表
	 * @return 拼接后的字符串
	 */
	public static final String joinShort(List<Short> shortList) {
		return joinShort(shortList, ',');
	}

	/**
	 * 将短整数列表拼接成"a b c d"的形式 中间以空格拼接
	 * 
	 * @param shortList
	 *            短整数列表
	 * @return 拼接后的字符串
	 */
	public static final String joinShortWithSpace(List<Short> shortList) {
		return joinShort(shortList, ' ');
	}

	/**
	 * 将短整数列表拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param shortList
	 *            短整数列表
	 * @param separator
	 *            分隔符
	 * @return 拼接后的字符串
	 */
	public static final String joinShort(List<Short> shortList, char separator) {
		return joinShort(shortList, String.valueOf(separator));
	}

	/**
	 * 将短整数列表拼接成"a b c d"的形式 中间以separator分隔
	 * 
	 * @param shortList
	 *            短整数列表
	 * @param separator
	 *            分隔字符串
	 * @return 拼接后的字符串
	 */
	public static final String joinShort(List<Short> shortList, String separator) {
		if (shortList == null || shortList.size() == 0) {
			return "";
		}
		if (shortList.size() == 1) {
			return shortList.get(0).toString();
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(shortList.get(0));
		for (int i = 1; i < shortList.size(); i++) {
			if (shortList.get(i) == null) {
				continue;
			}
			stringBuilder.append(separator).append(shortList.get(i).intValue());
		}
		return stringBuilder.toString();
	}

	/**
	 * 将字符串分隔成字符串数组 默认以逗号分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的字符串数组
	 */
	public static final String[] toStringArray(String param) {
		return toStringArray(param, ',');
	}

	/**
	 * 将字符串分隔成字符串数组 以空格分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的字符串数组
	 */
	public static final String[] toStringArrayBySpace(String param) {
		return toStringArray(param, ' ');
	}

	/**
	 * 将字符串分隔成字符串数组 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return 分隔成的字符串数组
	 */
	public static final String[] toStringArray(String param, char separator) {
		return toStringArray(param, String.valueOf(separator));
	}

	/**
	 * 将字符串分隔成字符串数组 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔字符串
	 * @return 分隔成的字符串数组
	 */
	public static final String[] toStringArray(String param, String separator) {
		if (isEmpty(param)) {
			return new String[0];
		}
		return param.split(separator.equals(",") ? "\\," : separator);
	}

	/**
	 * 将字符串分隔成字符串列表 默认以逗号分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的字符串列表
	 */
	public static final List<String> toStringList(String param) {
		return toStringList(param, ',');
	}

	/**
	 * 将字符串分隔成字符串列表 以空格分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的字符串列表
	 */
	public static final List<String> toStringListByWhiteSpace(String param) {
		return toStringList(param, ' ');
	}

	/**
	 * 将字符串分隔成字符串列表 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的字符串列表
	 */
	public static final List<String> toStringList(String param, char separator) {
		return toStringList(param, String.valueOf(separator));
	}

	/**
	 * 将字符串分隔成字符串列表 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔字符串
	 * @return 分隔成的字符串列表
	 */
	public static final List<String> toStringList(String param, String separator) {
		String[] stringArray = toStringArray(param, separator);
		List<String> stringList = new ArrayList<String>();
		for (int i = 0; i < stringArray.length; i++) {
			stringList.add(stringArray[i]);
		}
		return stringList;
	}

	/**
	 * 将字符串分隔成长整数数组 默认以逗号分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的长整数数组
	 */
	public static final Long[] toLongArray(String param) {
		return toLongArray(param, ',');
	}

	/**
	 * 将字符串分隔成长整数数组 以空格分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的长整数数组
	 */
	public static final Long[] toLongArrayBySpace(String param) {
		return toLongArray(param, ' ');
	}

	/**
	 * 将字符串分隔成长整数数组 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return 分隔成的长整数数组
	 */
	public static final Long[] toLongArray(String param, char separator) {
		return toLongArray(param, String.valueOf(separator));
	}

	/**
	 * 将字符串分隔成长整数数组 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔字符串
	 * @return 分隔成的长整数数组
	 */
	public static final Long[] toLongArray(String param, String separator) {
		String[] stringArray = toStringArray(param, separator);
		Long[] longArray = new Long[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			longArray[i] = Long.parseLong(stringArray[i]);
		}
		return longArray;
	}

	/**
	 * 将字符串分隔成长整数列表 默认以逗号分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的长整数列表
	 */
	public static final List<Long> toLongList(String param) {
		return toLongList(param, ',');
	}

	/**
	 * 将字符串分隔成长整数列表 以空格分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的长整数列表
	 */
	public static final List<Long> toLongListByWhiteSpace(String param) {
		return toLongList(param, ' ');
	}

	/**
	 * 将字符串分隔成长整数列表 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的长整数列表
	 */
	public static final List<Long> toLongList(String param, char separator) {
		return toLongList(param, String.valueOf(separator));
	}

	/**
	 * 将字符串分隔成长整数列表 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔字符串
	 * @return 分隔成的长整数列表
	 */
	public static final List<Long> toLongList(String param, String separator) {
		List<String> stringList = toStringList(param, separator);
		List<Long> longList = new ArrayList<Long>(stringList.size());
		for (int i = 0; i < stringList.size(); i++) {
			longList.add(Long.parseLong(stringList.get(i)));
		}
		return longList;
	}

	/**
	 * 将字符串分隔成整数数组 默认以逗号分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的整数数组
	 */
	public static final Integer[] toIntegerArray(String param) {
		return toIntegerArray(param, ',');
	}

	/**
	 * 将字符串分隔成整数数组 以空格分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的整数数组
	 */
	public static final Integer[] toIntegerArrayBySpace(String param) {
		return toIntegerArray(param, ' ');
	}

	/**
	 * 将字符串分隔成整数数组 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return 分隔成的整数数组
	 */
	public static final Integer[] toIntegerArray(String param, char separator) {
		return toIntegerArray(param, String.valueOf(separator));
	}

	/**
	 * 将字符串分隔成整数数组 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔字符串
	 * @return 分隔成的整数数组
	 */
	public static final Integer[] toIntegerArray(String param, String separator) {
		String[] stringArray = toStringArray(param, separator);
		Integer[] integerArray = new Integer[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			integerArray[i] = Integer.parseInt(stringArray[i]);
		}
		return integerArray;
	}

	/**
	 * 将字符串分隔成整数列表 默认以逗号分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的整数列表
	 */
	public static final List<Integer> toIntegerList(String param) {
		return toIntegerList(param, ',');
	}

	/**
	 * 将字符串分隔成整数列表 以空格分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的整数列表
	 */
	public static final List<Integer> toIntegerListByWhiteSpace(String param) {
		return toIntegerList(param, ' ');
	}

	/**
	 * 将字符串分隔成整数列表 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的整数列表
	 */
	public static final List<Integer> toIntegerList(String param, char separator) {
		return toIntegerList(param, String.valueOf(separator));
	}

	/**
	 * 将字符串分隔成整数列表 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔字符串
	 * @return 分隔成的整数列表
	 */
	public static final List<Integer> toIntegerList(String param, String separator) {
		List<String> stringList = toStringList(param, separator);
		List<Integer> integerList = new ArrayList<Integer>(stringList.size());
		for (int i = 0; i < stringList.size(); i++) {
			integerList.add(Integer.parseInt(stringList.get(i)));
		}
		return integerList;
	}

	/**
	 * 将字符串分隔成短整数数组 默认以逗号分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的短整数数组
	 */
	public static final Short[] toShortArray(String param) {
		return toShortArray(param, ',');
	}

	/**
	 * 将字符串分隔成短整数数组 以空格分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的短整数数组
	 */
	public static final Short[] toShortArrayBySpace(String param) {
		return toShortArray(param, ' ');
	}

	/**
	 * 将字符串分隔成短整数数组 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return 分隔成的短整数数组
	 */
	public static final Short[] toShortArray(String param, char separator) {
		return toShortArray(param, String.valueOf(separator));
	}

	/**
	 * 将字符串分隔成短整数数组 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔字符串
	 * @return 分隔成的短整数数组
	 */
	public static final Short[] toShortArray(String param, String separator) {
		String[] stringArray = toStringArray(param, separator);
		Short[] shortArray = new Short[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			shortArray[i] = Short.parseShort(stringArray[i]);
		}
		return shortArray;
	}

	/**
	 * 将字符串分隔成短整数列表 默认以逗号分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的短整数列表
	 */
	public static final List<Short> toShortList(String param) {
		return toShortList(param, ',');
	}

	/**
	 * 将字符串分隔成短整数列表 以空格分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的短整数列表
	 */
	public static final List<Short> toShortListByWhiteSpace(String param) {
		return toShortList(param, ' ');
	}

	/**
	 * 将字符串分隔成短整数列表 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @return 分隔成的短整数列表
	 */
	public static final List<Short> toShortList(String param, char separator) {
		return toShortList(param, String.valueOf(separator));
	}

	/**
	 * 将字符串分隔成短整数列表 以separator分隔
	 * 
	 * @param param
	 *            字符串
	 * @param separator
	 *            分隔字符串
	 * @return 分隔成的短整数列表
	 */
	public static final List<Short> toShortList(String param, String separator) {
		List<String> stringList = toStringList(param, separator);
		List<Short> shortList = new ArrayList<Short>(stringList.size());
		for (int i = 0; i < stringList.size(); i++) {
			shortList.add(Short.parseShort(stringList.get(i)));
		}
		return shortList;
	}
}