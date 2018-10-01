package com.fanren.Traffic.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * @author FanrenCLI
 * @Time 2018年9月24日-上午7:38:34
 * @version 0.0.1
 * @function 获取起始日期与终止日期之间的所有天
 */
public class DataToString {
//获取日期之内的所有数据库名     此函数留给后来者，如果数据库表不再是两天一张  就可以使用此函数
//	 public static List<String> getTimeString(String begin,String end,String id_car) throws Exception{
//		List<String> result=new ArrayList<>();
//		SimpleDateFormat DataFormat=new SimpleDateFormat("yyyy-MM-dd");
//		Date start_date=DataFormat.parse(begin);
//		Date end_date=DataFormat.parse(end);
//		String sss=DataFormat.format(start_date);
//		String nnn=DataFormat.format(end_date);
//		String sum="";
//		while(!sss.equals(nnn)) {
//			sum=id_car+"_"+sss.replace("-", "");
//			result.add(sum);
//			start_date=new Date( start_date.getTime() + (1000*60*60*24) );
//			sss=DataFormat.format(start_date);
//		}
//		sum=id_car+"_"+nnn.replace("-", "");
//		result.add(sum);
//		return result;
//
//	}
	 //获取日期之内的所有时间列表  用来做空驶率横坐标的值
	 public static List<String> getTimeString_1(String begin,String end) throws Exception{
			List<String> result=new ArrayList<>();
			SimpleDateFormat DataFormat=new SimpleDateFormat("yyyy-MM-dd");
			Date start_date=DataFormat.parse(begin);
			Date end_date=DataFormat.parse(end);
			String sss=DataFormat.format(start_date);
			String nnn=DataFormat.format(end_date);
			while(!sss.equals(nnn)) {
				result.add(sss);
				start_date=new Date( start_date.getTime() + (1000*60*60*24) );
				sss=DataFormat.format(start_date);
			}
			result.add(nnn);
			return result;

		}
	 //针对两天一张表的问题进行数据库表名转换
	 public static List<String> getTwoDayTablename(String begin,String end,String id) throws Exception{
		 List<String> result=new ArrayList<>();
		  List<String> result_1=new ArrayList<>();
		  SimpleDateFormat DataFormat=new SimpleDateFormat("yyyy-MM-dd");
		  Date start_date=DataFormat.parse(begin);
		  Date end_date=DataFormat.parse(end);
		  String sss=DataFormat.format(start_date);
		  String nnn=DataFormat.format(end_date);
		  String temp_1="";
		  while(!sss.equals(nnn)) {
			result.add(sss);
			start_date=new Date( start_date.getTime() + (1000*60*60*24) );
			sss=DataFormat.format(start_date);
		  }
		  result.add(nnn);
		  for(int i=0;i<result.size();i++) {
			  Date temp=DataFormat.parse(result.get(i).toString());
			  if(temp.getDate()%2==0) {
				  temp=new Date( temp.getTime() - (1000*60*60*24) );
				  temp_1=DataFormat.format(temp);
				  result_1.add(id+"_"+temp_1.replace("-", ""));
			  }else {
				  result_1.add(id+"_"+result.get(i).toString().replace("-", ""));
			  }
		  }
		  return result_1;
	 }
}
