package scrapers;

import constants.Constants;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsoupScrap {

    public static void main(String[] args) {
        try {
            new JsoupScrap().scrap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder scrap() throws IOException {

        Connection.Response get = Jsoup
                .connect(Constants.GET_URL)
                .userAgent(Constants.USER_AGENT)
                .referrer(Constants.REFERER)
                .method(Connection.Method.GET)
                .execute();

        String csrftoken = get.header("Set-Cookie").replaceAll(";.*", "");
        String token = Jsoup.parse(get.body()).select("input[name='csrfmiddlewaretoken']").val();

        Connection method = Jsoup
                .connect(Constants.POST_URL)
                .ignoreContentType(true)
                .header("User-Agent", Constants.USER_AGENT)
                .header("Referer", Constants.GET_URL)
                .header("Cookie", csrftoken)
                .header("Content-Type", Constants.CONTENT_TYPE)
//                .header("Content-Length", "42492")
                .header("Host", Constants.HOST)
                .data("count", "0")
                .data("csrfmiddlewaretoken", token)
                .method(Connection.Method.POST);
        Connection.Response execute = method.execute();

        Connection.Request request = method.request();
        System.out.println(request.multiHeaders().toString().getBytes(StandardCharsets.UTF_8).length * 8);

//        Document document = method.post();
//        System.out.println(document.body());

//        Connection.Response post = con.method(Connection.Method.POST).execute();
//        System.out.println(post.body());

//        Connection.Response execute = connect.method(Connection.Method.HEAD).execute();
//        Map<String, String> headers = execute.headers();

//        System.out.println("Content-Length: " + header);

//        Map<String, String> headers = get.headers();
//        for (Map.Entry<String, String> entry : headers.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }

//        Element element = document.select("div.more-btn > a[href]").first();
//        System.out.println(element.attr("href"));
//        Elements a = document.getElementsByClass("div.more-btn > a[href]");
        
        return new StringBuilder();
    }

}
