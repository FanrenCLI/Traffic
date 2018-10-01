package com.fanren.Traffic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanren.Traffic.DAO.Taxi_queryMapper;
import com.fanren.Traffic.pojo.Taxi_query;
import com.fanren.Traffic.pojo.Taxi_queryExample;
import com.fanren.Traffic.pojo.Taxi_queryExample.Criteria;


/**
 * @author FanrenCLI
 * @Time 2018年9月24日-上午7:42:35
 * @version 0.0.1
 * 车辆信息查询
 */
@Service
public class TaxiQueryService {
	@Autowired
	Taxi_queryMapper taxiquerymapper;

	public List<Taxi_query> getTaxiInfo(String query_taxi_id){
		// TODO Auto-generated method stub
		Taxi_queryExample taxi_example=new Taxi_queryExample();
		Criteria criteria=taxi_example.createCriteria();
		criteria.andIdCarEqualTo(query_taxi_id);		
		return taxiquerymapper.selectByExample(taxi_example);
	}
	public List<Taxi_query> getTaxiInfo() {
		// TODO Auto-generated method stub
		return taxiquerymapper.selectByExample(null);
	}
	
	
	
}
