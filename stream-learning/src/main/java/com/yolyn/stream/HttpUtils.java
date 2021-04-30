//package com.yolyn.stream;
//
//
//
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.X509Certificate;
//
///**
// * @author Shawn Qian
// * @since 2020年02月11日
// */
//public class HttpUtils {
//
//    static {
//        try {
//            trustAllHttpsCertificates();
//            HttpsURLConnection.setDefaultHostnameVerifier(
//                    (urlHostName, session) -> true
//            );
//        } catch (Exception e) {
//        }
//    }
//
//    public static void main(String[] args) {
//        String url = "http://ec-daily.zhonganonline.com/zuul/egypt/refundAdmin/resendAddPolicyMsg";
//
//        String cookie = "";
//        String params = "{}";
//
//        String rsp = post(url, cookie, params);
//        System.out.println(rsp);
//    }
//
//    public static void download(String url, String fileName) throws IOException {
//        URLConnection rulConnection = new URL(url).openConnection();// 此处的urlConnection对象实际上是根据URL的
//        HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
//        httpUrlConnection.setDoOutput(true);
//        httpUrlConnection.setDoInput(true);
//        httpUrlConnection.setUseCaches(false);
//        httpUrlConnection.setRequestProperty(HttpHeaders.Names.ACCEPT_ENCODING, "gzip");
//
//        httpUrlConnection.setRequestMethod(HttpMethod.GET.getName());
//
//        httpUrlConnection.connect();
//        InputStream ins = httpUrlConnection.getInputStream();
//
//        try (FileOutputStream fs = new FileOutputStream(fileName)) {
//            byte[] buffer = new byte[1204];
//            int byteSum = 0;
//            int byteRead;
//            while ((byteRead = ins.read(buffer)) != -1) {
//                byteSum += byteRead;
//                System.out.println(byteSum);
//                fs.write(buffer, 0, byteRead);
//            }
//        }
//    }
//
//    public static String get(String url) {
//        return get(url, null);
//    }
//
//    public static String get(String url, String cookie) {
//        try {
//            return request(url, HttpMethod.GET, null, cookie, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String post(String url, String params) {
//        try {
//            return request(url, HttpMethod.POST, null, null, params);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String post(String url, String cookie, String params) {
//        try {
//            return request(url, HttpMethod.POST, null, cookie, params);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String post(String url, String contentType, String cookie, String params) {
//        try {
//            return request(url, HttpMethod.POST, contentType, cookie, params);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private static String request(String url, HttpMethod httpMethod, String contentType, String cookie, String params) throws IOException {
//        URLConnection rulConnection = new URL(url).openConnection();// 此处的urlConnection对象实际上是根据URL的
//        HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
//        httpUrlConnection.setDoOutput(true);
//        httpUrlConnection.setDoInput(true);
//        httpUrlConnection.setUseCaches(false);
//
////        httpUrlConnection.setRequestProperty(HttpHeaders.Names.ACCEPT_ENCODING, "gzip");
//
////        text/plain;charset=UTF-8
////        application/xml;charset=UTF-8
//        if(contentType == null) {
////            httpUrlConnection.setRequestProperty(HttpHeaders.Names.ACCEPT, "application/json;charset=UTF-8");
//            httpUrlConnection.setRequestProperty(HttpHeaders.Names.CONTENT_TYPE, "application/json;charset=UTF-8");
//        }else{
//            httpUrlConnection.setRequestProperty(HttpHeaders.Names.ACCEPT, contentType);
//            httpUrlConnection.setRequestProperty(HttpHeaders.Names.CONTENT_TYPE, contentType);
//        }
//
//        if(cookie != null) {
//            httpUrlConnection.setRequestProperty("Cookie", cookie);
//        }
////        httpUrlConnection.setRequestProperty(HttpHeaders.Names.AUTHORIZATION, cookie);
//        httpUrlConnection.setRequestMethod(httpMethod.getName());
//
//        if (HttpMethod.POST.equals(httpMethod) && StringUtils.isNotBlank(params)) {
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpUrlConnection.getOutputStream(), "UTF-8"));
//            writer.write(params);
//            writer.close();
//        }
//
//        httpUrlConnection.connect();
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
//        String line;
//        StringBuilder result = new StringBuilder();
//        while ((line = in.readLine()) != null) {
//            result.append(line);
//        }
////        System.out.println(result);
//        return result.toString();
//    }
//
//    private static void trustAllHttpsCertificates() throws NoSuchAlgorithmException, KeyManagementException {
//        TrustManager[] trustAllCerts = new TrustManager[1];
//        trustAllCerts[0] = new TrustAllManager();
//        SSLContext sc = SSLContext.getInstance("SSL");
//        sc.init(null, trustAllCerts, null);
//        HttpsURLConnection.setDefaultSSLSocketFactory(
//                sc.getSocketFactory());
//    }
//
//
//    private static class TrustAllManager implements X509TrustManager {
//        public X509Certificate[] getAcceptedIssuers() {
//            return null;
//        }
//
//        public void checkServerTrusted(X509Certificate[] certs, String authType) {
//        }
//
//        public void checkClientTrusted(X509Certificate[] certs, String authType) {
//        }
//    }
//}