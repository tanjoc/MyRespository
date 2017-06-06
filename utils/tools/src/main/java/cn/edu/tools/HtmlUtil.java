package cn.edu.tools;

/** HTML代码转义相关的工具类 */
public class HtmlUtil {
	/**
	 * 将原字符串替换成HTML转义后的字符串
	 * 
	 * @param text
	 *            原字符串
	 * @return HTML转义后的字符串
	 */
	public static String escape(String text) {
		if (text == null) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : text.toCharArray()) {
			switch (c) {
			case '<':
				stringBuilder.append("&lt;");
				break;
			case '>':
				stringBuilder.append("&gt;");
				break;
			case '&':
				stringBuilder.append("&amp;");
				break;
			case '"':
				stringBuilder.append("&quot;");
				break;
			case 10:
			case 13:
				break;
			default:
				stringBuilder.append(c);
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 将HTML转义后的字符串替换成原字符串
	 * 
	 * @param html
	 *            HTML转义后的字符串
	 * @return 原字符串
	 */
	public static String unescape(String html) {
		if (html == null) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		char[] charArray = html.toCharArray();
		int length = charArray.length;
		for (int i = 0; i < length; i++) {
			char c = charArray[i];
			if (c != '&') {
				stringBuilder.append(c);
			} else {
				int j = html.indexOf(';', i);
				char charUnescape = unescape2Char(html.substring(i, j + 1));
				if (charUnescape != 0) {
					stringBuilder.append(charUnescape);
				}
				i = j;
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 将形如&lt;的HTML字符串转义成<等字符
	 * 
	 * @param html
	 *            HTML字符串
	 * @return 转义后的字符
	 */
	private static char unescape2Char(String html) {
		if (html == null) {
			return 0;
		}
		if (html.equals("&lt;")) {
			return '<';
		}
		if (html.equals("&gt;")) {
			return '>';
		}
		if (html.equals("&amp;")) {
			return '&';
		}
		if (html.equals("&quot;")) {
			return '"';
		}
		return 0;
	}
}