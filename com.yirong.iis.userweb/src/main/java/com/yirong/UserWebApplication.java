package com.yirong;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.yirong.commons.web.eif.CommonsWebEif;
import com.yirong.iis.userweb.constant.KmUserWebConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.yirong.awaken.core.BaseApplication;
import com.yirong.commons.util.file.PropertiesFileUtil;

@SpringBootApplication
@EnableTransactionManagement
public class UserWebApplication extends BaseApplication{

	public static void main(String[] args) {
		SpringApplication.run(UserWebApplication.class, args);
	}
	
 
	//destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		//获取配置信息awaken.properties
		String configPath =  System.getProperty("user.dir") + File.separator + "config" + File.separator;
		Map<String, String> map = PropertiesFileUtil.getPropertiesFileToMapFromOutFile(configPath + "yr-awaken.properties");

		// 初始化过滤器及文件夹等配置
		Map<String, String> resourceHandlerMap = new HashMap<String, String>();
		KmUserWebConstants.FILE_DOCS_BASE_PATH = map.get("km.user.file.docs");
		resourceHandlerMap.put("/docsFile/**", "file:" + KmUserWebConstants.FILE_DOCS_BASE_PATH);
		CommonsWebEif.initResourceHandlers(resourceHandlerMap);
		List<String> excludePathList = new ArrayList<String>();
		excludePathList.add("/docs_file/*");
		excludePathList.add("/file/onlineGetFile");
		CommonsWebEif.initInterceptors(null, excludePathList);

		//获取配置信息jdbc.properties
		Map<String, String> jdbcMap = PropertiesFileUtil.getPropertiesFileToMapFromOutFile(configPath + "yr-awaken-jdbc.properties");
				
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(map.get("db.url"));
		dataSource.setUsername(map.get("db.username"));// 用户名
		dataSource.setPassword(map.get("db.password"));// 密码
		dataSource.setDriverClassName(map.get("db.driver"));
		
		dataSource.setInitialSize(Integer.parseInt(jdbcMap.get("jdbc.pool.initialSize")));//初始化时建立物理连接的个数
		dataSource.setMaxActive(Integer.parseInt(jdbcMap.get("jdbc.pool.maxActive")));//最大连接池数量
		dataSource.setMinIdle(Integer.parseInt(jdbcMap.get("jdbc.pool.minIdle")));//最小连接池数量
		dataSource.setMaxWait(Integer.parseInt(jdbcMap.get("jdbc.pool.maxWait")));//获取连接时最大等待时间，单位毫秒。
		dataSource.setValidationQuery(jdbcMap.get("jdbc.pool.validationQuery"));//用来检测连接是否有效的sql
		dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
		dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
		dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
		return dataSource;
	}

}
