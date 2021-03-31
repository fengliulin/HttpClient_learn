package cc.chengheng.HttpClient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpClient连接池
 */
public class HttpClientConnectionPool {

    public static void main(String[] args) throws IOException {
        testPool();
    }

    private static void testPool() throws IOException {
        // 1、创建HttpClient连接管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        // 2、设置参数
        cm.setMaxTotal(200); // 设置最大连接数
        cm.setDefaultMaxPerRoute(20); // 设置每个主机的最大并发
        doGet(cm);
        doGet(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) throws IOException {
        // 3、获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        // 4、创建HttpGet对象
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        // 5、发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 6、获取数据
        if (response.getStatusLine().getStatusCode() == 200) {
            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(html);
        }

        // 7、关闭资源
        response.close();
        httpClient.close();
    }
}
