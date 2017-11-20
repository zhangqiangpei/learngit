package com.yirong.iis.tp.trkd.constant;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LtConstant {
	
	/**
	 * 路透token
	 */
	public static String ltToken = null;
	
	/**
	 * 路透token过期时间
	 */
	public static Date expiration = null;
	
	// 配置文件路径
	public static final String CFG_PATH = System.getProperty("user.dir") + File.separator + "config" + File.separator;
	
	/**
	 * trkd url本地缓存
	 */
	public static Map<String,String> trkdMap =new HashMap<String,String>();
	
}
