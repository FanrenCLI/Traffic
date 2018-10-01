package com.fanren.Traffic.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fanren.Traffic.pojo.KSLAnalysis;

public interface KSLAnalysisMapper {
	
	List<KSLAnalysis> SelectKSLAnalysisByDate(@Param("table_name") String tablename);

}
