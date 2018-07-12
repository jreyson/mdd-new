package al.weapon;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class APIRouter {
	
	private static JSONObject TranslateRequest(HttpServletRequest request) throws IOException {
		
		ServletInputStream stream = request.getInputStream();
		int length = request.getContentLength();
		byte[] content = new byte[length];
		stream.read(content, 0, length);
		stream.close();
		
		
		JSONObject obj = JSONObject.parseObject(new String(content));
		
		return obj;
	}
	
	private static JSONObject ProcessRequest(JSONObject argument) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
	
		String user_id = argument.getString("user_id");
		String api = argument.getString("api");
		JSONObject data = argument.getJSONObject("params");
		
		
		Map<String, JSONObject> api_detail_mapping = APIDetailFetcherSingleton.GetInstance().GetAPIDetailMapping();
		
		
		JSONObject api_detail = api_detail_mapping.get(api);
		
		
		
		String class_name = api_detail.getString("class_name");
		String function_name = api_detail.getString("function_name");
		boolean login_required = api_detail.getBooleanValue("login_required");
		
		JSONObject result = new JSONObject();
		
		if (login_required) {
			
			if (!CheckUserID(user_id)) {}
		}
		else {
		
			Class<?> instance = Class.forName(class_name);
			result = (JSONObject)instance.getMethod(function_name, JSONObject.class).invoke(instance.newInstance(), data);
		}
	
		return result;
	}
	
	public static void RunAjaxRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		
		
		PrintWriter writer = response.getWriter();
		writer.println(ProcessRequest(TranslateRequest(request)).toJSONString());
		writer.close();
	}
	
	private static boolean CheckUserID(String user_id) {
		
		return true;
	}
}
