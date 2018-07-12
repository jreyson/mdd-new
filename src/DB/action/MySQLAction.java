package DB.action;

import java.util.List;
import java.util.Map;

public interface MySQLAction {

	List<Map<String, Object>> FetchAPIDetail();
	
	List<Map<String, Object>> RetrieveCategory(Map<String, Object> params);

	List<Map<String, Object>> RetrieveLoopAD(Map<String, Object> params);

	List<Map<String, Object>> RetrievePromotionProduct(Map<String, Object> params);

	List<Map<String, Object>> RetrieveSiteNews(Map<String, Object> params);

	List<Map<String, Object>> RetrieveSuggestBrand(Map<String, Object> params);

	List<Map<String, Object>> RetrieveSuggestProduct(Map<String, Object> params);
	
	List<Map<String, Object>> InsertUserRegistInfo(Map<String, Object> params);
	
	List<Map<String, Object>> ModifyUserPassword(Map<String, Object> params);
	
	List<Map<String, Object>> RetrieveUserLoginValidate(Map<String, Object> params);
}
