package com.lot.util;

import java.util.Comparator;

/**
 * @author lcq
 * @date 2017年12月27日上午10:24:19
 * TODO
 **/
public class MapKeyComparator implements Comparator<String>{

	@Override
    public int compare(String str1, String str2) {

//        return str2.length()-str1.length();
        return str1.compareTo(str2);
    }
}
