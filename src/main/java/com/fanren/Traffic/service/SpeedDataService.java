package com.fanren.Traffic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanren.Traffic.DAO.SpeedDataMapper;
import com.fanren.Traffic.pojo.SpeedData;

@Service
public class SpeedDataService {

	
	@Autowired
	private SpeedDataMapper speeddatamapper;
	
	
	
	public List<SpeedData> getSpeedData(String tablename) {
		
		return speeddatamapper.SelectSpeedDataByDate(tablename);
	}
}
