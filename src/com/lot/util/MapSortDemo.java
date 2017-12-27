package com.lot.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author lcq
 * @date 2017年12月27日上午10:33:02
 * TODO
 **/
public class MapSortDemo {

    public static void main(String[] args) {

        Map<String, String> map = new TreeMap<String, String>();

//        map.put("KFC", "kfc");
//        map.put("WNBA", "wnba");
//        map.put("NBA", "nba");
//        map.put("CBA", "cba");
        //一般电商贸易=GeneralE-commerce, 下单时间=OrderTime, 下载=Download, 不通过=Disapproved, 业务处理=BusinessHandling, 东莞海关=DongguanCustoms
        map.put("一般电商贸易", "GeneralE-commerce");
        map.put("下单时间", "OrderTime");
        map.put("下载", "Download");
        map.put("不通过", "Disapproved");
        
        map.put("快件到达成都春熙路点部", "PackagehasarrivedatDepotCenteronChunxiRd");
        map.put("验证结果", "AuthenticationResult");
        map.put("清单海关编号", "SpecificationCustomsID");
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println("===========");
        Map<String, String> resultMap = sortMapByKey(map);  //按Key进行排序

        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }
}
