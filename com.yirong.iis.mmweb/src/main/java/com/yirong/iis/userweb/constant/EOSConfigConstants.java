package com.yirong.iis.userweb.constant;

import com.yirong.commons.sys.eif.SysParameterEif;

public class EOSConfigConstants {
 
	//请求地址
	public static String eosUrl = SysParameterEif.getValueByCode("iis.eos.url");
	
	//zookeeper地址
	public static String eosAccount =SysParameterEif.getValueByCode("iis.eos.account");
	
	//kafka集群地址及端口
	public static String eosSecret = SysParameterEif.getValueByCode("iis.eos.secret");
	
	//投标公告抽取服务地址
	public static String eosBucket =SysParameterEif.getValueByCode("iis.eos.bucket");	
	
	
}
