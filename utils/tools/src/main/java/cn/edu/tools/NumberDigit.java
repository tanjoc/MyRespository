package cn.edu.tools;

import java.math.BigDecimal;

/** 将一个数字的整数部分和小数部分按（十进制）位填充为数组 */

/** 其中整数部分为从低位到高位 小数部分为从从高位到低位 */
public class NumberDigit {
	/** 数组长度 目前协议32位足够 */
	private static final int ARRAY_LENGTH = 1 << 5;

	/** 符号位 1为正数或0 0为负数 */
	private int sign = 1;
	/** 整数数组 */
	private int[] intArray = new int[ARRAY_LENGTH];
	/** 小数数组 */
	private int[] decimalArray = new int[ARRAY_LENGTH];

	public NumberDigit(Number number) {
		/** 数组初始化 */
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			intArray[i] = 0;
			decimalArray[i] = 0;
		}
		/** 空值判断 */
		if (number == null) {
			return;
		}
		String value = BigDecimal.valueOf(number.doubleValue()).toPlainString();
		int valueLength = value.length();
		/** 符号位 */
		char signChar = value.charAt(0);
		if (signChar == '+') {
			sign = 1;
		}
		if (signChar == '-') {
			sign = 0;
			value = value.substring(1, valueLength);
			valueLength--;
		} else {
			sign = 1;
		}
		/** 小数点的位置 如果存在则是小数 否则按照纯整数计算 */
		int indexOfDot = value.indexOf('.');
		if (indexOfDot != -1) {
			/** 整数部分 */
			for (int i = 0; i < indexOfDot; i++) {
				intArray[i] = value.charAt(indexOfDot - 1 - i) - '0';
			}
			/** 小数部分 */
			for (int i = indexOfDot + 1; i < valueLength; i++) {
				int offset = i - (indexOfDot + 1);
				decimalArray[offset] = value.charAt(i) - '0';
			}
		} else {
			/** 仅包含整数部分 */
			for (int i = 0; i < valueLength; i++) {
				intArray[i] = value.charAt(valueLength - 1 - i) - '0';
			}
		}
	}

	/** 获取符号位 */
	public int getSign() {
		return this.sign;
	}

	/** 获取第i位数字 当i为正时取整数位（从低位到高位） 当i为负时取小数位（从高位到低位） */
	public int getDigit(int i) {
		if (i >= ARRAY_LENGTH || i < -ARRAY_LENGTH) {
			throw new IndexOutOfBoundsException("数组越界 只能取" + ARRAY_LENGTH + "位以内的整数或者小数！当前试图取得第" + (i >= 0 ? ((i + 1) + "位整数") : ((-i) + "位小数")));
		}
		return i >= 0 ? intArray[i] : decimalArray[-(i + 1)];
	}
}