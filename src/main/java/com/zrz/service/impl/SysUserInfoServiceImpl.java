package com.zrz.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrz.entity.SysUserInfoPO;
import com.zrz.mapper.SysUserInfoPOMapper;
import com.zrz.service.SysUserInfoService;
 
 
 
@Service("sysUserInfoService")
public class SysUserInfoServiceImpl implements SysUserInfoService{
 
    @Autowired
    private SysUserInfoPOMapper sysUserInfoPOMapper;
    
    public SysUserInfoPO getById(String id){
    	if(StringUtils.isBlank(id)){
    		return null;
    	}
    	SysUserInfoPO sysUserInfoPO = sysUserInfoPOMapper.selectByPrimaryKey(id);
    	return sysUserInfoPO;
    }
    
    public int save(SysUserInfoPO sysUserInfoPO){
    	int num = 0;
    	if(StringUtils.isEmpty(sysUserInfoPO.getId())
    			||sysUserInfoPOMapper.selectByPrimaryKey(sysUserInfoPO.getId())==null){
    		num = sysUserInfoPOMapper.insert(sysUserInfoPO);
    	}else{
    		num = sysUserInfoPOMapper.updateByPrimaryKey(sysUserInfoPO);
    	}
    	
    	return num;
    }
    
    public int insert(SysUserInfoPO sysUserInfoPO){
    	return sysUserInfoPOMapper.insert(sysUserInfoPO);
    }
    
    public int update(SysUserInfoPO sysUserInfoPO){
    	return sysUserInfoPOMapper.updateByPrimaryKey(sysUserInfoPO);
    }
    
    public int updateSelective(SysUserInfoPO sysUserInfoPO){
    	return sysUserInfoPOMapper.updateByPrimaryKeySelective(sysUserInfoPO);
    }
    
    public int deleteById(String id){
    	return sysUserInfoPOMapper.deleteByPrimaryKey(id);
    }
    
    public SysUserInfoPO getByAccount(String account){
    	return sysUserInfoPOMapper.getByAccount(account);
    }
    
    public List<SysUserInfoPO> getAllEnabled(){
    	return sysUserInfoPOMapper.getAllEnabled();
    }
    
}