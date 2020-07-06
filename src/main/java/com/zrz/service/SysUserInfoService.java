package com.zrz.service;

import java.util.List;

import com.zrz.entity.SysUserInfoPO;

public interface SysUserInfoService {
	
	SysUserInfoPO getById(String id);
	
	int save(SysUserInfoPO sysUserInfoPO);
	
	int insert(SysUserInfoPO sysUserInfoPO);
	
	int update(SysUserInfoPO sysUserInfoPO);
	
	int updateSelective(SysUserInfoPO sysUserInfoPO);
	
	int deleteById(String id);
	
	SysUserInfoPO getByAccount(String account);
	
	List<SysUserInfoPO> getAllEnabled();
	
	
}
