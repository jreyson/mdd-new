package MDD.Logic;



import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONArray;

import DB.action.MySQLAction;
import al.weapon.DataTransformer;
import al.weapon.MySQLSessionBuilderSingleton;

public class CategoryAPI {

	public static JSONArray RetrieveCategoryByPoint(String point, String category_type) throws UnsupportedEncodingException {
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("retrieve_type", 3);
		params.put("p_category_point", point);
		params.put("p_category_id", "");
		params.put("p_category_type", category_type);
		
		SqlSession session = MySQLSessionBuilderSingleton.GetInstance().GetSqlSessionFactory().openSession();
		
		JSONArray response = DataTransformer.TranslateMapToChineseJsonArray(session.getMapper(MySQLAction.class).RetrieveCategory(params));
		
		session.close();
		
		return response;
	}
}
