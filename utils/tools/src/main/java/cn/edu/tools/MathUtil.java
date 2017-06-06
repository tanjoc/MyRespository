package cn.edu.tools;

/**
 * 数学相关的工具类
 * 
 * @author Fsx
 * 
 */
public class MathUtil {
	/**
	 * 取min和max之间的随机短整数<br/>
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return min-max之间的随机短整数
	 */
	public static short random(short min, short max) {
		/** 如果min>max 则交换min和max */
		if (min > max) {
			return random(max, min);
		}
		return (short)(min + (short)(Math.random() * (max - min + 1)));
	}

	/**
	 * 取min和max之间的随机整数<br/>
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return min-max之间的随机整数
	 */
	public static int random(int min, int max) {
		/** 如果min>max 则交换min和max */
		if (min > max) {
			return random(max, min);
		}
		return min + (int)(Math.random() * (max - min + 1));
	}

	/**
	 * 取min和max之间的随机长整数<br/>
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return min-max之间的随机长整数
	 */
	public static long random(long min, long max) {
		/** 如果min>max 则交换min和max */
		if (min > max) {
			return random(max, min);
		}
		return min + (long)(Math.random() * (max - min + 1));
	}

	/**
	 * 取短整数a和短整数b之间的较大值
	 * 
	 * @param a
	 *            第一个数
	 * @param b
	 *            第二个数
	 * @return a和b之间的较大短整数值
	 */
	public static short max(short a, short b) {
		return a >= b ? a : b;
	}

	/**
	 * 取短整数a和短整数b之间的较小值
	 * 
	 * @param a
	 *            第一个数
	 * @param b
	 *            第二个数
	 * @return a和b之间的较小短整数值
	 */
	public static short min(short a, short b) {
		return a <= b ? a : b;
	}

	/**
	 * 取整数a和整数b之间的较大值
	 * 
	 * @param a
	 *            第一个数
	 * @param b
	 *            第二个数
	 * @return a和b之间的较大整数值
	 */
	public static int max(int a, int b) {
		return a >= b ? a : b;
	}

	/**
	 * 取整数a和整数b之间的较小值
	 * 
	 * @param a
	 *            第一个数
	 * @param b
	 *            第二个数
	 * @return a和b之间的较小整数值
	 */
	public static int min(int a, int b) {
		return a <= b ? a : b;
	}

	/**
	 * 取长整数a和长整数b之间的较大值
	 * 
	 * @param a
	 *            第一个数
	 * @param b
	 *            第二个数
	 * @return a和b之间的较大长整数值
	 */
	public static long max(long a, long b) {
		return a >= b ? a : b;
	}

	/**
	 * 取长整数a和长整数b之间的较小值
	 * 
	 * @param a
	 *            第一个数
	 * @param b
	 *            第二个数
	 * @return a和b之间的较小长整数值
	 */
	public static long min(long a, long b) {
		return a <= b ? a : b;
	}

	/**
	 * 取短整数min和短整数max之间 且不是短整数exclude的随机短整数值<br/>
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param exclude
	 *            不包含的值
	 * @return min和max之间 且不是exclude的随机短整数值
	 */
	public static short randomExclude(short min, short max, short exclude) {
		return randomExclude(min, max, exclude, exclude);
	}

	/**
	 * 取短整数min和短整数max之间 且不在短整数excludeMin和短整数excludeMax之间的随机短整数值<br/>
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param excludeMin
	 *            不包含的最小值
	 * @param excludeMax
	 *            不包含的最大值
	 * @return min和max之间 且不在excludeMin和excludeMax之间的随机短整数值
	 */
	public static short randomExclude(short min, short max, short excludeMin, short excludeMax) {
		/** 如果min>max 则交换min和max */
		if (min > max) {
			return randomExclude(max, min, excludeMin, excludeMax);
		}
		/** 如果min>max 则交换min和max */
		if (excludeMin > excludeMax) {
			return randomExclude(min, max, excludeMax, excludeMin);
		}
		/** 如果排除区间在取值区间之外 则直接在取值区间中取值 */
		if (excludeMin > max || excludeMax < min) {
			return random(min, max);
		}
		/** 范围检测及修正 */
		if (excludeMin <= min && excludeMax >= max) {
			throw new IllegalArgumentException("取值区间[" + min + "," + max + "]是排除区间[" + excludeMin + "," + excludeMax + "]的子集 因此没有随机数可以选取!");
		}
		if (excludeMin < min) {
			excludeMin = min;
		}
		if (excludeMax > max) {
			excludeMax = max;
		}
		/** 计算偏移量 取随机数 还原区间 */
		short offset = (short)(max - min + 1);
		short tempResult = random((short)(excludeMax + 1), (short)(excludeMin - 1 + offset));
		return tempResult <= max ? tempResult : (short)(tempResult - offset);
	}

	/**
	 * 取整数min和整数max之间 且不是整数exclude的随机整数值<br/>
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param exclude
	 *            不包含的值
	 * @return min和max之间 且不是exclude的随机整数值
	 */
	public static int randomExclude(int min, int max, int exclude) {
		return randomExclude(min, max, exclude, exclude);
	}

	/**
	 * 取整数min和整数max之间 且不在整数excludeMin和整数excludeMax之间的随机整数值<br/>
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param excludeMin
	 *            不包含的最小值
	 * @param excludeMax
	 *            不包含的最大值
	 * @return min和max之间 且不在excludeMin和excludeMax之间的随机整数值
	 */
	public static int randomExclude(int min, int max, int excludeMin, int excludeMax) {
		/** 如果min>max 则交换min和max */
		if (min > max) {
			return randomExclude(max, min, excludeMin, excludeMax);
		}
		/** 如果min>max 则交换min和max */
		if (excludeMin > excludeMax) {
			return randomExclude(min, max, excludeMax, excludeMin);
		}
		/** 如果排除区间在取值区间之外 则直接在取值区间中取值 */
		if (excludeMin > max || excludeMax < min) {
			return random(min, max);
		}
		/** 范围检测及修正 */
		if (excludeMin <= min && excludeMax >= max) {
			throw new IllegalArgumentException("取值区间[" + min + "," + max + "]是排除区间[" + excludeMin + "," + excludeMax + "]的子集 因此没有随机数可以选取!");
		}
		if (excludeMin < min) {
			excludeMin = min;
		}
		if (excludeMax > max) {
			excludeMax = max;
		}
		/** 计算偏移量 取随机数 还原区间 */
		int offset = max - min + 1;
		int tempResult = random(excludeMax + 1, excludeMin - 1 + offset);
		return tempResult <= max ? tempResult : tempResult - offset;
	}

	/**
	 * 取长整数min和长整数max之间 且不是长整数exclude的随机长整数值<br/>
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param exclude
	 *            不包含的值
	 * @return min和max之间 且不是exclude的随机长整数值
	 */
	public static long randomExclude(long min, long max, long exclude) {
		return randomExclude(min, max, exclude, exclude);
	}

	/**
	 * 取长整数min和长整数max之间 且不在长整数excludeMin和长整数excludeMax之间的随机长整数值<br/>
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param excludeMin
	 *            不包含的最小值
	 * @param excludeMax
	 *            不包含的最大值
	 * @return min和max之间 且不在excludeMin和excludeMax之间的随机长整数值
	 */
	public static long randomExclude(long min, long max, long excludeMin, long excludeMax) {
		/** 如果min>max 则交换min和max */
		if (min > max) {
			return randomExclude(max, min, excludeMin, excludeMax);
		}
		/** 如果excludeMin>excludeMax 则交换excludeMin和excludeMax */
		if (excludeMin > excludeMax) {
			return randomExclude(min, max, excludeMax, excludeMin);
		}
		/** 如果排除区间在取值区间之外 则直接在取值区间中取值 */
		if (excludeMin > max || excludeMax < min) {
			return random(min, max);
		}
		/** 范围检测及修正 */
		if (excludeMin <= min && excludeMax >= max) {
			throw new IllegalArgumentException("取值区间[" + min + "," + max + "]是排除区间[" + excludeMin + "," + excludeMax + "]的子集 因此没有随机数可以选取!");
		}
		if (excludeMin < min) {
			excludeMin = min;
		}
		if (excludeMax > max) {
			excludeMax = max;
		}
		/** 计算偏移量 取随机数 还原区间 */
		long offset = max - min + 1;
		long tempResult = random(excludeMax + 1, excludeMin - 1 + offset);
		return tempResult <= max ? tempResult : tempResult - offset;
	}
}