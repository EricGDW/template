package com.itucity.dsmp.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonUtil implements JsonValueProcessor {

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	private DateFormat dateFormat;

	public JsonUtil(String datePattern) {
		try {
			dateFormat = new SimpleDateFormat(datePattern);
		} catch (Exception ex) {
			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		}
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig arg1) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		return process(arg1);
	}

	private Object process(Object value) {
		if (value == null) {
			return "";
		}
		return dateFormat.format((Date) value);
	}

	public static String map2JsonStr(Map<?, ?> map) {
		JsonConfig jsonConfig = new JsonConfig();
		JsonUtil beanProcessor = new JsonUtil(DEFAULT_DATE_PATTERN);
		jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
		JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
		return jsonObject.toString();
	}

	public static JSONObject obj2JsonObj(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		JsonUtil beanProcessor = new JsonUtil(DEFAULT_DATE_PATTERN);
		jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
		JSONObject jsonObject = JSONObject.fromObject(obj, jsonConfig);
		return jsonObject;
	}

	public static JSONArray obj2JsonArr(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		JsonUtil beanProcessor = new JsonUtil(DEFAULT_DATE_PATTERN);
		jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
		JSONArray jsonArray = JSONArray.fromObject(obj, jsonConfig);
		return jsonArray;
	}

	public static JSONArray object2JsonArray(Object object) {
		if(object == null){
			return new JSONArray();
		}
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray;
	}

	public static String object2JsonStr(Object object) {
		if(object == null){
			return "";
		}
		String s = JSONArray.fromObject(object).toString();
		return s;
	}
}
