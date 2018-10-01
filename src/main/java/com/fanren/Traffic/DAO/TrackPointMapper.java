package com.fanren.Traffic.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fanren.Traffic.pojo.TrackPoint;

public interface TrackPointMapper {

	
	List<TrackPoint> getTrackPoint(@Param("table_name") String tablename);
}
