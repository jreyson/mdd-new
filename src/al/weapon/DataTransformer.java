package al.weapon;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DataTransformer {

	public static Map<String, Object> TranslateJsonObjectToMap(JSONObject data) {
		
		Map<String, Object> result = new HashMap<>();
		
		Set<Entry<String, Object>> key_set = data.entrySet();
		
		Iterator<Entry<String, Object>> iterator = key_set.iterator();
		
		while (iterator.hasNext()) {
			
			Entry<String, Object> key_value = iterator.next();
			result.put(key_value.getKey(), key_value.getValue());
		}
		
		return result;
	}
	
	public static Map<String, Object> TranslateChineseJsonObjectToMap(JSONObject data) throws UnsupportedEncodingException {
		
		Map<String, Object> result = new HashMap<>();
		
		Set<Entry<String, Object>> key_set = data.entrySet();
		
		Iterator<Entry<String, Object>> iterator = key_set.iterator();
		
		while (iterator.hasNext()) {
			
			Entry<String, Object> key_value = iterator.next();
			result.put(key_value.getKey(), URLDecoder.decode(key_value.getValue().toString(),"UTF-8"));
		}
		
		return result;
	}
	
	public static JSONArray TranslateMapToJsonArray(List<Map<String, Object>> data) {
		
		JSONArray result = new JSONArray();
		
		int length = data == null ? 0 : data.size();
		
		for (int i = 0; i < length; i++) {
			
			JSONObject temp = new JSONObject();
			
			temp.putAll(data.get(i));
			
			result.add(temp);
		}
		
		return result;
	}
	
	public static JSONArray TranslateMapToChineseJsonArray(List<Map<String, Object>> data) throws UnsupportedEncodingException {
		
		JSONArray result = new JSONArray();
		
		int length = data == null ? 0 : data.size();
		
		for (int i = 0; i < length; i++) {
			
			JSONObject temp = new JSONObject();
			
			Set<Entry<String, Object>> key_set = data.get(i).entrySet();
			Iterator<Entry<String, Object>> iterator = key_set.iterator();
			
			while (iterator.hasNext()) {
				Entry<String, Object> key_value = iterator.next();
				temp.put(key_value.getKey(), URLEncoder.encode(key_value.getValue().toString(),"UTF-8"));
			}
			
			result.add(temp);
		}
		
		return result;
	}
}
