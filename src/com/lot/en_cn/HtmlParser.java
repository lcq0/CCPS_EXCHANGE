package com.lot.en_cn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author lcq
 * @date 2017年12月22日上午11:53:56
 * TODO
 **/
public class HtmlParser {
	
	public String GetHtmlCont(String html_url,String path) {
		try {
			URL url = new URL(html_url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			if (connection!=null) {
				connection.connect();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line=null;
				StringBuffer strBuffer = new StringBuffer();
				while ((line=reader.readLine())!=null) {
					strBuffer.append(line);
				}
				connection.disconnect();
				return strBuffer.toString();
			}else {
				System.out.println("url:"+html_url+"\r\n未连接上HTML");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
