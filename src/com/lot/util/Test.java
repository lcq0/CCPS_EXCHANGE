package com.lot.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lot.en_cn.HtmlParser;

/**
 * @author lcq
 * @date 2017年12月22日下午2:59:49
 * TODO
 **/
public class Test {

	private static final String lineStr = System.lineSeparator();
	public static void main(String[] args) {
		
		String html_path = "E:/developmentDoc/20171213/20171213/Systemdemo";
		List<String> list = new ArrayList<String>();
		
		list = getPathList(html_path, ".html");
		int len = list.size();
		for (int i = 0; i < len; i++) {
			String temp_path = list.get(i);
			String html_content = ReadFile(temp_path);
			String CN_content = getHtmlCN(html_content);
			String txt_path = temp_path.replaceAll(".html", ".txt");
			WriteToFile(CN_content, txt_path, false);
			System.out.println("生成第"+i+"个TXT文件,路径："+txt_path);
		}
		
//		String string = ReadFile(html_path);
//		String html_url = "https://www.cnblogs.com/xing901022/p/3933417.html";
//		HtmlParser parser = new HtmlParser();
//		String string = parser.GetHtmlCont(html_url, "");
//		System.out.println("over");		
		
//		Pattern pattern = Pattern.compile("[^\u4E00-\u9FA5]");
//		Matcher matcher = pattern.matcher("中文asd英语ytuioys数学");
//		
//		String str = matcher.replaceAll(" ").replaceAll("\\s+", lineStr);
//		System.out.println(str);
//		String path = "E:/developmentDoc/20171213/20171213/index.txt";
//		
//		WriteToFile(str, path,false);
//		System.out.println("============================================");
//		System.out.println(ReadFile(path));
	}
	
	
	public static String getHtmlCN(String content) {
		Pattern pattern = Pattern.compile("[^\u4E00-\u9FA5]");
		Matcher matcher = pattern.matcher(content);
		String str = matcher.replaceAll(" ").replaceAll("\\s+", lineStr);
		
		return str;
	}
	
	/**
	 * 获取path文件夹下后缀为type的文件路径
	 * @param path
	 * @param type
	 * @return
	 */
	public static List<String> getPathList(String path,String type) {
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
	 * 
	 * @param str
	 * @param path
	 * @param append
	 */
	public static void WriteToFile(String str,String path,boolean append) {
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
//			oStreamWriter.close();
//			outputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static String ReadFile(String path) {
		StringBuffer strbuf = new StringBuffer();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
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
	
}



