package cc.chengheng.HttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用HttpClient实现网络爬虫
 */
public class HttpClientGetPost {
    public static void main(String[] args) throws IOException {
//        testGet();
        testPost();
    }

    private static void testPost() throws IOException {
        // 1、创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2、创建HttpPost请求并进行相关设置
        HttpPost httpPost = new HttpPost("http://www.itcast.cn");

        //region 设置请求体/参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "java"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");// 准备集合用来存放请求参数
        httpPost.setEntity(entity);
        //endregion

        httpPost.setHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");

        // 3、发起请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 4、判断响应状态码并获取响应数据
        if (response.getStatusLine().getStatusCode() == 200) {
            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(html);
        }

        // 5、关闭资源
        httpClient.close();
        response.close();
    }

    private static void testGet() throws IOException {
        // 1、创建HttpClient对象
//        new DefaultHttpClient(); 过时
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2、创建HttpGet请求并进行相关设置
        HttpGet httpGet = new HttpGet("http://www.itcast.cn?username=java");
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");

        // 3、发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 4、判断响应状态码并获取响应数据
        if (response.getStatusLine().getStatusCode() == 200) {
            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(html);
        }

        // 5、关闭资源
        httpClient.close();
        response.close();
    }
}
