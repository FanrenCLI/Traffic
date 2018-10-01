package com.fanren.Traffic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanren.Traffic.DAO.taxiMapper;
import com.fanren.Traffic.pojo.taxi;
import com.fanren.Traffic.pojo.taxiExample;
import com.fanren.Traffic.pojo.taxiExample.Criteria;

/**
 * @author FanrenCLI
 * @Time 2018年9月24日-上午7:43:03
 * @version 0.0.1
 * 车辆的增删查改
 */
@Service
public class TaxiService {
	
	
	@Autowired
	taxiMapper taximapper;
	
	
	public void insertTaxi(String taxiId,String tel) {
		
		taxi taxi=new taxi();
		taxi.setTaxiId(taxiId);
		taxi.setTel(tel);
		taximapper.insert(taxi);
		
	}


	public void deleteTaxi(String taxi_id, String tel) {
		// TODO Auto-generated method stub
		taxiExample taxiExample=new taxiExample();
		Criteria criteria=taxiExample.createCriteria();
		criteria.andTaxiIdEqualTo(taxi_id);
		criteria.andTelEqualTo(tel);
		taximapper.deleteByExample(taxiExample);
	}


	public void updataTaxi(String old_taxi_id, String new_taxi_id, String old_taxi_tel, String new_taxi_tel) {
		// TODO Auto-generated method stub
		taxi taxi=new taxi();
		taxi.setTaxiId(new_taxi_id);
		taxi.setTel(new_taxi_tel);
		taxiExample taxiExample=new taxiExample();
		Criteria criteria=taxiExample.createCriteria();
		criteria.andTaxiIdEqualTo(old_taxi_id);
		criteria.andTelEqualTo(old_taxi_tel);
		taximapper.updateByExampleSelective(taxi,taxiExample);
	}


	public List<taxi> getTaxiId() {
		// TODO Auto-generated method stub
		return taximapper.selectByExample(null);
	}
}
