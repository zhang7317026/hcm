package com.zrz.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrz.entity.SendDetailPO;
import com.zrz.mapper.SendDetailPOMapper;
import com.zrz.service.SendDetailService;
 
 
 
@Service("sendDetailService")
public class SendDetailServiceImpl implements SendDetailService{
 
    @Autowired
    private SendDetailPOMapper sendDetailPOMapper;
    
    public SendDetailPO getById(String id){
    	if(StringUtils.isBlank(id)){
    		return null;
    	}
    	SendDetailPO sendDetailPO = sendDetailPOMapper.selectByPrimaryKey(id);
    	return sendDetailPO;
    }
    
    public int save(SendDetailPO sendDetailPO){
    	int num = 0;
    	if(StringUtils.isEmpty(sendDetailPO.getId())
    			||sendDetailPOMapper.selectByPrimaryKey(sendDetailPO.getId())==null){
    		num = sendDetailPOMapper.insert(sendDetailPO);
    	}else{
    		num = sendDetailPOMapper.updateByPrimaryKey(sendDetailPO);
    	}
    	
    	return num;
    }
    
    public int insert(SendDetailPO sendDetailPO){
    	return sendDetailPOMapper.insert(sendDetailPO);
    }
    
    public int update(SendDetailPO sendDetailPO){
    	return sendDetailPOMapper.updateByPrimaryKey(sendDetailPO);
    }
    
    public int updateSelective(SendDetailPO sendDetailPO){
    	return sendDetailPOMapper.updateByPrimaryKeySelective(sendDetailPO);
    }
    
    public int deleteById(String id){
    	return sendDetailPOMapper.deleteByPrimaryKey(id);
    }
    
    /**
     * 根据用户id进行按照日期分组并进行排序
     */
    public Map<String,List<SendDetailPO>> getDetailByUserId(String userId){
    	Map<String,List<SendDetailPO>> returnMap = new LinkedHashMap<String,List<SendDetailPO>>();
    	//1-获取所有的日期，倒序
    	List<String> dateList = sendDetailPOMapper.getAllDate();
    	for(String date : dateList){
    		//2-按照每个日期进行正序查询
    		List<SendDetailPO> listOne = sendDetailPOMapper.getListByUserIdAndTime(userId, date);
    		//3-分组组装
    		returnMap.put(date, listOne);
    	}
    	return returnMap;
    }
}