package com.fanren.Traffic.util;

import java.util.ArrayList;
import java.util.List;

import com.fanren.Traffic.pojo.KSLAnalysis;
import com.fanren.Traffic.pojo.taxi;
import com.fanren.Traffic.service.KSLAnalysisService;
import com.fanren.Traffic.service.TaxiService;


/**
 * @author FanrenCLI
 * @Time 2018年9月24日-上午7:37:27
 * @version 0.0.1
 * @function 单辆车或多辆车空驶率数据
 */
public class SingleTaxiData {
	public static List<TimeAndKSL> getSingleTaxiData(String start,String end,String id,KSLAnalysisService KSLAnalysisservice) throws Exception {
		List<String> list_tablename=DataToString.getTwoDayTablename(start, end, id);
		List<String> list_time=DataToString.getTimeString_1(start, end);
		List<TimeAndKSL> list_result=new ArrayList<>();
		List<KSLAnalysis> list_data=new ArrayList<>();
		int num=0;
		int num2=0;
		double number=0.0;
		int i=0;
		int j=0;
		for( i=0;i<list_tablename.size();i++) {
			list_data=KSLAnalysisservice.getTaxiDataByTablename(list_tablename.get(i).toString());
			for( j=0;j<list_data.size();j++) {
				if(list_data.get(j).getTime().contains(list_time.get(i))) {
					num2++;
				}
				if(list_data.get(j).getTime().contains(list_time.get(i))&&
						list_data.get(j).getStatus().equals("空车")) {
					num++;
				}
			}
			number=(double)num/num2;
			TimeAndKSL timeandKSL=new TimeAndKSL();
			timeandKSL.setKSL(number);
			timeandKSL.setTime(list_time.get(i).toString());
			list_result.add(timeandKSL);
		}
		return list_result;
	}
	
	public static List<TimeAndKSL> getManyTaxiData(String start,String end,
												   KSLAnalysisService KSLAnalysisservice,
												   TaxiService taxiservice) throws Exception {
		List<taxi> list_taxi=taxiservice.getTaxiId();
		List<List<TimeAndKSL>> list_list_result=new ArrayList<>();
		List<String> list_time=DataToString.getTimeString_1(start, end);
		for(int i=0;i<10;i++) {
			List<TimeAndKSL> list_result=SingleTaxiData.getSingleTaxiData(start, end, list_taxi.get(i).getTaxiId().toString(), KSLAnalysisservice);
			list_list_result.add(list_result);
		}
		List<TimeAndKSL> list_result_1=new ArrayList<>();
		for(int k=0;k<list_time.size();k++) {
			TimeAndKSL result=new TimeAndKSL();
			double time_1=0.0;
			for(int j=0;j<10;j++) {
				time_1=time_1+list_list_result.get(j).get(k).getKSL();
			}
			result.setKSL(time_1/10);
			result.setTime(list_list_result.get(0).get(k).getTime());
			list_result_1.add(result);
		}
		return list_result_1;
	}
	
	
}
