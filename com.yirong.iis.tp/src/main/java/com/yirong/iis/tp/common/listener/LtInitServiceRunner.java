package com.yirong.iis.tp.common.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.sys.eif.SysLocalCacheEif;
import com.yirong.commons.sys.eif.SysParameterEif;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.commons.util.file.PropertiesFileUtil;
import com.yirong.iis.tp.common.constant.LtConstant;
import com.yirong.microservices.client.MsClient;

/**
 * 初始化服务
 * @ClassName: SysInitServiceRunner 
 * @Description: TODO(spring boot 启动后执行) 
 * @author liny
 * @date 2017-5-21 上午11:23:57 
 *
 */
@Component
@Order(value=1)
public class LtInitServiceRunner implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run(String... arg0) throws Exception {
		try {
			//获取配置信息
 
			Map<String, String> map = PropertiesFileUtil.getPropertiesFileToMapFromOutFile(LtConstant.CFG_PATH  + "yr-awaken.properties");
			
			//是否执行依赖模块InitServiceRunner类中初始化方法
			//若该模块为pom依赖模块，则不执行以下初始化，直接跳出
			if(null == map || StringUtil.isNullOrEmpty(map.get("awaken.dependency.do.init")) 
					|| !map.get("awaken.dependency.do.init").contains("tp")){
				return;
			}
			
			logger.info("==============关联服务启动开始===============");
			
			//01 初始化微服务:ip、url
			Map<String, String> clientMap = new HashMap<String, String>();
			clientMap.put("ms.center.url", map.get("ms.center.url"));
			clientMap.put("ms.ip", map.get("ms.ip"));
			MsClient.init(clientMap);
			
			//02 加载日志配置文件
			ErrorPromptInfoUtil.init("errorinfo.properties","lt");
			
			//03 初始化系统参数
			SysParameterEif.init();
 
			//04 加载数字字典到本地缓存
			SysLocalCacheEif.initSysDictionary();
 
			//05 初始化redis 参数配置+ip、端口
			Map<String, String> redisMap = PropertiesFileUtil.getPropertiesFileToMapFromOutFile(LtConstant.CFG_PATH  + "yr-awaken-redis.properties");
			redisMap.put("redis.ip", map.get("redis.ip"));
			redisMap.put("redis.port", map.get("redis.port"));
			RedisCacheEif.init(redisMap);
			
			logger.info("==============关联服务启动结束===============");
		} 
		
		catch (Exception e) {
			logger.error("关联服务启动异常", e);
		}
		
	}
 
}
