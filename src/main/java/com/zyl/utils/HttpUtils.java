package com.zyl.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

	private static Log log = LogFactory.getLog(HttpUtils.class);

	/**
	 * 发送请求到指定服务器
	 * 
	 * @param url 请求地址
	 */
	public static String sendGet(String url, String authorization) {
		BufferedReader br = null;
		StringBuffer result = new StringBuffer("");
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36");
			if (authorization != null) {
				connection.setRequestProperty("Authorization", authorization);
			}
			
			// 是否允许输入输出
			connection.setDoInput(true);
			
			// 提交模式
			connection.setRequestMethod("GET");
			
			// 开始连接请求
			connection.connect();
			
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			br.close();
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e);
			}
		}
		
		return result.toString();
	}
	public static String newscGet(String url, String authorization) {
		BufferedReader br = null;
		StringBuffer result = new StringBuffer("");
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/json;charset=GBK");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36");
			if (authorization != null) {
				connection.setRequestProperty("Authorization", authorization);
			}
			
			// 是否允许输入输出
			connection.setDoInput(true);
			
			// 提交模式
			connection.setRequestMethod("GET");
			
			// 开始连接请求
			connection.connect();
			
			br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			br.close();
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e);
			}
		}
		
		return result.toString();
	}
	/*public static String prophetGet(String url) {
		String result ="";
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse res = httpClient.execute(get);
			HttpEntity entity = res.getEntity();
			result = EntityUtils.toString(entity);
			res.close();
			httpClient.close();
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e);
			}
		}
		return result;
	}
	public static String prophetPost(String url,String params,String charSet) {
		String result ="";
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			post.setHeader("Accept", "application/json");   
			post.setHeader("Content-Type", "application/json;charset=utf-8");  
	        if(StringUtils.isEmpty(charSet)){
	        	charSet = "UTF-8";  
	        }
	        StringEntity entity = new StringEntity(params, charSet);  
	        post.setEntity(entity);
			CloseableHttpResponse res = httpClient.execute(post);
			HttpEntity entity2 = res.getEntity();
			result = EntityUtils.toString(entity2);
			res.close();
			httpClient.close();
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e);
			}
		}
		return result;
	}*/
	/**
	 * 发送请求到指定服务器
	 * 
	 * @param url 请求地址
	 */
	public static String sendPost(String url, String jsonData, String authorization) {
		System.out.println(jsonData);
		
		BufferedWriter bw = null;
		BufferedReader br = null;
		StringBuffer result = new StringBuffer("");
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36");
			if (authorization != null) {
				connection.setRequestProperty("Authorization", authorization);
			}
			
			// 是否允许输入输出
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			// 提交模式
			connection.setRequestMethod("POST");
			
			// 开始连接请求
			connection.connect();
			
			bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			bw.write(jsonData);
			bw.flush();
			bw.close();
			
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			br.close();

		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e);
			}
		}
		
		return result.toString();
	}

	/**
	 * 发送请求到指定服务器
	 * 
	 * @param url 请求地址
	 */
	/*public static String sendRequest(String url, String jsonData, String authorization) {

		System.out.println(jsonData);

		String result = null;

		try {

			// 建立请求
			Content content = Request.Post(url)

			// 追加头属性
			.addHeader("Content-Type", "application/json").addHeader("Authorization", authorization)

			// 设置入参
			.bodyString(jsonData, ContentType.APPLICATION_JSON)

			// 连接请求
			.execute().returnContent();

			// Print content
			result = content.toString();
		} catch (IOException e) {
			log.error(e);
		}
		
		return result;
	}*/

	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(sendPost("http://sherlock.tuifacai.com/api/user/auth", "{\"account\": \"sherlock_test\",\"password\": \"password\"}", ""));
//		System.out.println(newscGet("http://211.157.2.89:7779/sinochem/newsids?uid=83000001&sid=jcache-sinochem-data&key=28033|%E4%BA%9A%E6%B4%B2%E5%8E%9F%E6%B2%B9%E6%94%B6%E5%B8%82%E6%8A%A5%E5%91%8A", null));
		StringBuilder url=new StringBuilder("http://10.143.0.76:8080/prophet/api/1/brent/day");
//		url.append(URLEncoder.encode("28033|亚洲原油收市报告", "UTF-8"));
		System.out.println(newscGet(url.toString(), null));
	}
}
