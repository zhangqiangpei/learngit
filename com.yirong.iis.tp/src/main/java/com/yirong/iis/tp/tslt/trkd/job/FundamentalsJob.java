package com.yirong.iis.tp.tslt.trkd.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.commons.util.order.Order;
import com.yirong.iis.tp.common.entity.LtTrkdCompany;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;
import com.yirong.iis.tp.tslt.trkd.service.LtTrkdCompanyService;

/**
 * 基本面 trkd接口  定时任务
 * @author lijp
 *
 */
@Configuration
@EnableScheduling
public class FundamentalsJob {
	
	private Logger logger = LoggerFactory.getLogger(FundamentalsJob.class);
	
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);
	
	@Resource(name="ltGetFinancialStatementsReportsServiceImpl")
	private LtHttpService financialService;
	
	@Resource(name="ltGetSnapshotReportsServiceImpl")
	private LtHttpService snapshotService;
	
	@Autowired
	private LtTrkdCompanyService ltTrkdCompanyService;
	
	/**
	 * 每天早上8点半 同步基本面数据
	 * @throws InterruptedException
	 */
	@Scheduled(cron = "0 30 8 * * ?")
	public void doFundamentalsJob() throws InterruptedException {
		logger.info("***************FundamentalsJob定时任务开始**********");
		//List<LtTrkdCompany> companyList = ltTrkdCompanyService.getAll(Order.basicOrder());
		
		long count = ltTrkdCompanyService.entityCount();
		List<LtTrkdCompany> companyList = new ArrayList<LtTrkdCompany>();
		for(int i=0;i < Math.ceil(count/500.0);i++){
			PageEntiry page = new PageEntiry();
			page.setCurrentPage(i);
			page.setPageSize(500);
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.basicOrder());
			page.setOrders(orders);
			@SuppressWarnings("unchecked")
			List<LtTrkdCompany> list = ltTrkdCompanyService.findPage(page).getData();
			companyList.addAll(list);
		}
		
		for(final LtTrkdCompany company : companyList){
			
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					Map<String, Object> param = new HashMap<String, Object>();
				    param.put("companyId", company.getCompanyId());
				    param.put("companyIdType", "RIC");
				    try {
				    	snapshotService.request(param);
					} catch (Exception e) {
						e.printStackTrace();
					}
				    
					try {
						financialService.request(param);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}); 
			
		}
		logger.info("***************FundamentalsJob定时任务结束**********");
	}
	
}
