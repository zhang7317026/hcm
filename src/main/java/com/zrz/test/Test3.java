package com.zrz.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import com.zrz.util.ToolClass;

public class Test3 {

	public static void main(String[] args) throws ParseException {

        //time1
  		Calendar calendar1 = Calendar.getInstance();
  		calendar1.set(Calendar.HOUR_OF_DAY, 15); // 控制时
  		calendar1.set(Calendar.MINUTE, 8); // 控制分
  		calendar1.set(Calendar.SECOND, 01); // 控制秒
  		final Date time1 = calendar1.getTime();
  		//time2
  		Calendar calendar2 = Calendar.getInstance();
  		calendar2.set(Calendar.HOUR_OF_DAY, 15); // 控制时
  		calendar2.set(Calendar.MINUTE, 9); // 控制分
  		calendar2.set(Calendar.SECOND, 02); // 控制秒
  		final Date time2 = calendar2.getTime();
      		
        if(ToolClass.belongCalendar(new Date(), time1, time2)){
        	System.out.println("true");
        }else{
        	System.out.println("false");
        }
	}
}
