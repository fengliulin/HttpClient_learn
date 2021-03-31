package cc.chengheng.HttpClient;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 超时设置，添加代理
 */
public class ConfigAndProxy {
    public static void main(String[] args) throws IOException {
        testConfig();
    }

    private static void testConfig() throws IOException {
        // 1、创建请求配置对象
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000) // 设置连接超时时间
                .setConnectTimeout(10000) // 设置创建连接超时时间
                .setConnectionRequestTimeout(10000) // 设置请求超时时间
                .setProxy(new HttpHost("113.254.178.224", 8383))
                .build();

        // 1、创建HttpClient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        // 2、创建HttpGet对象
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        // 3、发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 4、获取响应数据
        if (response.getStatusLine().getStatusCode() == 200) {
            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(html);
        }

        // 5、关闭资源
        response.close();
        httpClient.close();
    }
}
