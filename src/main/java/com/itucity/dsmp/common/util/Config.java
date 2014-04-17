package com.itucity.dsmp.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.itucity.dsmp.common.Constants;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * 读取properties配置文件
 * @author c_zhangya
 */
public class Config {
	private static String filePath = "config.properties";
	private static Logger logger = Logger.getLogger(Config.class.getName());
	private static String uriProp;
	private static String classPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
	
	public Config() {
	}

	private static Properties props = new Properties();
	static {
		try {
			File file = null;
			uriProp = classPath + filePath;
			uriProp = URLDecoder.decode(uriProp, "utf-8");
			if("\\".equals(File.separator)){//windows环境
				file = new File(uriProp);
			}
			if("/".equals(File.separator)){//Linux环境
				file = new File("/"+uriProp);
			}
			FileInputStream fis = new FileInputStream(file);
			props.load(fis);
		} catch (FileNotFoundException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public static String codetoUTF8(String s){
		if(s==null || s.equals("")){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try{
			char c;
			for(int i=0;i<s.length();i++){
				c=s.charAt(i);
				if(c>=0 && c<=255){
					sb.append(c);
				}else{
					byte[] b;
					b=String.valueOf(c).getBytes("utf-8");
					for(int j =0;j<b.length;j++){
						int k=b[j];
						if(k<0){
							k+=256;
							sb.append("%"+Integer.toHexString(k).toUpperCase());
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	/**
	 * 取得变量名的值
	 * @param key 配置文件中的变量名
	 * @return 变量值
	 */
	public static String getValue(String key) {
		return props.getProperty(key);
	}

	/**
	 * 更新配置文件的变量值
	 * @param key	配置文件中的变量名
	 * @param value	变量赋值
	 */
	public static void updateProperties(String key, String value) {
		try {
			props.setProperty(key, value);
			File file = new File(uriProp);
			FileOutputStream fos = new FileOutputStream(file);
			props.store(fos, null);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取项目的root路径
	 * @return 项目的root路径
	 */
	public static String getRootPath(){
		String classPath = Config.class.getClassLoader().getResource("/").getPath();
		String rootPath = "";
		if("\\".equals(File.separator)){//windows环境
			 rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes")).replace("/", "\\");
		}
		if("/".equals(File.separator)){//Linux环境
			rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes")).replace("\\", "/");
		}
		return rootPath;
	}
	
	/**
	 * 中文文件名转码
	 * @param fName
	 * @return
	 */
	public static String encodingFileName(String fName){
		String reString = "";
		try {
			reString = URLEncoder.encode(fName, "UTF-8");
			reString = reString.replace("+", "%20");
			if(reString.length()>150){
				reString = new String(fName.getBytes("GB2312"),"IOS8859-1");
				reString = reString.replace(" ", "%20");
			} 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.info("Don't surpot this encoding.....");
		}
		return reString;
	}
	
	/**
	 * 中文文件名转码
	 * @param fileName
	 * @param agent
	 * @return
	 */
	public static String encodingFileName(String fileName,String agent){
		String result = "";
		try {
			if(null != agent && -1 != agent.indexOf("MSIE")){
				result = URLEncoder.encode(fileName, "UTF8").replaceAll("\\+", " ");
			}  else if (null != agent && -1 != agent.indexOf("Mozilla")){
				result = MimeUtility.encodeText(fileName, "UTF8", "B");
			}
		} catch (UnsupportedEncodingException e) {
			try {
				result = new String(fileName.getBytes("UTF-8"),"IOS8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 时间段终止日期加一天
	 * @param date 日期
	 * @return 增加一天后的日期
	 */
	public static Date addDayOfMonth(Date date){
		if(date!=null){
			Calendar c = Calendar.getInstance(Locale.CHINESE);
			c.setTimeInMillis(date.getTime());
			c.add(Calendar.DAY_OF_MONTH, 1);
			return c.getTime();
		}
		return date;
	}
	
	/**
	 * 转换文件大小
	 * @param fileSize
	 * @return
	 */
	public static String formatFileSize(long fileSize){
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeStr = "";
		if(fileSize<1024){
			fileSizeStr = df.format((double)fileSize)+"B";
		} else if(fileSize<1048576){
			fileSizeStr = df.format((double)fileSize / 1024)+"K";
		} else if(fileSize<1073741824){
			fileSizeStr = df.format((double)fileSize / 1048576)+"M";
		} else {
			fileSizeStr = df.format((double)fileSize / 1073741824)+"G";
		}
		return fileSizeStr;
	}
	
	/***
	 * 获取访问IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(){
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return "";
		}
		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for(int i=0;i<ipAddr.length;i++){
			if(i>0){
				ipAddrStr+=".";
			}
			ipAddrStr+=ipAddr[i] & 0xFF;
		}
		return ipAddrStr.replaceAll("\\pP", "");
	}
	
	//判断表示是否全为英文
  	@SuppressWarnings("unused")
	private static boolean isEnglish(String word) {
  		for (int i = 0; i < word.length(); i++) {
  			if (!(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')
  					&& !(word.charAt(i) >= 'a' && word.charAt(i) <= 'z')) {
  				return false;
  			}
  		}
  		return true;
  	}
  	
	 // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
 
    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * 返回文件路径配置
     * @param type 导入input,导出output
     * @return
     */
    public static String getFilePath(String type) {
    	
    	String pathString = "";
    	if(Constants.FILE_OPTION_TYPE_INPUT.equals(type)){//文件导入
    		pathString = Constants.FILE_UPLOAD_DIR;
    	} else if (Constants.FILE_OPTION_TYPE_OUTPUT.equals(type)) {
    		pathString = Constants.FILE_OUTPUT_DIR;
		}
    	
		if("\\".equals(File.separator)){//windows环境
			 pathString = Config.getRootPath()+"\\"+pathString+"\\"; 
		}
		if("/".equals(File.separator)){//Linux环境
			pathString = '/'+Config.getRootPath()+"/"+pathString+"/"; 
		}
		try {
			return URLDecoder.decode(pathString,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return null;
	}
    
    
    /**
     * 解决Spring MVC GET方法获取中文乱码问题
     * @param content
     * @return
     */
    public static String codeGetMethodChinese(String content){
    	byte bytes[];
		try {
			bytes = content.getBytes("ISO-8859-1");//以"ISO-8859-1"方式解析content字符串
			content = new String(bytes, "UTF-8"); //再用"utf-8"格式表示region
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
        return content;
    }
}