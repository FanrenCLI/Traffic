package com.fanren.Traffic.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fanren.Traffic.pojo.Taxi_query;
import com.fanren.Traffic.service.TaxiQueryService;
import com.fanren.Traffic.service.TaxiService;

@RestController
public class taxiInfo {

	
	@Autowired
	TaxiService taxiservice;
	@Autowired
	TaxiQueryService taxiqueryservice;
	
	@RequestMapping("/add_taxi")
	@ResponseBody
	public String insertTaxi(@Param(value="tel") String tel,@Param(value="taxi_id") String taxi_id) {
		taxiservice.insertTaxi(taxi_id, tel);
		String str="Success";
		return str;
	}
	
	@RequestMapping("/del_taxi")
	@ResponseBody
	public String deleteTaxi(@Param(value="tel") String tel,@Param(value="taxi_id") String taxi_id) {
		
		taxiservice.deleteTaxi(taxi_id,tel);
		String str="Success";
		return str;
	}
	@RequestMapping("/updata_taxi")
	@ResponseBody
	public String updataTaxi(@Param(value="old_taxi_id") String old_taxi_id,
							 @Param(value="new_taxi_id") String new_taxi_id,
							 @Param(value="old_taxi_tel") String old_taxi_tel,
							 @Param(value="new_taxi_tel") String new_taxi_tel) {
		
		taxiservice.updataTaxi(old_taxi_id,new_taxi_id,old_taxi_tel,new_taxi_tel);
		String str="Success";
		return str;
	}
	@RequestMapping("/query_data")
	@ResponseBody
	public List<Taxi_query> QueryTaxiById(@Param(value="query_taxi_id") String query_taxi_id) {
		if(query_taxi_id!="") {
			List<Taxi_query> taxiquery=taxiqueryservice.getTaxiInfo(query_taxi_id);
			return taxiquery;
		}else {
			List<Taxi_query> taxiquery=taxiqueryservice.getTaxiInfo();
			return taxiquery;
		}
		
	}
	
	
	
	
	
	
}
