package MDD.Logic;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONArray;

import DB.action.MySQLAction;
import al.weapon.DataTransformer;
import al.weapon.MySQLSessionBuilderSingleton;

public class ProductPromotion {

	public static JSONArray RetrievePromotionByPoint(String point) throws UnsupportedEncodingException {
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("retrieve_type", 2);
		params.put("p_promotion_point", point);
		params.put("p_promotion_id", "");
		
		SqlSession session = MySQLSessionBuilderSingleton.GetInstance().GetSqlSessionFactory().openSession();
		
		JSONArray response = DataTransformer.TranslateMapToChineseJsonArray(session.getMapper(MySQLAction.class).RetrievePromotionProduct(params));
		
		session.close();
		
		return response;
	}
}
