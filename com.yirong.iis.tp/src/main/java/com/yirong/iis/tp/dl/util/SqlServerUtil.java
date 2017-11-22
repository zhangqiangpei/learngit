package com.yirong.iis.tp.dl.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.yirong.commons.sys.eif.SysParameterEif;

public class SqlServerUtil {
	public static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	public static void initPoolConfig(){
        try {
			dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setJdbcUrl(SysParameterEif.getValueByCode("Dl-SqlServer-Url"));
	        dataSource.setUser(SysParameterEif.getValueByCode("Dl-Username"));
	        dataSource.setPassword(SysParameterEif.getValueByCode("Dl-Password"));
	        dataSource.setInitialPoolSize(Integer.parseInt(SysParameterEif.getValueByCode("Dl-InitialPoolSize")));//连接池初始化时创建的连接数
	        dataSource.setMaxPoolSize(Integer.parseInt(SysParameterEif.getValueByCode("Dl-MaxPoolSize")));//连接池中拥有的最大连接数，如果获得新连接时会使连接总数超过这个值则不会再获取新连接，而是等待其他连接释放，所以这个值有可能会设计地很大
	        dataSource.setMinPoolSize(Integer.parseInt(SysParameterEif.getValueByCode("Dl-MinPoolSize")));//连接池保持的最小连接数
	        dataSource.setMaxIdleTime(Integer.parseInt(SysParameterEif.getValueByCode("Dl-MaxIdleTime")));//连接的最大空闲时间，如果超过这个时间，某个数据库连接还没有被使用，则会断开掉这个连接。如果为0，则永远不会断开连接,即回收此连接
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Map<String,Object>> query(String sql){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
	        ResultSet rs = ps.executeQuery();
	        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
	        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数   
	        Map<String, Object> rowData = new HashMap<String, Object>();
	        while (rs.next()) {
	        	rowData = new HashMap<String, Object>(columnCount);   
	        	for (int i = 1; i < columnCount+1; i++) { 
	                rowData.put(md.getColumnName(i), rs.getObject(i));   
	        	}   
	        	list.add(rowData); 
	        }
	        
	        if (rs != null) rs.close();
            if (ps !=null) ps.close();
            if (con !=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
