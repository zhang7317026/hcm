package com.zrz.hcm;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class StartupListener {

	public static String ShopNum;
	
	public static void beginRun() {

		/*---------------------------A.M------------------------------*/
		//timeAM1
//		Calendar calendarAM1 = Calendar.getInstance();
//		calendarAM1.set(Calendar.HOUR_OF_DAY, 7); // 控制时
//		calendarAM1.set(Calendar.MINUTE, 35); // 控制分
//		calendarAM1.set(Calendar.SECOND, 1); // 控制秒
//		final Date timeAM1 = calendarAM1.getTime();
		//timeAM2
//		Calendar calendarAM2 = Calendar.getInstance();
//		calendarAM2.set(Calendar.HOUR_OF_DAY, 8); // 控制时
//		calendarAM2.set(Calendar.MINUTE, 25); // 控制分
//		calendarAM2.set(Calendar.SECOND, 2); // 控制秒
//		final Date timeAM2 = calendarAM2.getTime();
		
		
		//timeAM3
		Calendar calendarAM3 = Calendar.getInstance();
		calendarAM3.set(Calendar.HOUR_OF_DAY, 8); // 控制时
		calendarAM3.set(Calendar.MINUTE, 20); // 控制分
		calendarAM3.set(Calendar.SECOND, 1); // 控制秒
		final Date timeAM3 = calendarAM3.getTime();
		//timeAM4
		Calendar calendarAM4 = Calendar.getInstance();
		calendarAM4.set(Calendar.HOUR_OF_DAY, 8); // 控制时
		calendarAM4.set(Calendar.MINUTE, 28); // 控制分
		calendarAM4.set(Calendar.SECOND, 2); // 控制秒
		final Date timeAM4 = calendarAM4.getTime();
		
		
		
		/*---------------------------P.M------------------------------*/
				
		//timePM1
		Calendar calendarPM1 = Calendar.getInstance();
		calendarPM1.set(Calendar.HOUR_OF_DAY, 19); // 控制时
		calendarPM1.set(Calendar.MINUTE, 40); // 控制分
		calendarPM1.set(Calendar.SECOND, 0); // 控制秒
		final Date timePM1 = calendarPM1.getTime();
		//timePM2
		Calendar calendarPM2 = Calendar.getInstance();
		calendarPM2.set(Calendar.HOUR_OF_DAY, 19); // 控制时
		calendarPM2.set(Calendar.MINUTE, 58); // 控制分
		calendarPM2.set(Calendar.SECOND, 2); // 控制秒
		final Date timePM2 = calendarPM2.getTime();
		
		
//		//timePM3
//		Calendar calendarPM3 = Calendar.getInstance();
//		calendarPM3.set(Calendar.HOUR_OF_DAY, 20); // 控制时
//		calendarPM3.set(Calendar.MINUTE, 50); // 控制分
//		calendarPM3.set(Calendar.SECOND, 0); // 控制秒
//		final Date timePM3 = calendarPM3.getTime();
//		//timePM4
//		Calendar calendarPM4 = Calendar.getInstance();
//		calendarPM4.set(Calendar.HOUR_OF_DAY, 21); // 控制时
//		calendarPM4.set(Calendar.MINUTE, 55); // 控制分
//		calendarPM4.set(Calendar.SECOND, 2); // 控制秒
//		final Date timePM4 = calendarPM4.getTime();
				
		
		
		//发送心跳
		Run.runHeartbeat();
		//发送请求
//		Run.runPost(timeAM1,timeAM2);//早上1
		Run.runPost(timeAM3,timeAM4);//早上2
		Run.runPost(timePM1,timePM2);//下午1
//		Run.runPost(timePM3,timePM4);//下午2
	}
	
}
