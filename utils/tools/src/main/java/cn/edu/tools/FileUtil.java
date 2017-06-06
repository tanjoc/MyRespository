package cn.edu.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件读写工具类
 * 
 * @author Fsx
 * 
 */
public class FileUtil {
	/**
	 * 将文件内容读入字符串
	 * 
	 * @param file
	 *            文件
	 * @return 文件内容字符串
	 * @throws Exception
	 *             文件操作异常
	 */
	public static String readFromFile(File file) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
				file))) {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line).append("\r\n");
			}
			/** 删除末尾的\r\n */
			if (stringBuilder.length() > 0) {
				stringBuilder.delete(stringBuilder.length() - 2,
						stringBuilder.length());
			}
			return stringBuilder.toString();
		}
	}

	/**
	 * 将文件内容读入字符串
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件内容字符串
	 * @throws Exception
	 *             文件操作异常
	 */
	public static String readFromFile(String fileName) throws Exception {
		return readFromFile(new File(fileName));
	}

	/**
	 * 将二进制文件内容读入字符串
	 * 
	 * @param file
	 *            文件
	 * @return 文件内容
	 * @throws Exception
	 *             文件操作异常
	 */
	public static String readFromHexFile(File file) throws Exception {
		InputStream inputStream = new FileInputStream(file);
		char[] result = new char[(int) file.length()];
		int index = 0;
		int read;
		while ((read = inputStream.read()) != -1) {
			result[index++] = (char) read;
		}
		try {
			inputStream.close();
		} catch (Exception e) {
		}
		return new String(result);
	}

	/**
	 * 将二进制文件内容读入字符串
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件内容
	 * @throws Exception
	 *             文件操作异常
	 */
	public static String readFromHexFile(String fileName) throws Exception {
		return readFromHexFile(new File(fileName));
	}

	/**
	 * 将字符串写入文件
	 * 
	 * @param file
	 *            文件
	 * @param outputString
	 *            字符串
	 * @throws Exception
	 *             文件操作异常
	 */
	public static void writeToFile(File file, String outputString)
			throws Exception {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		bufferedWriter.write(outputString);
		bufferedWriter.flush();
		try {
			bufferedWriter.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 将字符串写入文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param outputString
	 *            字符串
	 * @throws Exception
	 *             文件操作异常
	 */
	public static void writeToFile(String fileName, String outputString)
			throws Exception {
		writeToFile(new File(fileName), outputString);
	}

	/**
	 * 将字符串写入二进制文件
	 * 
	 * @param file
	 *            文件
	 * @param outputString
	 *            字符串
	 * @throws Exception
	 *             文件操作异常
	 */
	public static void writeToHexFile(File file, String outputString)
			throws Exception {
		OutputStream outputStream = new FileOutputStream(file);
		outputStream.write(outputString.getBytes("ISO8859-1"));
		outputStream.flush();
		try {
			outputStream.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 将字符串写入二进制文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param outputString
	 *            字符串
	 * @throws Exception
	 *             文件操作异常
	 */
	public static void writeToHexFile(String fileName, String outputString)
			throws Exception {
		writeToHexFile(new File(fileName), outputString);
	}

	/**
	 * 文件复制
	 * 
	 * @param sourceFile
	 *            源文件
	 * @param targetFile
	 *            目标文件
	 * @throws Exception
	 *             文件操作异常
	 */
	public static void fileCopy(File sourceFile, File targetFile)
			throws Exception {
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream(
					sourceFile));
			bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(targetFile));
			byte[] byteArray = new byte[1 << 12];
			int alreadyRead = 0;
			while ((alreadyRead = bufferedInputStream.read(byteArray)) != -1) {
				bufferedOutputStream.write(byteArray, 0, alreadyRead);
			}
			bufferedOutputStream.flush();
		} finally {
			/** 关闭流 */
			try {
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
				if (bufferedOutputStream != null) {
					bufferedOutputStream.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 文件复制
	 * 
	 * @param sourceFileName
	 *            源文件名称
	 * @param targetFileName
	 *            目标文件名称
	 * @throws Exception
	 *             文件操作异常
	 */
	public static void fileCopy(String sourceFileName, String targetFileName)
			throws Exception {
		fileCopy(new File(sourceFileName), new File(targetFileName));
	}

	/**
	 * 文件复制
	 * 
	 * @param sourceFile
	 *            源文件
	 * @param targetFileName
	 *            目标文件名称
	 * @throws Exception
	 *             文件操作异常
	 */
	public static void fileCopy(File sourceFile, String targetFileName)
			throws Exception {
		fileCopy(sourceFile, new File(targetFileName));
	}

	/**
	 * 文件复制
	 * 
	 * @param sourceFileName
	 *            源文件名称
	 * @param targetFile
	 *            目标文件
	 * @throws Exception
	 *             文件操作异常
	 */
	public static void fileCopy(String sourceFileName, File targetFile)
			throws Exception {
		fileCopy(new File(sourceFileName), targetFile);
	}
}