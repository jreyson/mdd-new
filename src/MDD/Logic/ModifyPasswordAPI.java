package MDD.Logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONObject;

import DB.action.MySQLAction;
import al.weapon.DataTransformer;
import al.weapon.MySQLSessionBuilderSingleton;

public class ModifyPasswordAPI {

	public JSONObject ModifyUserPassword(JSONObject params){
		
		Map<String, Object> params_for_db = DataTransformer.TranslateJsonObjectToMap(params);
	
		SqlSession session = MySQLSessionBuilderSingleton.GetInstance().GetSqlSessionFactory().openSession();
		List<Map<String, Object>> data = session.getMapper(MySQLAction.class).ModifyUserPassword(params_for_db);
		session.close();
		
		JSONObject result = new JSONObject();
		result.put("status", data.get(0).get("status"));
		
		return result;
	}
}
