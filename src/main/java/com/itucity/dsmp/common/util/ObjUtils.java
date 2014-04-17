package com.itucity.dsmp.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Object工具类
 */
public class ObjUtils {
	
	public static String obj2Str(Object obj) {
		return obj==null?"":obj+"";
	}
	
	public static String obj2Str(Object obj, String def) {
		return obj==null?def:obj+"";
	}
	
	public static long obj2Long(Object obj) {
        return obj==null?0:Long.valueOf(obj+"");
    }
    
    public static int obj2Int(Object obj) {
        return obj==null?0:Integer.valueOf(obj+"");
    }
    
    public static Date obj2Date(Object obj) {
        return (Date)obj;
    }
    
	public static String int2Str(Integer I, String def) {
		return I==null?def:I+"";
	}
	
	public static String long2Str(Long L, String def) {
        return L==null?def:L+"";
    }
	
	public static String list2Str(List<String> list) {
	    StringBuffer sb=new StringBuffer();
	    for(String e: list){
	        if(sb.length()==0) sb.append(e);
	        else sb.append(","+e);
	    }
        return sb.toString();
    }
	
	public static boolean strIsEmpty(String s) {
		return s==null || "".equals(s);
	}
	
	public static boolean strIsNotEmpty(String s) {
		return !strIsEmpty(s);
	}
	
	public static String nvl(String str, String def){
        if(str==null) return def;
        else return str;
    }
	
	public static String substring(String str,String beginStr,String endStr){
        int beginIndex=str.indexOf(beginStr)+beginStr.length();
        int endIndex=str.indexOf(endStr);
        if(beginIndex<0 || endIndex<0 || endIndex<beginIndex){
            return "";
        }
        return str.substring(beginIndex, endIndex);
    }

    public static String lastSubstring(String str,String beginStr,String endStr){
        int beginIndex=str.lastIndexOf(beginStr)+beginStr.length();
        int endIndex=str.lastIndexOf(endStr);
        if(beginIndex<0 || endIndex<0 || endIndex<beginIndex){
            return "";
        }
        return str.substring(beginIndex, endIndex);
    }
    
    /** 格式化XML, 去掉多余的空格、回车 */
    public static String formateXml(String xml){
        String regex="(?<=>)\\s+(?=<)";
        return xml.replaceAll(regex, "");
    }
    
	/** 正则表达式查找类似"key=value,key=value"的数据	 */
	public static String regexFind(String s, String key, String def){
        String regex="(?<="+key+"=)\\w*";
        Pattern p = Pattern.compile(regex); 
        Matcher m = p.matcher(s);
        if(m.find()) { 
            return m.group();
        }else return def;
    }
	
	/** 克隆Serializable
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cloneSerializable(T src) throws RuntimeException {
		ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		T dest = null;
		try {
			out = new ObjectOutputStream(memoryBuffer);
			out.writeObject(src);
			out.flush();
			in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
			dest =  (T) in.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (out!=null){
				try {out.close();out = null;} catch (Exception e) {}
			}
			if (in!=null){
				try {in.close();in = null;} catch (Exception e) {}
			}
		}
		return dest;
	}
	
	public static Date dataAdd(Date date, long millisecond) {
		if(date==null) return null;
		return new Date(date.getTime()+millisecond);
	}
	
	public static String getDebugMsg(Throwable e) {
		if(e==null) return null;
		StringWriter write=new StringWriter();
		PrintWriter out=new PrintWriter(write);
		e.printStackTrace(out);
		return write.toString();
	}

	/**
	 * 排序字段名替换成序号
	 * @param src
	 * @param replacExpression
	 * @return
	 */
	public static String sortFieldNameToIndex(String src, String replacExpression) {
		if(strIsEmpty(src)) return null;
		List<String> replacList=Arrays.asList(replacExpression.toUpperCase().split(","));
		boolean isCopy=false;
		StringBuffer desc=new StringBuffer();
		StringBuffer v=new StringBuffer();

		for(char c: (src+" ").toUpperCase().toCharArray()) {
			if(c==44) {// 逗号
				if(v.length()>0) {
					int idx=replacList.indexOf(v.toString());
					if(idx==-1) desc.append(v.toString());
					else desc.append(idx+1);
					desc.append(" ASC");
					v=new StringBuffer();
				}
				isCopy=false;
				desc.append(c);
			} else if(isCopy) {
				desc.append(c);
			} else if(c==9||c==32) {//tab符, 空格
				if(v.length()>0) {
					int idx=replacList.indexOf(v.toString());
					if(idx==-1) desc.append(v.toString());
					else desc.append(idx+1);
					v=new StringBuffer();
					isCopy=true;
					desc.append(c);
				}
			} else {
				v.append(c);
			}
		}
		return desc.toString();
	}
	
}
