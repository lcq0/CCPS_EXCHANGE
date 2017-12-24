package com.lot.en_cn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lot.util.HtmlEncoding;

/**
 * @author lcq
 * @date 2017年12月22日上午11:53:56
 * TODO
 **/
public class HtmlParser {
	
	/**
	 * 将HTML内容转成中文，字符串以换行符间隔
	 * @param content
	 * @return
	 */
	public String getHtmlCN(String content) {
		Pattern pattern = Pattern.compile("[^\u4E00-\u9FA5]");
		Matcher matcher = pattern.matcher(content);
		String str = matcher.replaceAll(" ").replaceAll("\\s+", HtmlEncoding.line_str);
		
		return str;
	}
	
	/**
	 * 获取path文件夹下后缀为type的文件路径
	 * @param path
	 * @param type
	 * @return
	 */
	public List<String> getPathList(String path,String type) {
		List<String> list = new ArrayList<String>();
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File file2 : files) {
				if (file2.isDirectory()) {
					getPathList(file2.getAbsolutePath(), type);
				}else {
					if (file2.getName().endsWith(type)) {
						list.add(file2.getAbsolutePath());
					}
				}
			}
		}
		return list;
	}
	/**
	 * str写入TXT
	 * @param str
	 * @param path
	 * @param append
	 */
	public void WriteToFile(String str,String path,boolean append,String encoding) {
		try {
			FileWriter writer = new FileWriter(path, append);
			BufferedWriter bw = new BufferedWriter(writer);
//			bw.append("第二次添加\r\n");
			bw.write(str+System.lineSeparator());
			bw.close();
			writer.close();
//			FileOutputStream outputStream = new FileOutputStream(path, append);
//			OutputStreamWriter oStreamWriter = new OutputStreamWriter(outputStream, HtmlEncoding.utf8_encoding);
//			BufferedWriter bw = new BufferedWriter(oStreamWriter);
//			bw.write(str);
//			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取文件，返回字符串
	 * @param path
	 * @return
	 */
	public String ReadFile(String path,String encoding) {
		StringBuffer strbuf = new StringBuffer();
		
		try {
//			BufferedReader reader = new BufferedReader(new FileReader(path));
			FileInputStream inputStream = new FileInputStream(new File(path));
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str=reader.readLine())!=null) {
				strbuf.append(System.lineSeparator()+str);
			}
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return strbuf.toString();
	}
	
	/**
	 * 将文本内容转成map
	 * 内容：
	 * key1=value1
	 * key2=value2
	 * @param path
	 * @return
	 */
	public Map<String, String> getMap(String path,String encoding) {
		StringBuffer strBuffer = new StringBuffer();
		Map<String, String> result_map = new HashMap<>();
		try {
			FileInputStream inputStream = new FileInputStream(new File(path));
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String str=null;
			while ((str=reader.readLine())!=null) {
				strBuffer.append(str+"+");
			}
			String  temp_str = strBuffer.toString().trim();
			String[] array_str = temp_str.split("[+]");
			for (String string : array_str) {
				String[] temp_str1 = string.replaceAll(" ", "").split("=");
				if (temp_str1!=null&&temp_str1.length>1) {
					result_map.put(temp_str1[0], temp_str1[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_map;
	}
	
}






