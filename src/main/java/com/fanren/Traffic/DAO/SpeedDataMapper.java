package com.fanren.Traffic.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fanren.Traffic.pojo.SpeedData;

public interface SpeedDataMapper {

	List<SpeedData> SelectSpeedDataByDate(@Param("table_name") String name);
	
}
