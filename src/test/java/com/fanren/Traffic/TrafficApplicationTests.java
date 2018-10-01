package com.fanren.Traffic;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fanren.Traffic.pojo.KSLAnalysis;
import com.fanren.Traffic.pojo.taxi;
import com.fanren.Traffic.service.KSLAnalysisService;
import com.fanren.Traffic.service.TaxiService;
import com.fanren.Traffic.util.DataToString;
import com.fanren.Traffic.util.SingleTaxiData;
import com.fanren.Traffic.util.TimeAndKSL;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TrafficApplicationTests {

	
	@Autowired
	private KSLAnalysisService KSLAnalysisservice;
	@Autowired
	private TaxiService taxiservice;
	
	@Test
	public void contextLoads() throws Exception {
		String start="2018-03-20";
		String end="2018-03-23";
		String taxi_id="苏FB3592";
		double[] Str1 =new double[99];
		String[] Str2=new String[99];
		List<String> list_tablename=DataToString.getTwoDayTablename(start, end, taxi_id);
		List<String> list_time=DataToString.getTimeString_1(start, end);
		List<TimeAndKSL> list_result=new ArrayList<TimeAndKSL>();
		List<KSLAnalysis> list_data=new ArrayList<KSLAnalysis>();
		List<TimeAndKSL> list_temp=new ArrayList<TimeAndKSL>();
		
		int num=0;
		int num2=0;
		double number;
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
		
		
	}
	@Test
	public void getttime() throws Exception {
		String start="2018-03-19";
		String end="2018-03-24";
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
		System.out.println(list_time.toString());
	}
	@Test
	public void test2() throws Exception {
		String start="2018-03-19";
		String end="2018-03-24";
		List<TimeAndKSL> list=SingleTaxiData.getManyTaxiData(start, end, KSLAnalysisservice, taxiservice);
		System.out.println(list.get(0).getKSL());
	}
	@Test
	public void test3() throws Exception {
		String date="2018-03-20";
		String taxi_id="苏fb5173";
		List<String> track_list=DataToString.getTwoDayTablename(date, date, taxi_id);
		System.out.println(track_list.toString());
	}
	
	
}
