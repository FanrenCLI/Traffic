package com.fanren.Traffic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanren.Traffic.DAO.Change_PointMapper;
import com.fanren.Traffic.pojo.Change_Point;


@Service
public class Change_PointService {

	
	@Autowired
	private Change_PointMapper changepointmapper;
	
	
	public List<Change_Point> getChange_PointData(){
		
		return changepointmapper.selectByExample(null);
	}
}
