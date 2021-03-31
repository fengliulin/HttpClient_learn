package cc.chengheng.Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Base64;

public class JsoupTest {
    public static void main(String[] args) throws IOException {
        testGetDocument();
//        testGetElement();
//        testGetElementAttribute();

    }

    public static void testGetElementAttribute() throws IOException {
        String resource = JsoupTest.class.getClassLoader().getResource("Test.html").getPath();
        String decode = URLDecoder.decode("%e7%88%ac%e8%99%ab%e5%ad%a6%e4%b9%a0", "UTF-8");

        String replace = resource.replace("%e7%88%ac%e8%99%ab%e5%ad%a6%e4%b9%a0", decode);
        Document doc = Jsoup.parse(new File(replace), "UTF-8");

        Element aClass = doc.getElementsByAttributeValue("class", "city_class").first();
        System.out.println(aClass.text());
    }


    public static void testGetElement() throws IOException {
        String resource = JsoupTest.class.getClassLoader().getResource("Test.html").getPath();
        String decode = URLDecoder.decode("%e7%88%ac%e8%99%ab%e5%ad%a6%e4%b9%a0", "UTF-8");

        String replace = resource.replace("%e7%88%ac%e8%99%ab%e5%ad%a6%e4%b9%a0", decode);
        Document doc = Jsoup.parse(new File(replace), "UTF-8");

        // 根据id获取元素getElementByid
        Element element = doc.getElementById("city_bj");
        String text = element.text();
        System.out.println(text);

        // 根据标签获取元素getElementsByTag
//        Elements elements = doc.getElementsByTag("title");
//        Element titleElement = elements.first();
//        String title = titleElement.text();
//        System.out.println(title);

        // 根据class获取元素getElementsByClass
        /*Element element1 = doc.getElementsByClass("city_class").last();
        System.out.println(element1.text());*/

        // 根据属性获取元素
//        String abc = doc.getElementsByAttribute("abc").first().text();
//        System.out.println(abc);
    }

    private static void testGetDocument() throws IOException {
        String resource = JsoupTest.class.getClassLoader().getResource("Test.html").getPath();
        String decode = URLDecoder.decode("%e7%88%ac%e8%99%ab%e5%ad%a6%e4%b9%a0", "UTF-8");

        String replace = resource.replace("%e7%88%ac%e8%99%ab%e5%ad%a6%e4%b9%a0", decode);



      /*  Document doc = Jsoup.connect("https://www.baidu.com").get();
        Document doc1 = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);*/

        Document parse = Jsoup.parse(new File(replace), "UTF-8");
        System.out.println(parse);


        /*Elements title = doc.getElementsByTag("title");
        Element first = title.first();
        String text = first.text();
        System.out.println(text);*/
    }
}
