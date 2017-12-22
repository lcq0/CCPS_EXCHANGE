package com.lot.en_cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.lot.util.HtmlEncoding;

/**
 * @author lcq
 * @date 2017年12月22日下午5:26:36 TODO
 **/
public class Main {

	public static void main(String[] args) {

		StringBuffer result = new StringBuffer();
		StringBuffer r1 = new StringBuffer();
		Set<String> set = new HashSet<String>();
		HtmlParser parser = new HtmlParser();
		String html_path = "E:/developmentDoc/20171213/20171213/Systemdemo";
		List<String> list = new ArrayList<String>();

		list = parser.getPathList(html_path, ".html");
		int len = list.size();
		for (int i = 0; i < len; i++) {
			String temp_path = list.get(i);
			String html_content = parser.ReadFile(temp_path);
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
		parser.WriteToFile(result.toString(), "E:/developmentDoc/20171213/cn.txt", false);
	}
}
