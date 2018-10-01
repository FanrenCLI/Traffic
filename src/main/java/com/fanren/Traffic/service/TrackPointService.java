package com.fanren.Traffic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanren.Traffic.DAO.TrackPointMapper;
import com.fanren.Traffic.pojo.TrackPoint;

@Service
public class TrackPointService {

	@Autowired
	TrackPointMapper trackpointmapper;
	
	public List<TrackPoint> getTrackPoint(String tablename) {
		return trackpointmapper.getTrackPoint(tablename);
	}
}
