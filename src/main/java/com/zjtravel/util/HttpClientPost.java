package com.zjtravel.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * 模拟客户端向服务端发送请求
 * Created by hunger on 2017/1/8.
 */
public class HttpClientPost {

    public static final Integer SMS_CREATE = 1;//新建云信用户
    public static final Integer SMS_SEND = 2;//发送短信验证
    public static final Integer SMS_CHECK = 3;//校验验证码
    public static final Integer SMS_STATUS = 4;//判断短信发送状态
    //阻止创建对象
    private HttpClientPost(){}
    //执行相应的post请求
    public static String execute(Integer type,Map<String,String> parameterss) {
        final String url;
        final Map<String,String> headers;
        final Map<String,String> parameters;
            switch (type) {
                case 1 :
                    url = "https://api.netease.im/nimserver/user/create.action";
                    headers = null;
                    parameters = null;
                    doExecution(url,headers,parameters);
                    System.out.println();break;
                case 2 :
                    url = "https://api.netease.im/nimserver/user/create.action";
                    System.out.println();break;
                case 3 :
                    url = "https://api.netease.im/nimserver/user/create.action";
                    System.out.println();break;
                case 4 :
                    url = "https://api.netease.im/nimserver/user/create.action";
                    System.out.println();break;
                default :
                    System.out.println();
            }
        return null;
    }

    /**
     * 执行访问
     * @param url 访问的地址
     * @param headers 请求头信息
     * @param parameters 请求参数
     * @return JSON字符串
     */
    private static String doExecution(String url, Map<String,String> headers, Map<String,String> parameters){
        {
            //建立客户端对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //新建post请求
            HttpPost httpost = new HttpPost(url);
            //设置头信息
            if(null!=headers) {
                for (String header : headers.keySet()) {
                    httpost.addHeader(header,headers.get(header));
                }
            }
            //设置参数信息
            List params = new ArrayList();
            if (null != parameters) {
                for (String parameter : parameters.keySet()) {
                    params.add(new BasicNameValuePair(parameter, parameters.get(parameter)));
                }
            }
            try {
                httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                HttpResponse response = httpclient.execute(httpost);
                // 处理响应
                return EntityUtils.toString(response.getEntity(), "utf-8");
            }catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                httpost.releaseConnection();
            }
            return null;
        }
    }

}
