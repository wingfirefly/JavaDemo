package com.xxniu.file.FileEncrypter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadJson {
	public static void main(String[] args) {
		String sJson = "[{\"ICRMESSAGEHEADER\":[{\"REPORTNO\":\"2015013000001982886304\",\"QUERYTIME\":\"2015.01.30 15:10:22\",\"REPORTCREATETIME\":\"2015.01.30 15:10:23\"}]}]";
		JSONArray jsonAry = null;
		try {
			jsonAry = new JSONArray(sJson);
			JSONArray jsonAry1 = new JSONArray(jsonAry.getJSONObject(0).get("ICRMESSAGEHEADER").toString());
			JSONObject jsonObj = jsonAry1.getJSONObject(0);
			System.out.println(jsonObj.getString("QUERYTIME"));
			System.out.println(jsonObj.length() + "--" + jsonObj.names().get(0).toString());
			System.out.println(jsonObj.names() + ".get(0)");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 方法描述：递归解析json数组 获取表的名称，再进方法里进行循环进行表数据的插入。 每个表都对应唯一的变量，建立一个标识类进行管理。
	 * 每一个类都有对应的bean，用bean对象进行数据的管理，利用反射进行数据库的插入。
	 * 
	 * @param jsonString
	 *            json字符串
	 * @throws JSONException
	 */
	public static void parseJsonArray(String jsonString) throws JSONException {
		JSONArray jsonAry = new JSONArray(jsonString);
		for (int i = 0; i < jsonAry.length(); i++) {
			String sName = jsonAry.getJSONObject(i).names().get(i).toString();
			Object object = jsonAry.getJSONObject(i).get(sName);
			if (object.getClass().getName().equals("org.json.JSONObject")) {
				parseJsonArray(object.toString());
			} else {

			}
		}
	}
}
