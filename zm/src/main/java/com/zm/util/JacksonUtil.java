package com.zm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义响应结构
 */
public class JacksonUtil {
	/** jackson对象 */
	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		// 反序列化时忽略在JSON字符串中存在，而在Java中不存在的属性
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 定义时间格式
		MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 将对象转换成json字符串。
	 * @param data
	 * @return
	 */
	public static String bean2Json(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 * 
	 * @param jsonData json数据
	 * @param beanType class
	 * @return
	 */
	public static <T> T json2Bean(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> json2BeanList(String jsonData, Class<T> beanType) {
		// List类型mapper.getTypeFactory().constructParametricType(List.class, Bean.class);
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			return MAPPER.readValue(jsonData, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 将json数据转换成Map<k,v>
	 * @param jsonData
	 * @param vClass
	 * */
	public static <V> Map<String, V> json2Map(String jsonData, Class<V> vClass) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(HashMap.class, String.class, vClass);
		try {
			return MAPPER.readValue(jsonData, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}

	public static Map<String, Object> json2Map(String jsonString) throws Exception {
		try{
			return MAPPER.readValue(jsonString, Map.class);
		} catch (Exception e){
			e.printStackTrace();
		}
		return new HashMap<>();
	}
	/**
	 * 将json数据转换成Map<String,Object>
	 *
	 * */
	private static Map<String, Object> json2MapRecursion(String jsonData) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			map = MAPPER.readValue(jsonData, Map.class);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				Object obj = entry.getValue();
				if (obj != null && obj instanceof String) {
					String str = ((String) obj);
					if (str.startsWith("[")) {
						List<?> list = json2ListRecursion(str);
						map.put(entry.getKey(), list);
					} else if (str.startsWith("{")) {
						Map<String, Object> mapRecursion = json2MapRecursion(str);
						map.put(entry.getKey(), mapRecursion);
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 把json解析成list，如果list内部的元素存在jsonString，继续解析
	 * @param json
	 * @return
	 * @throws Exception
	 */
	private static List<Object> json2ListRecursion(String json) throws Exception {
		List<Object> list = MAPPER.readValue(json, List.class);
		for (Object obj : list) {
			if (obj != null && obj instanceof String) {
				String str = (String) obj;
				if (str.startsWith("[")) {
					obj = json2ListRecursion(str);
				} else if (obj.toString().startsWith("{")) {
					obj = json2MapRecursion(str);
				}
			}
		}

		return list;
	}

	/*public static void main(String[] args) throws Exception {
		String json = "{\"session_key\":\"Izffr8Lf8N3uFUUXlt5WvQ==\",\"expires_in\":7200,\"openid\":\"o2l0J0dLBB0BCn3xvSDPW4tT6_0U\"}";
		Map<String,String> map = json2Map(json,String.class);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			 System.out.println("key= " + entry.getKey() + " and value= "
					  + entry.getValue());
			 }
	}*/
}
