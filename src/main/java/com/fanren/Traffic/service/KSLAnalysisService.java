package com.fanren.Traffic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanren.Traffic.DAO.KSLAnalysisMapper;
import com.fanren.Traffic.pojo.KSLAnalysis;

/**
 * @author FanrenCLI
 * @Time 2018年9月24日-上午7:42:07
 * @version 0.0.1
 * 数据挖掘
 */
@Service
public class KSLAnalysisService {

	@Autowired
	private KSLAnalysisMapper KSLAnalysismapper;

	
	public List<KSLAnalysis> getTaxiDataByTablename(String Tablename) {
		// TODO Auto-generated method stub
		return KSLAnalysismapper.SelectKSLAnalysisByDate(Tablename);
	}
}
