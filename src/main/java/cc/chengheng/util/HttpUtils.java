package cc.chengheng.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * 封装HttpClient工具，方面爬去网页内容
 */
public abstract class HttpUtils {

    /** HttpClient连接池 */
    private static PoolingHttpClientConnectionManager cm;

    private static List<String> userAgentList;

    private static RequestConfig requestConfig;

    /**
     * 静态代码块会在类加载的时候执行
     */
    static {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);

        requestConfig = RequestConfig.custom()
                            .setSocketTimeout(10000)
                            .setConnectTimeout(10000)
                            .setConnectionRequestTimeout(10000)
                            .build();

        userAgentList = new ArrayList<>();
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
    }

    /**
     * GET请求获取响应体的内容
     *
     * @param url
     * @return
     */
    public static String getHtml(String url) {
        // 1、从连接池中获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        // 2、创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);

        // 3、设置请求头和请求配置对象
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent", userAgentList.get(new Random().nextInt(userAgentList.size())));

        // 4、发起请求,得到响应结果
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            // 5、获取响应内容
            if (response.getStatusLine().getStatusCode() == 200) {
                String html = "";
                if (response.getEntity() != null) {
                    html = EntityUtils.toString(response.getEntity(), "UTF-8");
                }

                return html;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(response).close();
                //httpClient.close(); // 这里的HttpClient是从连接池中获取的，不需要关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
