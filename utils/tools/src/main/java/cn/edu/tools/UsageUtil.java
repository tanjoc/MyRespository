package cn.edu.tools;

/**
 * 用量相关的工具类
 * 
 * @author Fsx
 * 
 */
public class UsageUtil {
	/**
	 * 将文件大小描述为"4G M K B"的形式
	 * 
	 * @param fileSize
	 *            文件大小
	 * @return 文件大小描述
	 */
	public static String descFileSize(int fileSize) {
		int g = (fileSize >> 30) & 0x3FF;
		int m = (fileSize >> 20) & 0x3FF;
		int k = (fileSize >> 10) & 0x3FF;
		int b = fileSize & 0x3FF;
		StringBuilder stringBuilder = new StringBuilder();
		if (g != 0) {
			stringBuilder.append(g).append("G");
		}
		if (m != 0) {
			stringBuilder.append(m).append("M");
		}
		if (k != 0) {
			stringBuilder.append(k).append("K");
		}
		if (b != 0) {
			stringBuilder.append(b).append("B");
		}
		return stringBuilder.toString();
	}
}