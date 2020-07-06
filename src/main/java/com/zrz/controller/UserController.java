package com.zrz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zrz.entity.SysUserInfoPO;
import com.zrz.service.SysUserInfoService;
import com.zrz.util.MemcachedUtils;
 
@Controller
@RequestMapping(value="/user")
public class UserController {
 
	@Autowired
	private SysUserInfoService sysUserInfoService;

	
	@RequestMapping(value={"/getUserInfo"} , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserInfoPO(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>(); 
		SysUserInfoPO sysUserInfoPO = sysUserInfoService.getById(MemcachedUtils.getUserInfoPO(request).getId());
		//清空重要信息
		sysUserInfoPO.setAccount("");
		sysUserInfoPO.setPassword("");
		//map put
		map.put("userInfo", sysUserInfoPO);
		//message
		map.put("message", "");
		
		return map;
	}

}