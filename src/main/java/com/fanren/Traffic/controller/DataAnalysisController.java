package com.fanren.Traffic.controller;



import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanren.Traffic.pojo.Change_Point;
import com.fanren.Traffic.pojo.SpeedData;
import com.fanren.Traffic.pojo.TrackPoint;
import com.fanren.Traffic.service.Change_PointService;
import com.fanren.Traffic.service.KSLAnalysisService;
import com.fanren.Traffic.service.SpeedDataService;
import com.fanren.Traffic.service.TaxiService;
import com.fanren.Traffic.service.TrackPointService;
import com.fanren.Traffic.util.DataToString;
import com.fanren.Traffic.util.SingleTaxiData;
import com.fanren.Traffic.util.TimeAndKSL;

/**
 * @author FanrenCLI
 * @Time 2018年10月3日-上午11:55:21
 * @version 0.0.1
 */
@Controller
public class DataAnalysisController {

	
	@Autowired
	private KSLAnalysisService KSLAnalysisservice;
	@Autowired
	private TaxiService taxiservice;
	@Autowired
	private TrackPointService trackpointservice;
	@Autowired
	private SpeedDataService speeddataservice;
	@Autowired
	private Change_PointService changepointservice; 
	
	
	@RequestMapping("/KSL_query")
	@ResponseBody
	public List<TimeAndKSL> getKSLData(@Param(value="start") String start,@Param(value="end") String end,
									   @Param(value="taxi_id") String taxi_id) throws Exception{
		return SingleTaxiData.getSingleTaxiData(start, end, taxi_id, KSLAnalysisservice);
		
	}
	
	
	
	@RequestMapping("/KSL_query_NOId")
	@ResponseBody
	public List<TimeAndKSL> getKSLData_NoId(@Param(value="start") String start,@Param(value="end") String end) throws Exception {
		
		return SingleTaxiData.getManyTaxiData(start, end, KSLAnalysisservice, taxiservice);
	}
	
	
	@RequestMapping("/track_query")
	@ResponseBody
	public List<TrackPoint> getTrackPoint(@Param(value="track_taxi") String track_taxi,
										  @Param(value="date") String date) throws Exception{

		List<String> track_list=DataToString.getTwoDayTablename(date, date, track_taxi);
		return trackpointservice.getTrackPoint(track_list.get(0).toString()) ;
	}

	
	@RequestMapping("/speed_query")
	@ResponseBody
	public List<SpeedData> getSpeedData(@Param(value="speed_taxi_id") String speed_taxi_id,
							 @Param(value="speed_date") String speed_date) throws Exception {
		List<String> speed_list=DataToString.getTwoDayTablename(speed_date, speed_date, speed_taxi_id);
		
		return speeddataservice.getSpeedData(speed_list.get(0).toString());
	}
	
	@RequestMapping("/change_pointAnalysis")
	@ResponseBody
	public List<Change_Point> getChange_PointData(){
		
		List<Change_Point> list_change_point=changepointservice.getChange_PointData();
		
		return list_change_point;
	}
	
	
	
	
	
	
	
	
}
