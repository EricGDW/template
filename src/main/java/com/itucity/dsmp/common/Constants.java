package com.itucity.dsmp.common;

import com.itucity.dsmp.common.util.Config;

/**
 * 系统级静态常量.
 * 可通过message.properties初始化,同时保持常量static & final的特征.
 * @see ConfigurableConstants
 */
public class Constants extends ConfigurableConstants {
	
    //静态初始化读入message.properties中的设置
    static {
        init("config.properties");
    }
    
    /**
     * 从message.properties中读取constant.message_bundle_key的值，如果配置文件不存在或配置文件中不存在该值时，默认取值"messages"
     */
    public final static int DEFAULT_PAGE_SIZE = Integer.parseInt(getProperty("constant.default_page_size", String.valueOf(25)));
    
    public final static String  SYS001 = getProperty("SYS001", "参数异常");
    
    public final static String  SYS002 = getProperty("SYS002", "数据库连接异常");
    
    public final static String  SYS003 = getProperty("SYS003", "没有结果集");
    
    /**
     * 文件上传的父级路径
     * @author ya.zhang
     */
    public final static String  FILE_UPLOAD_DIR =Config.getValue("file_upload_dir");
    
    /**
     * 从数据中导出文件的父级路径
     * @author ya.zhang
     */
    public final static String  FILE_OUTPUT_DIR =Config.getValue("file_output_dir");
    
    public final static String  FILE_OPTION_TYPE_INPUT ="input";
    
    public final static String  FILE_OPTION_TYPE_OUTPUT ="output";

    /**
     * 状态字段约定
     */
    public final static String STATUS_KEY = "status";
    
    public final static String STATUS_FAILED = "FAILED";
    
    public final static String STATUS_SUCCESS = "SUCCESS";
    
    /**
     * session 中存储token的key常量名
     */
    public static final String APP_KEY = "appkey";
    
    public static final String SIGN_KEY = "sign";
    
    public final static String TOKEN_KEY = "token";
    
    public final static String USERNAME_KEY = "username";
    
    public static final String TIMESTAMP_KEY = "timestamp";
    
    
    /**
     * MongoDB 数据保存的字段约定
     */
    public final static String ID = "id";
    
    public final static String TITLE = "title";
    
    public final static String CONTENT = "content";
    
    public final static String AUTHOR = "author";
    
    public final static String URL = "url";
    
    public final static String IMGURLS = "imgurls";
    
    public final static String PUB_TIME = "pubTime";
    
    public final static String TAGS = "tags";
    
    /**
     * 错误码
     */
    public final static String ERROR_INVALID_PARAMETER = "1";

	public static final String ERROR_NOT_FOUND = "2";

	public static final String ERROR_SERVER = "3";
	
	public static String getError(String errorCode){
		String errmsg = "";
		return errmsg;
	}
}
