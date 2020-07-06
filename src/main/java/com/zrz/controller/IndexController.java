package com.zrz.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zrz.entity.PositionListPO;
import com.zrz.entity.SendDetailPO;
import com.zrz.entity.SysUserInfoPO;
import com.zrz.hcm.Run;
import com.zrz.service.PositionListService;
import com.zrz.service.SendDetailService;
import com.zrz.service.SysUserInfoService;
import com.zrz.util.Constans;
import com.zrz.util.CookieUtil;
import com.zrz.util.MemcachedUtils;
import com.zrz.util.ToolClass;
import com.zrz.util.UUIDUtil;
 
@Controller
@RequestMapping(value="")
public class IndexController {
 
	@Autowired
	private SysUserInfoService sysUserInfoService;
	@Autowired
	private SendDetailService sendDetailService;
	@Autowired
	private PositionListService positionListService;
	
	
    @RequestMapping(value={"","/"})
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response){
    	
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/main/main");
        
        return mav;
    }
    
    @RequestMapping(value={"/getSendDetailList"} , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,List<SendDetailPO>> getSendDetailList(HttpServletRequest request, HttpServletResponse response){
		
    	String userId = MemcachedUtils.getUserInfoPO(request).getId(); 
    	Map<String,List<SendDetailPO>> mapList = sendDetailService.getDetailByUserId(userId);
    	
		return mapList;
	}
    
    @RequestMapping(value={"/begin"}, method = RequestMethod.POST)
    @ResponseBody
    public String begin(HttpServletRequest request, HttpServletResponse response){
    	SysUserInfoPO sysUserInfoPO = sysUserInfoService.getById(MemcachedUtils.getUserInfoPO(request).getId());
    	sysUserInfoPO.setIsEnabled("1");
    	int num = sysUserInfoService.update(sysUserInfoPO);
    	if(num==1){
    		return Constans.SUCCESS;
    	}else{
    		return Constans.ERROR;
    	}
	}
	
	@RequestMapping(value={"/end"}, method = RequestMethod.POST)
    @ResponseBody
    public String end(HttpServletRequest request, HttpServletResponse response){
		SysUserInfoPO sysUserInfoPO = sysUserInfoService.getById(MemcachedUtils.getUserInfoPO(request).getId());
		sysUserInfoPO.setIsEnabled("0");
    	int num = sysUserInfoService.update(sysUserInfoPO);
    	if(num==1){
    		return Constans.SUCCESS;
    	}else{
    		return Constans.ERROR;
    	}
	}
	
	@RequestMapping(value={"/updateToken"} ,method = RequestMethod.POST)
    @ResponseBody
    public String updateToken(HttpServletRequest request, HttpServletResponse response){
		String tokenContent = request.getParameter("tokenContent");
		String token = "token="+tokenContent;
		
		if(StringUtils.isBlank(tokenContent)){
			return Constans.ERROR;
		}
		
		//校验时间戳是否有效
		boolean tokenCheck = Run.checkToken(token);
    	if(!tokenCheck){
    		return "token_invalid";
    	}
		
		String timestmp = "";
		try{
			//解析时间戳
			String[] splitArgs = tokenContent.split("\\|");
			//10:1526976475
			timestmp = splitArgs[2].split(":")[1];
		}catch(Exception e){
			e.printStackTrace();
			return "token_invalid";
		}
		
		
		
		SysUserInfoPO sysUserInfoPO = sysUserInfoService.getById(MemcachedUtils.getUserInfoPO(request).getId());
    	sysUserInfoPO.setToken(token);
    	sysUserInfoPO.setTokenUpdateTime(Integer.valueOf(timestmp));
    	sysUserInfoPO.setStatus("1");//设为有效
    	
    	
    	int num = sysUserInfoService.update(sysUserInfoPO);
    	if(num==1){
    		return Constans.SUCCESS;
    	}else{
    		return Constans.ERROR;
    	}
	}
	
	@RequestMapping(value={"/now"} ,method = RequestMethod.POST)
    @ResponseBody
    public String now(HttpServletRequest request, HttpServletResponse response){
		SysUserInfoPO sysUserInfoPO = sysUserInfoService.getById(MemcachedUtils.getUserInfoPO(request).getId());
    	
		List<SysUserInfoPO> list = new ArrayList<SysUserInfoPO>();
		list.add(sysUserInfoPO);
    	boolean isSuccess = false;
		try {
			isSuccess = Run.httpsPost(list);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	if(isSuccess){
    		return Constans.SUCCESS;
    	}else{
    		return Constans.ERROR;
    	}
	}
	
	
    
    @RequestMapping(value={"/login"})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("/home/login");
    	return mav;
    }
    
    @RequestMapping(value={"/register"})
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("/home/register");
    	return mav;
    }
    
    
    @RequestMapping(value={"/loginCommit"} , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> loginCommit(HttpServletRequest request, HttpServletResponse response){
    	Map<String, String> map = new HashMap<String, String>();
    	//验证账号密码
    	String account = request.getParameter("account");
    	String password = request.getParameter("password");
    	if(StringUtils.isBlank(account)||StringUtils.isBlank(password)){
    		map.put("flag", Constans.ERROR);
    		map.put("message", "账号或密码不能为空！");
    		return map;
    	}
    	SysUserInfoPO sysUserInfoPO = sysUserInfoService.getByAccount(account); 
    	if(sysUserInfoPO==null){
    		map.put("flag", Constans.ERROR);
    		map.put("message", "账号不存在！");
    		return map;
    	}else{
    		if(!password.equals(sysUserInfoPO.getPassword())){
    			map.put("flag", Constans.ERROR);
        		map.put("message", "账号或密码输入错误！");
        		return map;
    		}
    	}
    	
    	//生成cookie-tookenId
    	String tookenId = UUIDUtil.uuidRandom();
    	CookieUtil.addCookie(response, Constans.TOKEN_ID, tookenId, Integer.MAX_VALUE);
    	
    	//记录登录时间
    	sysUserInfoPO.setLastLogin(ToolClass.getTime());
    	
    	sysUserInfoService.updateSelective(sysUserInfoPO);
    	
    	map.put("flag", Constans.SUCCESS);
    	map.put("message", "登陆成功！");
    	
    	//去除敏感信息
    	sysUserInfoPO.setAccount("");
    	sysUserInfoPO.setPassword("");
    	//存储memcached
    	MemcachedUtils.saveUserInfoPO(tookenId, sysUserInfoPO);
    	MemcachedUtils.set(sysUserInfoPO.getId(), sysUserInfoPO ,new Date(0));
    	
    	return map;
    }
    
    
	@RequestMapping(value={"/registerCommit"} , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> registerCommit(HttpServletRequest request, HttpServletResponse response){
    	Map<String, String> map = new HashMap<String, String>();
    	//验证账号密码名称
    	String account = request.getParameter("account");
    	String password = request.getParameter("password");
    	String name = request.getParameter("name");
    	if(StringUtils.isBlank(account)||StringUtils.isBlank(password)||StringUtils.isBlank(name)){
    		map.put("flag", Constans.ERROR);
    		map.put("message", "账号密码或者名称不能为空！");
    		return map;
    	}
    	//验证是否有重复
    	SysUserInfoPO sysUserInfoPO = sysUserInfoService.getByAccount(account); 
    	if(sysUserInfoPO!=null){
    		map.put("flag", Constans.ERROR);
    		map.put("message", "账号或已存在！");
    		return map;
    	}
    	//注册成功
    	sysUserInfoPO = new SysUserInfoPO();//清空
    	String nowTime = ToolClass.getTime();
    	sysUserInfoPO.setId(UUIDUtil.uuidRandom());
    	sysUserInfoPO.setName(name);
    	sysUserInfoPO.setAccount(account);
    	sysUserInfoPO.setPassword(password);
    	sysUserInfoPO.setCreateTime(nowTime);
    	sysUserInfoPO.setLastLogin(nowTime);
    	sysUserInfoPO.setStatus("1");//默认有效
    	sysUserInfoPO.setSuccessTimes(0);
    	sysUserInfoPO.setIsEnabled("1");//默认自动开启
    	
    	
    	int rs = sysUserInfoService.save(sysUserInfoPO);
    	if(rs>0){
	    	map.put("flag", Constans.SUCCESS);
			map.put("message", "注册成功！");
    	}else{
    		map.put("flag", Constans.ERROR);
			map.put("message", "注册失败！");
    	}
    	
    	//生成cookie-tookenId
    	String tookenId = UUIDUtil.uuidRandom();
    	CookieUtil.addCookie(response, Constans.TOKEN_ID, tookenId, Integer.MAX_VALUE);
    	//存储memcached
    	MemcachedUtils.saveUserInfoPO(tookenId, sysUserInfoPO);
    
    	return map;
    }
    
	@RequestMapping(value={"/getPositionList"})
    @ResponseBody
    public List<PositionListPO> getPositionList(HttpServletRequest request, HttpServletResponse response){
		return positionListService.getAllList();
	}
	
	
	@RequestMapping(value={"/changePosition"})
    @ResponseBody
    public String changePosition(HttpServletRequest request, HttpServletResponse response){
		String positionId = request.getParameter("positionId");
		PositionListPO positionListPO = positionListService.getById(positionId);
		
		SysUserInfoPO sysUserInfoPO = sysUserInfoService.getById(MemcachedUtils.getUserInfoPO(request).getId());
		sysUserInfoPO.setPositionId(positionId);
		sysUserInfoPO.setPositionX(positionListPO.getPositionX());
		sysUserInfoPO.setPositionY(positionListPO.getPositionY());
		
		int flag = sysUserInfoService.updateSelective(sysUserInfoPO);
		if(flag==1){
    		return Constans.SUCCESS;
    	}else{
    		return Constans.ERROR;
    	}
	}

}