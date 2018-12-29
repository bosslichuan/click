package com.zk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/root/ip.txt")));
            while (true) {
                try {
                    String line = br.readLine();
                    if (null == line) {
                        break;
                    }

                    System.setProperty("http.maxRedirects", "50");
                    System.getProperties().setProperty("proxySet", "true");
                    System.getProperties().setProperty("http.proxyHost", line.split(":")[0]);
                    System.getProperties().setProperty("http.proxyPort", line.split(":")[1]);
                    System.out.println(String.format("#########http.proxyHost:%s,http.proxyPort:%s", line.split(":")[0], line.split(":")[1]));
                    URL url = new URL("http://3q.jks088.com/index.html?id=bossli");
                    HttpURLConnection url_con = (HttpURLConnection) url.openConnection();
                    url_con.setConnectTimeout(3000);
                    url_con.setReadTimeout(3000);
                    url_con.setRequestMethod("GET");
                    url_con.setDoInput(true);
                    url_con.setDoOutput(true);
                    url_con.setRequestProperty("connection", "keep-alive");
                    url_con.setRequestProperty("Accept-Encoding", "gzip, deflate");
                    url_con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                    url_con.setRequestProperty("Host", "3q.jks088.com");
                    url_con.setRequestProperty("Upgrade-Insecure-Requests", "1");
                    url_con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
                    url_con.connect();
                    BufferedReader bb = new BufferedReader(new InputStreamReader(url_con.getInputStream(), "UTF-8"));
                    String tempLine = bb.readLine();
                    StringBuffer tempStr = new StringBuffer();
                    String crlf = System.getProperty("line.separator");
                    while (tempLine != null) {
                        tempStr.append(tempLine);
                        tempStr.append(crlf);
                        tempLine = bb.readLine();
                    }
                    System.out.println(tempStr.toString());
                    int nextInt = random.nextInt(4);
                    url_con.disconnect();
                    TimeUnit.SECONDS.sleep(nextInt);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
