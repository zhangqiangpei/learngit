package com.yirong.iis.tp.trkd.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.trkd.service.ILtTrkdInterfaceService;

/**
 * 加载数据,不管是否为单独微服务都会执行
 * @ClassName: SysLoadDataRunner 
 * @Description: TODO(spring boot 启动后执行) 
 * @author liny
 * @date 2017-5-21 上午11:24:22 
 *
 */
@Component
@Order(value=2)
public class LtLoadDataRunner implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ILtTrkdInterfaceService ltTrkdInterfaceService;
	
	@Override
	public void run(String... arg0) throws Exception {
		logger.info("服务启动执行,执行加载缓存操作 .");
		ltTrkdInterfaceService.initUrlCache();
		logger.info("执行加载缓存操作结束 .");
	}

}
