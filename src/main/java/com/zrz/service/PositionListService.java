package com.zrz.service;

import java.util.List;

import com.zrz.entity.PositionListPO;

public interface PositionListService {
	
	PositionListPO getById(String id);
	
	int save(PositionListPO positionListPO);
	
	int insert(PositionListPO positionListPO);
	
	int update(PositionListPO positionListPO);
	
	int updateSelective(PositionListPO positionListPO);
	
	int deleteById(String id);

	/**
	 * 获取所有list
	 */
	List<PositionListPO> getAllList();
}
