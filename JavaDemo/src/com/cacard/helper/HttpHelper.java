/**
 * author:cacard
 */

package com.cacard.helper;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用于调用Web接口，内部使用了Apache HttpClient
 */
public class HttpHelper {

	private static final String CONTENT_TYPE_HEADER = "Content-Type";
	private static final String CONTENT_TYPE = "application/json; charset=UTF-8";

	/**
	 * GET请求
	 * @param url
	 * @return 响应结果
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String Get(String url) throws ClientProtocolException,
			IOException {
		String result = null;

		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE);

		CloseableHttpResponse res = null;
		try {
			res = (CloseableHttpResponse) client.execute(httpGet);

			HttpEntity entity = res.getEntity();
			if (entity != null) {
				long len = entity.getContentLength();
				if (len != -1) {
					result = EntityUtils.toString(entity);
				}
			}
		} finally {
			res.close();
		}

		return result;
	}

	/**
	 * Post数据
	 * @param url
	 * @param map 数据对
	 * @return 响应结果
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String Post(String url, LinkedHashMap<String, String> map)
			throws ParseException, IOException {
		String result = null;

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE);

		String json = ConvertMapToJsonString(map);
		StringEntity se = new StringEntity(json,"UTF-8");
		post.setEntity(se);

		try {
			HttpResponse response = client.execute(post);
			result = EntityUtils.toString(response.getEntity()/*,"UTF-8"*/);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	/**
	 * Map -> JsonString (Using Jackson)
	 */
	public static String ConvertMapToJsonString(
			LinkedHashMap<String, String> map) {
		if (map == null || map.keySet().size() == 0) {
			return "{}";
		}

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;

		try {
			jsonString = mapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return jsonString;
	}



}
