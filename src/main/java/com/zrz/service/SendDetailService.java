package com.zrz.service;

import java.util.List;
import java.util.Map;

import com.zrz.entity.SendDetailPO;

public interface SendDetailService {
	
	SendDetailPO getById(String id);
	
	int save(SendDetailPO sendDetailPO);
	
	int insert(SendDetailPO sendDetailPO);
	
	int update(SendDetailPO SendDetailPO);
	
	int updateSelective(SendDetailPO sendDetailPO);
	
	int deleteById(String id);
	
	Map<String,List<SendDetailPO>> getDetailByUserId(String userId);
	
}
