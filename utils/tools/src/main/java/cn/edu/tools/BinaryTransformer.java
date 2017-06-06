package cn.edu.tools;

/** 二进制字符串和字节数组的转换工具类 */
public class BinaryTransformer {
	/** 二进制字符串转换为字节数组 */
	public static byte[] string2ByteArray(String inputString) {
		if (StringUtil.isEmpty(inputString)) {
			return new byte[0];
		}
		inputString = inputString.trim();
		int length = inputString.length();
		byte[] byteArray = new byte[length];
		for (int i = 0; i < length; i++) {
			char c = inputString.charAt(i);
			if (c >= '0' && c <= '9') {
				byteArray[i] = (byte) (c - '0');
			} else if (c >= 'A' && c <= 'F') {
				byteArray[i] = (byte) (c - 'A' + 10);
			} else if (c >= 'a' && c <= 'f') {
				/** 兼容大小写 */
				byteArray[i] = (byte) (c - 'a' + 10);
			} else {
				throw new IllegalArgumentException("第" + (i + 1) + "个字符'" + c
						+ "'无法转换为字节！");
			}
		}
		return byteArray;
	}

	/** 字节数组转换为二进制字符串 */
	public static String byteArray2String(byte[] byteArray) {
		if (byteArray == null) {
			return null;
		}
		int length = byteArray.length;
		char[] charArray = new char[length];
		for (int i = 0; i < length; i++) {
			byte b = byteArray[i];
			charArray[i] = (b <= (byte) 9) ? (char) ('0' + b)
					: (char) ('A' + b - 10);
		}
		return String.valueOf(charArray);
	}

	/** 整数转换为二进制数组（1个字节） */
	public static byte[] int8ToByteArray(int int8) {
		byte[] byteArray = new byte[1 * 2];
		byteArray[0] = (byte) ((int8 >> 4) & 0xF);
		byteArray[1] = (byte) (int8 & 0xF);
		return byteArray;
	}

	/** 整数转换为二进制数组（2个字节） */
	public static byte[] int16ToByteArray(int int16) {
		byte[] byteArray = new byte[2 * 2];
		byteArray[0] = (byte) ((int16 >> 4) & 0xF);
		byteArray[1] = (byte) (int16 & 0xF);
		byteArray[2] = (byte) ((int16 >> 12) & 0xF);
		byteArray[3] = (byte) ((int16 >> 8) & 0xF);
		return byteArray;
	}

	/** 整数转换为二进制数组（3个字节） */
	public static byte[] int24ToByteArray(int int24) {
		byte[] byteArray = new byte[3 * 2];
		byteArray[0] = (byte) ((int24 >> 4) & 0xF);
		byteArray[1] = (byte) (int24 & 0xF);
		byteArray[2] = (byte) ((int24 >> 12) & 0xF);
		byteArray[3] = (byte) ((int24 >> 8) & 0xF);
		byteArray[4] = (byte) ((int24 >> 20) & 0xF);
		byteArray[5] = (byte) ((int24 >> 16) & 0xF);
		return byteArray;
	}

	/** 将十进制数直接转换成十六进制数组（每位直接对应相同的十六进制数字） */
	public static byte[] longToDirectByteArray(long l, int length) {
		if (length < 0) {
			throw new IllegalArgumentException("数组长度不能是负数！");
		}
		NumberDigit numberDigit = new NumberDigit(l);
		byte[] byteArray = new byte[length];
		for (int i = 0; i < length; i++) {
			byteArray[length - 1 - i] = (byte) (numberDigit.getDigit(i));
		}
		return byteArray;
	}

	/** 二进制数组转换为十六进制数组 */
	public static byte[] byteArray2HexArray(byte[] byteArray) {
		if (byteArray == null) {
			return null;
		}
		int byteLength = byteArray.length;
		if (byteLength % 2 != 0) {
			throw new IllegalArgumentException("二进制数组的长度需要是偶数！");
		}
		int hexLength = byteLength / 2;
		byte[] hexArray = new byte[hexLength];
		for (int i = 0; i < hexLength; i++) {
			hexArray[i] = (byte) ((byteArray[i * 2] << 4) + byteArray[i * 2 + 1]);
		}
		return hexArray;
	}

	/** 十六进制数组转换为二进制数组 */
	public static byte[] hexArray2ByteArray(int[] hexArray) {
		if (hexArray == null) {
			return null;
		}
		int length = hexArray.length;
		byte[] byteArray = new byte[length * 2];
		for (int i = 0; i < length; i++) {
			byteArray[i * 2] = (byte) ((hexArray[i] >> 4) & 0xF);
			byteArray[i * 2 + 1] = (byte) (hexArray[i] & 0xF);
		}
		return byteArray;
	}

	/** 十六进制数组转换为二进制数组 */
	public static byte[] hexArray2ByteArray(byte[] hexArray) {
		if (hexArray == null) {
			return null;
		}
		int length = hexArray.length;
		byte[] byteArray = new byte[length * 2];
		for (int i = 0; i < length; i++) {
			byteArray[i * 2] = (byte) ((hexArray[i] >> 4) & 0xF);
			byteArray[i * 2 + 1] = (byte) (hexArray[i] & 0xF);
		}
		return byteArray;
	}
}