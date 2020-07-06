package com.zrz.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrz.entity.PositionListPO;
import com.zrz.mapper.PositionListPOMapper;
import com.zrz.service.PositionListService;
 
 
 
@Service("positionListService")
public class PositionListServiceImpl implements PositionListService{
 
    @Autowired
    private PositionListPOMapper positionListPOMapper;
    
    public PositionListPO getById(String id){
    	if(StringUtils.isBlank(id)){
    		return null;
    	}
    	PositionListPO PositionListPO = positionListPOMapper.selectByPrimaryKey(id);
    	return PositionListPO;
    }
    
    public int save(PositionListPO PositionListPO){
    	int num = 0;
    	if(StringUtils.isEmpty(PositionListPO.getId())
    			||positionListPOMapper.selectByPrimaryKey(PositionListPO.getId())==null){
    		num = positionListPOMapper.insert(PositionListPO);
    	}else{
    		num = positionListPOMapper.updateByPrimaryKey(PositionListPO);
    	}
    	
    	return num;
    }
    
    public int insert(PositionListPO PositionListPO){
    	return positionListPOMapper.insert(PositionListPO);
    }
    
    public int update(PositionListPO PositionListPO){
    	return positionListPOMapper.updateByPrimaryKey(PositionListPO);
    }
    
    public int updateSelective(PositionListPO PositionListPO){
    	return positionListPOMapper.updateByPrimaryKeySelective(PositionListPO);
    }
    
    public int deleteById(String id){
    	return positionListPOMapper.deleteByPrimaryKey(id);
    }
    
    
    /**
	 * 获取所有list
	 */
    public List<PositionListPO> getAllList(){
    	return positionListPOMapper.getAllList();
    }
}