package com.yirong.iis.tp.tslt.trkd.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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
 * 重大事件获取  定时任务
 * @author lijp
 *
 */
@Configuration
@EnableScheduling
public class SignificantDevelopmentsJob {
	
	private static final ExecutorService threadPool = Executors
			.newFixedThreadPool(10);
	
	private Logger logger = LoggerFactory.getLogger(FundamentalsJob.class);
	
	@Resource(name="ltGetSignificantDevelopmentsServiceImpl")
	private LtHttpService ltHttpService;
	
	@Autowired
	private LtTrkdCompanyService ltTrkdCompanyService;
	
	/**
	 * 每隔3小时获取一次重大事件数据
	 * @throws InterruptedException
	 */
	@Scheduled(cron = "0 0 0/3  * * ?") 
	public void doSignificantDevelopmentsJob() throws InterruptedException {
		logger.info("***************doSignificantDevelopmentsJob定时任务开始**********");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		final String endTime = sdf.format(new Date());
		
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.HOUR, -3);
		final String startTime = sdf.format(startDate.getTime());
		
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
		
		//List<LtTrkdCompany> companyList = ltTrkdCompanyService.getAll(Order.basicOrder());
		for(final LtTrkdCompany company : companyList){
			
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					 Map<String, Object> param = new HashMap<String, Object>();
					  param.put("companyId", company.getCompanyId());
					  param.put("StartDate", startTime);
					  param.put("EndDate", endTime);
					  try {
						  ltHttpService.request(param);
					  } catch (Exception e) {
						  e.printStackTrace();
					  }
					
				}
			}); 
			 
		}
		logger.info("***************doSignificantDevelopmentsJob定时任务结束**********");
	}
	
}
