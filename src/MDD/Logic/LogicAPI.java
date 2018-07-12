package MDD.Logic;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class LogicAPI {

	public JSONObject RetrieveFrontPageData(JSONObject params) throws UnsupportedEncodingException {
		
		String point = params.getString("point");
		
		JSONArray loop_ad = LoopADAPI.RetrieveLoopADByPoint(point);
		
		JSONArray category = CategoryAPI.RetrieveCategoryByPoint(point, "normal");
		
		JSONArray category_point = CategoryAPI.RetrieveCategoryByPoint(point, "promotion");
		
		JSONArray site_product_news = SiteProductNews.RetrieveSiteProductNewsByPoint(point);
		
		JSONArray product_promotion = ProductPromotion.RetrievePromotionByPoint(point);
		
		JSONArray suggest_brand = SuggestBrand.RetrieveSuggestBrandByPoint(point);
		
		JSONArray suggest_product = SuggestProduct.RetrieveSuggestProductByPoint(point);
		
		
		
		JSONObject response = new JSONObject();
		
		response.put("status", 0);
		
		response.put("loop_ad", loop_ad);
		response.put("category", category);
		response.put("category_point", category_point);
		response.put("site_product_news", site_product_news);
		response.put("product_promotion", product_promotion);
		response.put("suggest_brand", suggest_brand);
		response.put("suggest_product", suggest_product);
		
		return response;
	}
}
