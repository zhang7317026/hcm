package com.zrz.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public class Test2 {
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String url = "https://inspur.hcmcloud.cn/api/auth/get_auth"; 
        String cookie = "token=\"2|1:0|10:1529048767|5:token|56:NGYwZWI4OWUyNjYzMjI3YjdiYTQ5NmUyNzQ0NGUxMTc1YTdkYzI1Mw==|4aab6a05a337f46a6d9bc32323ce80c9c61005189665030548d8816f41cb6b17\"";
		
        String rs = sendGet(url, "app_type=service&v=1532913233242", "UTF-8", 5000, cookie);

		System.out.println(rs);
		
	}

	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param ,String codeType ,int outTime, String cookie) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            //设置超时时间
            connection.setReadTimeout(outTime);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Cookie",cookie);
            
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),codeType));//设置编码
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            //异常后返回TIMEOUT
            return "TIMEOUT";
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
}