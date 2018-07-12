package al.weapon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONObject;

import DB.action.MySQLAction;

public class APIDetailFetcherSingleton {
	
	private Map<String, JSONObject> api_detail_mapping;
	
	private APIDetailFetcherSingleton() {

		FetchData();
	}

	private void FetchData() {
		
		api_detail_mapping = new HashMap<>();
		
		SqlSession session = MySQLSessionBuilderSingleton.GetInstance().GetSqlSessionFactory().openSession();
		List<Map<String, Object>> data = session.getMapper(MySQLAction.class).FetchAPIDetail();

		session.close();
		
		int length = data.size();
		
		for (int i = 0; i < length; i++) {
			
			JSONObject detail = new JSONObject();
			
			detail.put("class_name", data.get(i).get("class_name"));
			detail.put("function_name", data.get(i).get("function_name"));
			detail.put("login_required", data.get(i).get("login_required"));

			api_detail_mapping.put(data.get(i).get("api_code").toString(), detail);
		}
	}
	
	public void ReInit() { FetchData(); }
	
	public static APIDetailFetcherSingleton GetInstance() { return APIDetailBuilder.instance; }

	public Map<String, JSONObject> GetAPIDetailMapping() { return api_detail_mapping; }
	
	private static class APIDetailBuilder { 
		
		private static APIDetailFetcherSingleton instance = new APIDetailFetcherSingleton(); 
	}
}
