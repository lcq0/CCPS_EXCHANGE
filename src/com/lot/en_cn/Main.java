package com.lot.en_cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lot.util.HtmlEncoding;

/**
 * @author lcq
 * @date 2017年12月22日下午5:26:36 TODO
 **/
public class Main {
	
	private static HtmlParser parser = new HtmlParser();
	private static String html_path = "F:\\parse\\en";
	private static String file_type = ".txt";
	private static String txt_path = "F:\\parse\\cn.txt";
	
	public static void main(String[] args) {
		
//		getHtmlCN();
		
		ParseCN_EN();
		
		String str = "中国 = China+美国 = USA+";
		String[] s = str.split("[+]");
		System.out.println(s[0]);
	}
	
	/**
	 * 替换文件内容
	 */
	private static void ParseCN_EN() {
		Map<String, String> map = parser.getMap(txt_path,HtmlEncoding.gbk_encoding);
		List<String> pathList = parser.getPathList(html_path, file_type);
		int len = pathList.size();
		for (int i = 0; i < len; i++) {
			String path = pathList.get(i);
			String temp_str = parser.ReadFile(path,HtmlEncoding.utf8_encoding);
			for (String key : map.keySet()) {
				if (temp_str.contains(key)) {
					temp_str = temp_str.replaceAll(key, map.get(key));
				}
			}
			temp_str = temp_str.trim();
			parser.WriteToFile(temp_str, path, false,HtmlEncoding.gbk_encoding);
		}	
	}
	
	/**
	 * 获取本地HTML内容的中文字符串并保存到TXT中去
	 */
	private static void getHtmlCN() {
		StringBuffer result = new StringBuffer();
		StringBuffer r1 = new StringBuffer();
		Set<String> set = new HashSet<String>();
		
		
		List<String> list = new ArrayList<String>();

		list = parser.getPathList(html_path, ".html");
		int len = list.size();
		for (int i = 0; i < len; i++) {
			String temp_path = list.get(i);
			String html_content = parser.ReadFile(temp_path,HtmlEncoding.gbk_encoding);
			String CN_content = parser.getHtmlCN(html_content);
			r1.append(CN_content+HtmlEncoding.line_str);
			System.out.println("生成第" + i + "个TXT文件");
		}
		String[] cn_strs = r1.toString().split(HtmlEncoding.line_str);
		for (int j = 0; j < cn_strs.length; j++) {
			set.add(cn_strs[j]);
		}
		for (Iterator it = set.iterator(); it.hasNext();) {
			result.append(it.next().toString()+HtmlEncoding.line_str);
		}
		parser.WriteToFile(result.toString(), "E:/developmentDoc/20171213/cn.txt", false,HtmlEncoding.gbk_encoding);
	}
	
	
}
