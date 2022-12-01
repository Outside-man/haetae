/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 * http 请求工具
 *
 * @author MessiahJK
 * @version : HttpUtils.java 2018/11/19 21:02 MessiahJK
 */
public class HttpUtils {
    /**
     * 请求的字符集
     */
    private final static String charset = "utf-8";

    /**
     * 超时时长
     */
    private final static Integer connectTimeout = 2000;

    /**
     * socket 超时时长
     */
    private final static Integer socketTimeout = null;

    /**
     * Do GET request
     *
     * @param url
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static String doGet(String url) throws Exception {

        URL localURL = new URL(url);

        URLConnection connection = openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setRequestProperty("Accept-Charset", charset);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }

        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }

    /**
     * Do POST request
     *
     * @param url
     * @param parameterMap
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map parameterMap) throws Exception {

        /* Translate parameter map to parameter date string */
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (parameterMap.get(key) != null) {
                    value = (String) parameterMap.get(key);
                } else {
                    value = "";
                }

                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }

        URL localURL = new URL(url);

        URLConnection connection = openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", charset);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterBuffer.length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(parameterBuffer.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }

    /**
     * 开启代理的连接
     *
     * @param localURL
     * @param proxyHost
     * @param proxyPort
     * @return
     * @throws IOException
     */
    private static URLConnection openConnection(URL localURL, String proxyHost, Integer proxyPort) throws IOException {
        if (proxyHost == null || proxyPort == null) {
            throw new IllegalArgumentException("代理信息不能为空");
        }
        URLConnection connection;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        connection = localURL.openConnection(proxy);
        return connection;
    }

    /**
     * 开启无代理的连接
     *
     * @param localURL
     * @return
     * @throws IOException
     */
    private static URLConnection openConnection(URL localURL) throws IOException {
        URLConnection connection;
        connection = localURL.openConnection();
        return connection;
    }

    /**
     * Render request according setting
     *
     * @param connection
     */
    private void renderRequest(URLConnection connection) {

        if (connectTimeout != null) {
            connection.setConnectTimeout(connectTimeout);
        }

        if (socketTimeout != null) {
            connection.setReadTimeout(socketTimeout);
        }

    }

    /**
     *当要发送复杂json对象时 使用
     *
     */
    public static String doPostUseJson(String url, String json) {
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            BasicResponseHandler handler = new BasicResponseHandler();
            StringEntity entity = new StringEntity(json, "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            result = httpClient.execute(httpPost, handler);
            return result;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 请求接口返回字节输入流
     * @param url 链接
     * @param json 参数
     * @return 字节输入流
     */
    public static ByteArrayInputStream sendPost(String url, String json) {
        InputStream inputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-type", "application/json; charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        try {
            StringEntity s = new StringEntity(json, StandardCharsets.UTF_8);
            s.setContentEncoding("UTF-8");
            httppost.setEntity(s);
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                // 获取相应实体
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                // 创建一个Buffer字符串
                byte[] buffer = new byte[1024];
                // 每次读取的字符串长度，如果为-1，代表全部读取完毕
                int len = 0;
                // 使用一个输入流从buffer里把数据读取出来
                while ((len = inputStream.read(buffer)) != -1) {
                    // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                    outStream.write(buffer, 0, len);
                }
                // 关闭输入流
                inputStream.close();
                // 把outStream里的数据写入内存
                byteArrayInputStream = new ByteArrayInputStream(outStream.toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayInputStream;
    }
    
}
