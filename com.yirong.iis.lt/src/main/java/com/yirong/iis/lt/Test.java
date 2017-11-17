package com.yirong.iis.lt;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yirong.commons.util.server.HttpRequestUtils;

public class Test {

	public static void main(String[] args) {
		//sqlserver 连接测试
		/*SqlServerUtil.initPoolConfig();
		List<Map<String,Object>> list = SqlServerUtil.query("select * from CompanyIndustrySector");
		System.out.print(list.size());*/
		//获取token令牌 https测试 
		/*JSONObject content = new JSONObject();
		JSONObject CreateServiceToken_Request_1 = new JSONObject();
		CreateServiceToken_Request_1.put("ApplicationID", "trkddemoappcs");
		CreateServiceToken_Request_1.put("Username", "trkd-demo-cs@thomsonreuters.com");
		CreateServiceToken_Request_1.put("Password", "o5o1t27pl");
		content.put("CreateServiceToken_Request_1", CreateServiceToken_Request_1);
		String result = HttpRequestUtils.httpsRequest("https://api.trkd.thomsonreuters.com/api/TokenManagement/TokenManagement.svc/REST/Anonymous/TokenManagement_1/CreateServiceToken_1"
				, content.toString());
		System.out.print(result);*/
		Map<String,String> headMap = new HashMap<String,String>();
		headMap.put("X-Trkd-Auth-Token", "B101CA44175902A459DBC8874C6A36086A84186A681E35B6465938366C2877BE76752F04743A3A4DCFCBFBC8B7049EA653C9C272BAA136000962B0ECE6C7F05DFE19126F4103C4258EF10D68E6483FFEB9C2601E2AD3B07A4581041BD5D0CDBE");
		headMap.put("X-Trkd-Auth-ApplicationID", "trkddemoappcs");
		JSONObject content = new JSONObject();
		JSONObject GetOfficersAndDirectors_Request_1 = new JSONObject();
		GetOfficersAndDirectors_Request_1.put("companyId", "IBM.N");
		GetOfficersAndDirectors_Request_1.put("companyIdType", "RIC");
		GetOfficersAndDirectors_Request_1.put("ShowReferenceInformation", false);
		content.put("GetOfficersAndDirectors_Request_1", GetOfficersAndDirectors_Request_1);
		String result = HttpRequestUtils.httpPostHead("http://api.trkd.thomsonreuters.com/api/Fundamentals/Fundamentals.svc/REST/Fundamentals_1/GetOfficersAndDirectors_1", 
				content,
				headMap);
		System.out.print(result);
	}

}
