package MDD.Logic;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONObject;

import DB.action.MySQLAction;
import al.weapon.DataTransformer;
import al.weapon.MySQLSessionBuilderSingleton;

public class RegistAPI {

	public static JSONObject InsertUserRegistInfo(JSONObject params){
			
		Map<String, Object> params_for_db = DataTransformer.TranslateJsonObjectToMap(params);
		
		SqlSession session = MySQLSessionBuilderSingleton.GetInstance().GetSqlSessionFactory().openSession();
		session.getMapper(MySQLAction.class).InsertUserRegistInfo(params_for_db);
		session.close();
		
		JSONObject result = new JSONObject();
		
		result.put("status", 0);			
		
		return result;
	}
}
