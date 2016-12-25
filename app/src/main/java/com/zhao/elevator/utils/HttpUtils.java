package com.zhao.elevator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by zhao on 2016/12/8.
 */

public class HttpUtils {
    ///< 请求服务URL
    private static URL url;

    /*    public HttpUtils(String path) {
            PATH=path;
        }*/
    public HttpUtils() {

    }

    /**
     * 向服务端提交数据
     * @param params    url参数
     * @param encode    字节编码
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String doPostAsyn(String PATH, Map<String, String> params, String encode){
        ///< 初始化URL
        StringBuffer buffer = new StringBuffer();
//            StringBuffer buffer = new StringBuffer();

        if (null != params && !params.isEmpty()){
            for (Map.Entry<String, String> entry : params.entrySet()){
                buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue())).append("&");
            }
            ///< 删除多余的&
            buffer.deleteCharAt(buffer.length() - 1);
        }

        ///< show url
        //System.out.println(buffer.toString());

        try {
            url = new URL(PATH);
            if (null != url)
            {
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (null == urlConnection)
                {
                    return null;
                }
                urlConnection.setConnectTimeout(3000);
                urlConnection.setRequestMethod("POST");    ///< 设置请求方式
                urlConnection.setDoInput(true);            ///< 表示从服务器获取数据
                urlConnection.setDoOutput(true);        ///< 表示向服务器发送数据

                byte[] data = buffer.toString().getBytes();
                ///< 设置请求体的是文本类型
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
                ///< 获得输出流
                OutputStream outputStream = urlConnection.getOutputStream();
                outputStream.write(data);
                outputStream.close();
                ///< 获得服务器的响应结果和状态码
                int responseCode = urlConnection.getResponseCode();
                //System.out.println("" + responseCode);
                if (200 == responseCode)
                {
                    return changeInputStream(urlConnection.getInputStream());
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    public static String doGetAsyn(String PATH){
        try {
            url = new URL(PATH);
            if (null != url)
            {
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (null == urlConnection)
                {
                    return null;
                }
                urlConnection.setConnectTimeout(3000);
                urlConnection.setRequestMethod("GET");    ///< 设置请求方式
                urlConnection.setDoInput(true);            ///< 表示从服务器获取数据

                ///< 设置请求体的是文本类型
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                ///< 获得服务器的响应结果和状态码
                int responseCode = urlConnection.getResponseCode();
                if (200 == responseCode)
                {
                    return changeInputStream(urlConnection.getInputStream());
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获得网络返回值【0 - 正确    1 - 用户名错误    2 - 密码错误】
     * @param inputStream
     * @return
     */

    private static String changeInputStream(InputStream inputStream) {
        // TODO Auto-generated method stub
        BufferedReader bfr=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb=new StringBuilder();
        String read="";
        try {
            while ((read=bfr.readLine())!=null){
                sb.append(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  sb.toString();
    }
}