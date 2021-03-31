package cc.chengheng;

import org.apache.commons.lang3.builder.ToStringExclude;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * jdk自带的API实现网络爬虫
 */
public class JDKURLConnection {
    public static void main(String[] args) throws IOException {
//        testGet();
        testPost();
        URL url = JDKURLConnection.class.getClassLoader().getResource("");
        System.out.println(url); // result: file:/Users/fengliulin/IdeaProjects/java%e7%88%ac%e8%99%ab%e5%ad%a6%e4%b9%a0/target/classes/

        URL url1 = JDKURLConnection.class.getClassLoader().getResource("cc/chengheng/JDKURLConnection.class");
        System.out.println(url1); // result: file:/Users/fengliulin/IdeaProjects/java%e7%88%ac%e8%99%ab%e5%ad%a6%e4%b9%a0/target/classes/cc/chengheng/JDKURLConnection.class

        String url2 = JDKURLConnection.class.getClassLoader().getResource("cc/chengheng/JDKURLConnection.class").getPath();
        System.out.println(url2); // result: /Users/fengliulin/IdeaProjects/java%e7%88%ac%e8%99%ab%e5%ad%a6%e4%b9%a0/target/classes/cc/chengheng/JDKURLConnection.class
    }

    private static void testPost() throws IOException {
        // 1、确定访问/爬去的url
        URL url = new URL("http://www.itcast.cn");

        // 2、获取连接对象
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        // 3、设置连接信息，请求方式/请求参数/请求头
        urlConnection.setDoOutput(true); // 允许向url输出内容
        urlConnection.setRequestMethod("POST"); // 这里大写
        urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        urlConnection.setConnectTimeout(30000);

        // POST请求，添加请求体信息
        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write("username=xx".getBytes(StandardCharsets.UTF_8));

        // 4、获取数据
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String html = "";
        while ((line = bufferedReader.readLine()) != null) {
            html += line + "\n";
        }

        System.out.println(html);

        bufferedReader.close();
        inputStream.close();
    }

    private static void testGet() throws IOException {
        // 1、确定访问/爬去的url
        URL url = new URL("http://www.itcast.cn");

        // 2、获取连接对象
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        // 3、设置连接信息，请求方式/请求参数/请求头
        urlConnection.setRequestMethod("GET"); // 这里大写
        urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        urlConnection.setConnectTimeout(30000); // 设置超时时间

        // 4、获取数据
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String html = "";
        while ((line = bufferedReader.readLine()) != null) {
            html += line + "\n";
        }

        System.out.println(html);

        bufferedReader.close();
        inputStream.close();
    }
}
