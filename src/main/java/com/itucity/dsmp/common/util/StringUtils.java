package com.itucity.dsmp.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>
 * 字符串工具类
 * </p>
 * <p>
 * <b>最后修改时间：</b><br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2010-12-17下午02:37:17
 * </p>
 * 
 * @version 1.0
 */
public class StringUtils {

	/**
	 * <p>
	 * 判断是否为空串（或NULL）
	 * </p>
	 * <p>
	 * <b>最后修改时间：</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2010-12-17下午03:51:08
	 * </p>
	 * 
	 * @param s
	 *            目标字符串
	 * @return True是空串（或NULL），false不是空串（或NULL）
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s);
	}

	/**
	 * <p>
	 * 判断是否不为空串（或NULL）
	 * </p>
	 * <p>
	 * <b>最后修改时间：</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2010-12-17下午03:51:08
	 * </p>
	 * 
	 * @param s
	 *            目标字符串
	 * @return True不是空串（或NULL），false是空串（或NULL）
	 */
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	public static boolean equals(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		}

		return s1.equals(s2);
	}

	public static String replace(String sourceStr, int beginIndex,
			int endIndex, String targetStr) {
		String resultStr = sourceStr.substring(0, beginIndex);
		resultStr += targetStr;
		resultStr += sourceStr.substring(endIndex);
		return resultStr;
	}

	public static String substring(String str, String beginStr, String endStr) {
		int beginIndex = str.indexOf(beginStr) + beginStr.length();
		int endIndex = str.indexOf(endStr);
		if (beginIndex < 0 || endIndex < 0 || endIndex < beginIndex) {
			return "";
		}
		return str.substring(beginIndex, endIndex);
	}

	public static String generateString(String expressionString,
			HashMap<String, String> variableMap) {
		StringBuilder resultData = new StringBuilder();
		StringBuilder variableName = new StringBuilder();
		boolean isVariable = false;
		for (char dataChar : expressionString.toCharArray()) {
			if (dataChar == '}') {
				isVariable = false;
				resultData.append(variableMap.get(variableName.toString()
						.toUpperCase()));
			}
			if (isVariable) {
				variableName.append(dataChar);
			} else if (dataChar != '{' && dataChar != '}') {
				resultData.append(dataChar);
			}
			if (dataChar == '{') {
				isVariable = true;
				variableName = new StringBuilder();
			}
		}
		return resultData.toString();
	}

	public static byte[] decoderBASE64(String base64) throws IOException {
		byte[] b = null;
		b = new BASE64Decoder().decodeBuffer(base64);
		return b;
	}

	public static String encoderBASE64(byte[] s) {
		if (s == null)
			return null;
		return (new BASE64Encoder()).encode(s).replace("\r\n", "");
	}

	/*
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF";

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encoderHex(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decoderHex(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2) {
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		}
		return new String(baos.toByteArray());
	}

	public static String encoderUnicode(String dataString) {
		char[] utfBytes = dataString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + hexB;
		}
		return unicodeBytes;
	}

	// public static String decodeUnicode( final String dataStr ) {
	// int start = 0;
	// int end = 0;
	// final StringBuffer buffer = new StringBuffer();
	// while( start > -1 ) {
	// end = dataStr.indexOf( "\\\\u", start + 2 );
	// String charStr = "";
	// if( end == -1 ) {
	// charStr = dataStr.substring( start + 2, dataStr.length() );
	// } else {
	// charStr = dataStr.substring( start + 2, end);
	// }
	// char letter = (char) Integer.parseInt( charStr, 16 ); // 16进制parse整形字符串。
	// buffer.append( new Character( letter ).toString() );
	// start = end;
	// }
	// return buffer.toString();
	// }

	public static String numberToChinese(String numberStr) {
		StringBuilder resultStr = new StringBuilder();
		HashMap<String, String> numberChineseMap = new HashMap<String, String>();
		numberChineseMap.put("0", "零");
		numberChineseMap.put("1", "一");
		numberChineseMap.put("2", "二");
		numberChineseMap.put("3", "三");
		numberChineseMap.put("4", "四");
		numberChineseMap.put("5", "五");
		numberChineseMap.put("6", "六");
		numberChineseMap.put("7", "七");
		numberChineseMap.put("8", "八");
		numberChineseMap.put("9", "九");
		char[] numberCharArray = numberStr.toCharArray();
		for (char numberChar : numberCharArray) {
			String numberOneStr = String.valueOf(numberChar);
			if (numberChineseMap.get(numberOneStr) != null) {
				numberOneStr = numberChineseMap.get(numberOneStr);
			}
			resultStr.append(numberOneStr);
		}
		return resultStr.toString();
	}

	@SuppressWarnings("unused")
	public static String getxmlContent(String str, String begintitle,
			String endtitle) {

		if (!org.springframework.util.StringUtils.hasText(str))
			return str;

		String beginstr = str.substring(0,
				str.indexOf(begintitle) + begintitle.length());

		String endstr = str.substring(str.indexOf(endtitle));

		String content = str.substring(
				str.indexOf(begintitle) + begintitle.length(),
				str.indexOf(endtitle));

		return content;
	}

	/**
	 *  将多个对象用separator组合起来,可用于sql拼接
	 *  例如传入(stingList,",")，则结果为  str1,str2,str3.....
	 * @author Jackey
	 * @date 2014-1-13
	 * @param iterable 只要是实现了iterable接口的对象都可以，例如Collection集合
	 * @param separator 分隔符
	 * @return
	 */
	public static String join(Iterable<?> iterable, String separator) {
		if (iterable == null) {
			return null;
		}
		return join(iterable.iterator(), separator);
	}

	public static String join(Iterator<?> iterator, String separator) {

		if (iterator == null) {
			return null;
		}
		if (!iterator.hasNext()) {
			return "";
		}
		Object first = iterator.next();
		if (!iterator.hasNext()) {
			return first == null ? "" : first.toString();
		}

		// two or more elements
		StringBuilder buf = new StringBuilder(128); 
		if (first != null) {
			buf.append(first);
		}

		while (iterator.hasNext()) {
			if (separator != null) {
				buf.append(separator);
			}
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
		}
		return buf.toString();
	}

	public static void main(String[] arge) {
		// HashMap<String,Object> a=new HashMap<String,Object>();
		// OrderBuyVO orderBuy=new OrderBuyVO();
		// orderBuy.setOrderID("1234");
		// a.put("1", orderBuy);
		// OrderBuyVO o=(OrderBuyVO)a.get("1");
		// o.setOrderID("4321");
		// System.out.println(orderBuy.getOrderID());

		// String expressionString="一一一{USER_NAME}二二二{phone_num}三三三";
		// HashMap<String,String> variableMap=new HashMap<String,String>();
		// variableMap.put("USER_NAME","刘备");
		// variableMap.put("PHONE_NUM","13312341234");
		// System.out.println(StringUtils.generateString(expressionString,
		// variableMap));
		//
		// StringBuilder s=new StringBuilder("1234567890");
		// System.out.println(s.insert(0, "AAA").toString());

		// System.out.println("联通电话支付12");
		// System.out.println(StringUtils.encoderUnicode("联通电话支付12"));
		// System.out.println("901a805475358bdd652f4ed8");
		// System.out.println(StringUtils.decodeUnicode("901a805475358bdd652f4ed8"));
		System.out.println(StringUtils.numberToChinese("阿阿150749地6965天7在"));
	}
}
