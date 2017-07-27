package com.zjtravel.util.sms;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by hunger on 2017/1/8.
 */
public class SMS {
    static final String appKey = "b1f105e67d7434328ee302e4f8b6a4a6";
    static final String appSecret = "f8de57e537ee";

    public static String  create(String accid) {
        final String url = "https://api.netease.im/nimserver/user/create.action";
        final String nonce = String.valueOf((int)(Math.random()*100000));
        final String curTime = String.valueOf((new Date()).getTime() / 1000L);
        final String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);
        httpost.addHeader("AppKey", appKey);
        httpost.addHeader("Nonce", nonce);
        httpost.addHeader("CurTime", curTime);
        httpost.addHeader("CheckSum", checkSum);
        httpost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        List params = new ArrayList();
        params.add(new BasicNameValuePair("accid",  accid));

        return execute(httpclient, httpost, params);
    }


    public static String  sendCode(String mobile) {
        final String url = "https://api.netease.im/sms/sendcode.action";
        final String nonce = String.valueOf((int)Math.random()*100000);
        final String curTime = String.valueOf((new Date()).getTime() / 1000L);
        final String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);
        httpost.addHeader("AppKey", appKey);
        httpost.addHeader("Nonce", nonce);
        httpost.addHeader("CurTime", curTime);
        httpost.addHeader("CheckSum", checkSum);
        httpost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        List params = new ArrayList();
        params.add(new BasicNameValuePair("mobile",  mobile));
//        params.add(new BasicNameValuePair("templateid","1"));
        return execute(httpclient, httpost, params);
    }
    public static String  verify(String mobile,String code) {
        final String url = "https://api.netease.im/sms/verifycode.action";
        final String nonce = String.valueOf((int)Math.random()*100000);
        final String curTime = String.valueOf((new Date()).getTime() / 1000L);
        final String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);
        httpost.addHeader("AppKey", appKey);
        httpost.addHeader("Nonce", nonce);
        httpost.addHeader("CurTime", curTime);
        httpost.addHeader("CheckSum", checkSum);
        httpost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        List params = new ArrayList();
        params.add(new BasicNameValuePair("mobile",  mobile));
        params.add(new BasicNameValuePair("code",  code));
        return execute(httpclient, httpost, params);
    }
    public static String  getSataus(String sendid) {
        final String url = "https://api.netease.im/sms/querystatus.action";
        final String nonce = String.valueOf((int)Math.random()*100000);
        final String curTime = String.valueOf((new Date()).getTime() / 1000L);
        final String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);
        httpost.addHeader("AppKey", appKey);
        httpost.addHeader("Nonce", nonce);
        httpost.addHeader("CurTime", curTime);
        httpost.addHeader("CheckSum", checkSum);
        httpost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        List params = new ArrayList();
        params.add(new BasicNameValuePair("sendid",  sendid));
        return execute(httpclient, httpost, params);
    }

    private static String execute(CloseableHttpClient httpclient, HttpPost httpost, List params) {
        try {
            httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            // 请求
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
