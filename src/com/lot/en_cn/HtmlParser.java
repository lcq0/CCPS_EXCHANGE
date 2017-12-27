package com.lot.en_cn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
	 * 
	 * @param path
	 * @param encoding
	 * @return
	 */
	public Object[] getMapObjects(String path,String encoding) {
		StringBuffer strBuffer = new StringBuffer();
		Map<String, String> result_map = new HashMap<String, String>() ;
		Map<String, String> temp_map = null;
		try {
			FileInputStream inputStream = new FileInputStream(new File(path));
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding);
			@SuppressWarnings("resource")
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
			int size = result_map.size();
			Object[] objects = new Object[size];
			int i=0;
			for (Map.Entry<String, String> entry : result_map.entrySet()) {
	            temp_map = new HashMap<String, String>();
	            temp_map.put(entry.getKey(), entry.getValue());
	            objects[i++]=temp_map;
	        }
			for (int j = 0; j < objects.length; j++) {
				Map<String, String> m0 = (Map<String, String>) objects[j];
				System.out.println(m0.entrySet().iterator().next().getKey() + " " + m0.entrySet().iterator().next().getValue());
			}
			for (int j = 0; j < size; j++) {
				for (int j2 = j+1; j2 < size; j2++) {
					Map<String, String> m0 = (Map<String, String>) objects[j];
					Map<String, String> m1 = (Map<String, String>) objects[j2];
					if (m1.entrySet().iterator().next().getKey().length()>m0.entrySet().iterator().next().getKey().length()) {
						objects[j2] = m0;
						objects[j] = m1;
					}
					
				}
			}
			System.out.println("排序之后===================================================================\r\n\r\n\r\n");
			for (int j = 0; j < objects.length; j++) {
				Map<String, String> m0 = (Map<String, String>) objects[j];
				System.out.println(m0.entrySet().iterator().next().getKey() + " " + m0.entrySet().iterator().next().getValue());
			}
			result_map = new HashMap<String, String>();
			for (int j = 0; j < objects.length; j++) {
				result_map.putAll((Map<String, String>) objects[j]);
			}
			return objects;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		Map<String, String> result_map = new HashMap<String, String>() ;
		Map<String, String> temp_map = null;
		try {
			FileInputStream inputStream = new FileInputStream(new File(path));
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding);
			@SuppressWarnings("resource")
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
			int size = result_map.size();
			Object[] objects = new Object[size];
			int i=0;
			for (Map.Entry<String, String> entry : result_map.entrySet()) {
	            temp_map = new HashMap<String, String>();
	            temp_map.put(entry.getKey(), entry.getValue());
	            objects[i++]=temp_map;
	        }
			for (int j = 0; j < objects.length; j++) {
				Map<String, String> m0 = (Map<String, String>) objects[j];
				System.out.println(m0.entrySet().iterator().next().getKey() + " " + m0.entrySet().iterator().next().getValue());
			}
			for (int j = 0; j < size; j++) {
				for (int j2 = j+1; j2 < size; j2++) {
					Map<String, String> m0 = (Map<String, String>) objects[j];
					Map<String, String> m1 = (Map<String, String>) objects[j2];
					if (m1.entrySet().iterator().next().getKey().length()>m0.entrySet().iterator().next().getKey().length()) {
						objects[j2] = m0;
						objects[j] = m1;
					}
					
				}
			}
			System.out.println("排序之后===================================================================\r\n\r\n\r\n");
			for (int j = 0; j < objects.length; j++) {
				Map<String, String> m0 = (Map<String, String>) objects[j];
				System.out.println(m0.entrySet().iterator().next().getKey() + " " + m0.entrySet().iterator().next().getValue());
			}
			result_map = new HashMap<String, String>();
			for (int j = 0; j < objects.length; j++) {
				result_map.putAll((Map<String, String>) objects[j]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_map;
	}
	
	public Map<String, String> sortMapByKey(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {
			public int compare(String key1, String key2) {
				return key1.compareTo(key2);
			}});
		sortedMap.putAll(oriMap);
		return sortedMap;
	}	
	
	public Map<String, String> sortMapByKeyLength(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {
			public int compare(String key1, String key2) {
				return key1.compareTo(key2);
			}});
		sortedMap.putAll(oriMap);
		return sortedMap;
	}
	
}






