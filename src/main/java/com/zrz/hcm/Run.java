package com.zrz.hcm;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.zrz.entity.MailBean;
import com.zrz.entity.PositionListPO;
import com.zrz.entity.SendDetailPO;
import com.zrz.entity.SysUserInfoPO;
import com.zrz.service.PositionListService;
import com.zrz.service.SendDetailService;
import com.zrz.service.SysUserInfoService;
import com.zrz.util.HttpsRequest;
import com.zrz.util.MD5Utils;
import com.zrz.util.SendMail;
import com.zrz.util.SpringContextUtil;
import com.zrz.util.ToolClass;
import com.zrz.util.UUIDUtil;

@Component
public class Run {
	
	private static final Logger logger = LoggerFactory.getLogger(Run.class);

	
	/**
	 * 发送签到请求
	 */
	public static void runPost(final Date time1, final Date time2){
		
		final DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		//time1时间触发
		Timer timer1 = new Timer();
		timer1.scheduleAtFixedRate(new TimerTask() {
			public void run() {
//				//判断时间是否在时间段内
//				if(!ToolClass.belongCalendar(new Date(), time1, time2)){
//					return;
//				}
				
				
				logger.info("----("+ToolClass.getTime()+")---开始执行time("+format.format(time1)+")---");
				
				//获取所有的用户
				SysUserInfoService sysUserInfoService = SpringContextUtil.getBean("sysUserInfoService");
				List<SysUserInfoPO> list = sysUserInfoService.getAllEnabled();
				if(list==null||list.size()==0){
					return;
				}
				//计算time1-time2之间的秒数
				Long second = Math.abs((time2.getTime() - time1.getTime())/1000);
				//根据用户数量计算间隔区间
				Long secondMax = second/list.size();
				Long secondMin = second/list.size()/2;
				Random rand = new Random();
				
				//循环打
				int size = list.size();
				for(int i=0;i<size;i++){
					//secondMin-secondMax之间的随机数
					long randNum =  rand.nextInt(secondMin.intValue()) + secondMin;
					try {
						logger.info("-------------sleep("+randNum+")");
						
						//等待
						Thread.sleep(randNum*1000);
						
						//随机一个用户
						SysUserInfoPO po;
						if(list.size()>1){
							int randIndex =  rand.nextInt(list.size()-1) + 0;
							po = list.get(randIndex);
							list.remove(randIndex);
						}else{
							po = list.get(0);
						}
						
						logger.info("-------------po.Account("+po.getAccount()+")");
						
						//打
						boolean isWorkTime = false;
						try {
							isWorkTime = ToolClass.isEffectiveDate(
									new Date(), "HH:mm:ss", "08:30:00", "17:30:00");
						} catch (ParseException e) {
							e.printStackTrace();
						}
						if(!isWorkTime){
							List<SysUserInfoPO> listOne = new ArrayList<SysUserInfoPO>();
							listOne.add(po);
							httpsPost(listOne);
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
					
					
				}
				
				
//				Timer timer2 = new Timer();
//				//time1-time2内每秒执行一次
//				timer2.schedule(new TimerTask() {
//					//计算time1-time2之间的秒数
//					private Long second = Math.abs((time2.getTime() - time1.getTime())/1000);
//					private int num = 0;//累计次数
//					public void run() {
//						
//						Random rand = new Random();
//						//1-second之间的随机数
//						int randNum =  rand.nextInt(second.intValue()) + 1;
//						//抽中1或者second内都没中则执行
//						if(randNum==1||num>=second.intValue()){
//							try {
//								SysUserInfoService sysUserInfoService = SpringContextUtil.getBean("sysUserInfoService");
//								List<SysUserInfoPO> list = sysUserInfoService.getAllEnabled();
//								httpsPost(list);
//							} catch (NoSuchAlgorithmException e) {
//								e.printStackTrace();
//							} catch (UnsupportedEncodingException e) {
//								e.printStackTrace();
//							}
//							
//							logger.info("----------已执行post发送----"+ToolClass.getTime()+"------");
//				            System.gc();
//				            cancel();
//						}
//						//次数累计
//						num++;
//					}
//				}, 0, 1000);
			}
		}, time1, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
		
		
	}
	
	
	/**
	 * 校验token
	 */
	public static boolean checkToken(String token){
		String url = "https://inspur.hcmcloud.cn/api/auth/get_auth?app_type=service&v="
				+ToolClass.getTimeLong();
		
		String rs = HttpsRequest.doPost(url, "", "utf-8" ,token);
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String,Object>();
		map = gson.fromJson(rs, map.getClass());
		//获取employee内容
		if(map.get("employee")==null||"null".equalsIgnoreCase((map.get("employee")).toString())){
			//请求失效，更新用户状态为失效警告
			return false;
		}
		return true;
	}
	
	
	/**
	 * 发送心跳
	 */
	public static void runHeartbeat(){

		//每50秒请求一次
		Timer timer2 = new Timer();
		timer2.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				String url = "https://inspur.hcmcloud.cn/api/auth/get_auth?app_type=service&v="
						+ToolClass.getTimeLong();
				//获取all list
				
				SysUserInfoService sysUserInfoService = SpringContextUtil.getBean("sysUserInfoService");
				List<SysUserInfoPO> list = sysUserInfoService.getAllEnabled();
				for (SysUserInfoPO po : list) {  
					try {
						String rs = HttpsRequest.doPost(url, "", "utf-8" ,po.getToken());
						
						Gson gson = new Gson();
						Map<String,Object> map = new HashMap<String,Object>();
						map = gson.fromJson(rs, map.getClass());
						//获取employee内容
						if(map.get("employee")==null||"null".equalsIgnoreCase((map.get("employee")).toString())){
							//请求失效，更新用户状态为失效警告
							po.setStatus("9");
							sysUserInfoService.updateSelective(po);
							
							
							
							//登录失效
							logger.info("请求已发送----"+ToolClass.getTime()+"---登录失效!!!!!!!!!!");
							//拼装邮件内容
							MailBean mb = new MailBean();
					        mb.setHost("smtp.yimingkeji123.com");// 设置SMTP主机(163)，若用126，则设为：smtp.126.com
					        mb.setUsername("server@yimingkeji123.com");// 设置发件人邮箱的用户名
					        mb.setPassword("Zhang123");// 设置发件人邮箱的密码
					        mb.setFrom("server@yimingkeji123.com");// 设置发件人的邮箱
					        mb.setSubject("一鸣科技提醒您：HCM登录超时,发送日期:"+ToolClass.getTime());// 设置邮件的主题
					        mb.setContent("一鸣科技提醒您：HCM登录超时,账号为："+po.getAccount());
					        //发送
					        SendMail sm = new SendMail();
					        
					        //时间判断
					        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
					    
					    	Date now = df.parse(df.format(new Date()));
					    	Date beginTime1 = df.parse("9:00");
					    	Date endTime1 = df.parse("9:02");
					    	Date beginTime2 = df.parse("16:00");
					    	Date endTime2 = df.parse("16:02");
					        if(ToolClass.belongCalendar(now, beginTime1, endTime1)
					        		||ToolClass.belongCalendar(now, beginTime2, endTime2)){
						    	
//					        	mb.setTo(entry.getKey()+"@inspur.com");
					        	mb.setTo("793755638@qq.com");
						        boolean flag = sm.sendMail(mb);
						        //是否发送成功
						        if(flag){
						        	logger.info("------邮件已发送至"+mb.getTo()+"-----");
						        }else{
						        	logger.info("------邮件发送"+mb.getTo()+"失败！！！！-----");
						        }
						    }else{
						    	continue;
						    }
						}else{
							//登录正常
							logger.info("请求已发送----"+ToolClass.getTime()+"---登录正常---");
							//请求正常，更新用户状态为正常状态
							po.setStatus("1");
							sysUserInfoService.updateSelective(po);
						}
					} catch (Exception e) {
				        e.printStackTrace();
				    }
				}
			}
		}, 100, 50*1000);
		
	}

	public static boolean httpsPost(List<SysUserInfoPO> list) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		boolean isSuccess = false;
		
		//首先判断是否为法定节假日，不为节假日0时或者错误9，为1周六天、2法定节假日不执行
//		if(ToolClass.isHoliday()==1||ToolClass.isHoliday()==2){
//			return;
//		}
		
		SysUserInfoService sysUserInfoService = SpringContextUtil.getBean("sysUserInfoService");
		String url = "https://inspur.hcmcloud.cn/api/attend.signin.create"; 
		
		Gson gson = new Gson();
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		
		//固定参数
		map.put("type", 3);
		map.put("beacon", "");
		map.put("information", "{\"source\":\"both\",\"accuracy\":0}");
		
		//发送请求
		//获取all list
		for (SysUserInfoPO sysUserInfoPO : list) {
			
			try{
				//获取自己的位置
				PositionListService positionListService = SpringContextUtil.getBean("positionListService");
				PositionListPO positionListPO = positionListService.getById(sysUserInfoPO.getPositionId());
				
				//位置随机数
				Random rand = new Random();
				int xMax = positionListPO.getPositionXMax(), 
					xMin = positionListPO.getPositionXMin(), 
					yMax = positionListPO.getPositionYMax(), 
					yMin = positionListPO.getPositionYMin();
				int xR = rand.nextInt(Math.abs(xMax-xMin)) + xMin;
				int yR = rand.nextInt(Math.abs(yMax-yMin)) + yMin;
				//设置位置，X轴为南北，Y轴为东西，与实际相反
				String x = positionListPO.getPositionX()+""+xR;
				String y = positionListPO.getPositionY()+""+yR;
				map.put("latitude", x);
				map.put("longitude", y);
				map.put("location_id", positionListPO.getLocationId());
				
				//动态参数
				Long timestamp = new Date().getTime();
				String md5Before = map.get("location_id").toString() 
						+map.get("type").toString()
						+map.get("latitude").toString()
						+map.get("longitude").toString()
						+timestamp
						+"hcm cloud";
				map.put("timestamp", timestamp);
				map.put("hash",MD5Utils.lower32(md5Before));
				
				String param = gson.toJson(map);
				String rsString = HttpsRequest.doPost(url, param, "utf-8" ,sysUserInfoPO.getToken());
				//判断是否成功
				JSONObject jsonObj = new JSONObject(rsString);
				boolean flag = false;
				try {
					if("true".equals(jsonObj.getJSONObject("result").get("success").toString())){
						flag = true;
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					flag = false;
				}
	//			boolean flag=true;
				
				SendDetailPO sendDetailPO = new SendDetailPO();
				sendDetailPO.setId(UUIDUtil.uuidRandom());
				sendDetailPO.setSendTime(ToolClass.getTime());
				sendDetailPO.setUserId(sysUserInfoPO.getId());
				sendDetailPO.setStatus(flag+"");
				sendDetailPO.setPositionX(ToolClass.getDouble(x));
				sendDetailPO.setPositionY(ToolClass.getDouble(y));
				sendDetailPO.setPositionType(sysUserInfoPO.getPositionId());
				//记录到sendDetail表
				SendDetailService sendDetailService = SpringContextUtil.getBean("sendDetailService");
				sendDetailService.insert(sendDetailPO);
				if(flag){
					//更新成功次数
					sysUserInfoPO.setSuccessTimes(sysUserInfoPO.getSuccessTimes()+1);
					sysUserInfoService.update(sysUserInfoPO);
					
					isSuccess = true;
				}
				
				logger.info("---执行结果--目标名称("+sysUserInfoPO.getName()+") 成功标识("+flag+") 执行时间("+ToolClass.getTime()+")");
		
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());;
			}
		
		}
		return isSuccess;
	}
	
	
	
	public static void main(String[] args){
//		
//		try {
//			File f = new File("list.xml");
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			Document doc = builder.parse(f);
//			NodeList nl = doc.getElementsByTagName("VALUE");
//			for (int i = 0; i < nl.getLength(); i++) {
//				cookies.put(doc.getElementsByTagName("NAME").item(i).getFirstChild().getNodeValue()
//						,doc.getElementsByTagName("TOKEN").item(i).getFirstChild().getNodeValue());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		//time1
//		Calendar calendar1 = Calendar.getInstance();
//		calendar1.set(Calendar.HOUR_OF_DAY, 8); // 控制时
//		calendar1.set(Calendar.MINUTE, 05); // 控制分
//		calendar1.set(Calendar.SECOND, 01); // 控制秒
//		final Date time1 = calendar1.getTime();
//		//time2
//		Calendar calendar2 = Calendar.getInstance();
//		calendar2.set(Calendar.HOUR_OF_DAY, 8); // 控制时
//		calendar2.set(Calendar.MINUTE, 15); // 控制分
//		calendar2.set(Calendar.SECOND, 02); // 控制秒
//		final Date time2 = calendar2.getTime();
//		//time3
//		Calendar calendar3 = Calendar.getInstance();
//		calendar3.set(Calendar.HOUR_OF_DAY, 18); // 控制时
//		calendar3.set(Calendar.MINUTE, 40); // 控制分
//		calendar3.set(Calendar.SECOND, 01); // 控制秒
//		final Date time3 = calendar3.getTime();
//		//time4
//		Calendar calendar4 = Calendar.getInstance();
//		calendar4.set(Calendar.HOUR_OF_DAY, 18); // 控制时
//		calendar4.set(Calendar.MINUTE, 50); // 控制分
//		calendar4.set(Calendar.SECOND, 02); // 控制秒
//		final Date time4 = calendar4.getTime();
//				
//		//发送心跳
//		runHeartbeat();
//		//发送请求
//		runPost(time1,time2);//早上
//		runPost(time3,time4);//下午
//		
	}
	
	
}


