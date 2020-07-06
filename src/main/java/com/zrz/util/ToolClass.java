package com.zrz.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class ToolClass {

	/**
     * isNotEmpty
     * 等价于StringUtils.isNotEmpty
     * @param value
     * @return boolean
     */
    public static boolean isNotEmpty(String value)
    {
    	return null != value && !"".equals(value);
    }
    
    /**
     * 判断是否存在汉字
     * @param string
     * @return boolean
     */
    public static boolean isChineseChar(String str){
        boolean temp = false;
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
        Matcher m=p.matcher(str); 
        if(m.find()){ 
            temp =  true;
        }
        return temp;
    }
    
    
    /**
     * 获取当前时间 
     * @return string
     */
    public static String getTime(){
    	Date date=new Date();
    	DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time=format.format(date);
    	return time;
    }
    
    /**
     * 获取当前日期 EX:2018-03-15
     * @return string
     */
    public static String getDate(){
    	Date date = new Date();
    	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	String time=format.format(date);
    	return time;
    }
    
    /**
     * 获取当前日期 EX:20180315
     * @return string
     */
    public static String getDate2(){
    	Date date = new Date();
    	DateFormat format=new SimpleDateFormat("yyyyMMdd");
    	String time=format.format(date);
    	return time;
    }
    
    /**
     * 获取是否为法定节假日
     * @return double
     * =0 工作日，=1 休息日，=2 节假日，=9 错误
     */
    public static double isHoliday(){
    	
    	try{
	    	String url = "http://api.goseek.cn/Tools/holiday";
			String param = "date="+ToolClass.getDate2();
			String httpResult = HttpRequest.sendGet(url, param, "UTF-8", 5000);
			Gson gson = new Gson();
			Map<String,Double> map = new HashMap<String,Double>();
			map = gson.fromJson(httpResult, map.getClass());
			if(map.get("code")==10000){
				return map.get("data");
			}else{
				return 9;
			}
    	}catch(Exception e){
    		e.printStackTrace();
    		return 9;
    	}
    }
    
    /**
     * 获取当前时间Long 
     * @return Long
     */
    public static Long getTimeLong(){
    	Date date = new Date();
    	return date.getTime();
    }
    
    /**
     * 字符串转数字
     */
    public static int getInt(String str){
    	if(StringUtils.isBlank(str)){
    		return 0;
    	}else{
    		return Integer.parseInt(str);
    	}
    }
    
    /**
     * 字符串转大数字
     */
    public static BigDecimal getDecimal(String str){
    	if(StringUtils.isBlank(str)){
    		return new BigDecimal("0");
    	}else{
    		return new BigDecimal(str);
    	}
    }
    
	
	/**
	 * 两个string异或 
	 */
	public static String twoStringXor(String str1, String str2) {
		String result = "";
        byte strb1[] = str1.getBytes();
        byte strb2[] = str2.getBytes();
        byte strb3[] = new byte[strb1.length];
        for(int i=0;i<strb1.length;i++){
        	 int aa = strb1[i] ^ strb2[i];
        	 strb3[i] = (byte) aa;
        }
        result = new String(strb3);
        return result;
    }
	
	/**
	 * 判断string中是否含有非法字符
	 */
	public static boolean isConstantSQL(String str){
		boolean is = false;
		String[] args = {";","'","*","delete","drop","truncate","or","and","select","--"};
		String[] extend = {"store"};
		for(String a : args){
			if(str.toLowerCase().contains(a)){
				for(String e : extend){
					if(e.equals(str)){
						break;
					}else{
						is = true;
						break;
					}
				}
			}
		}
		return is;
	}
	
	
	/**
	 * string转double
	 */
	public static double getDouble(String str){
		if(StringUtils.isBlank(str)){
			return 0;
		}else{
			try{
				return Double.parseDouble(str);
			}catch(Exception e){
				return 0;
			}
		}
	}
	
	/**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
	 * 获取访问真实IP
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ToolClass.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (ToolClass.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
	
	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 * 
	 * @param nowTime 当前时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return boolean
	*/
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
			return true;
		}
	
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);
	
		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);
	
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
	
		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 * 
	 * @param nowDate 当前日期
	 * @param format 日期格式
	 * @param startTimeStr 开始时间str
	 * @param endTimeStr 结束时间str
	 * @return boolean
	 * @throws ParseException 
	*/
	public static boolean isEffectiveDate(
			Date nowDate, String format, String startTimeStr, String endTimeStr) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		String now = sf.format(nowDate);
		Date nowTime = new SimpleDateFormat(format).parse(now);
		Date startTime = new SimpleDateFormat(format).parse(startTimeStr);
		Date endTime = new SimpleDateFormat(format).parse(endTimeStr);
		
		return isEffectiveDate(nowTime, startTime, endTime);
	}
	
	public static void main(String[] args) throws ParseException {
		
		boolean a = isEffectiveDate(new Date(), "HH:mm:ss", "10:40:00", "17:40:00");
		System.out.println(a);
	}
}
