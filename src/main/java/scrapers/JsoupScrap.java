package scrapers;

import constants.Constants;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class JsoupScrap {

    private String[] arr;
    private StringBuilder sb = new StringBuilder();

    public JsoupScrap() {
        try {
            initRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getScrap(){

        parse();

        StringBuilder sb = new StringBuilder();
        Document document = Jsoup.parse(this.sb.toString());
        Elements elements = document.select(Constants.VACANCIES);

        for (Element element : elements) {

            String position = element.select(Constants.POSITION).text();
            String company = element.select(Constants.COMPANY).text();
            String cities = element.select(Constants.CITIES).text();
            String description = element.select(Constants.DESCRIPTION).text();
            sb.append(position).append(";")
                    .append(company).append(";")
                    .append(cities).append(";")
                    .append(description).append("\n");
        }

        return sb.toString();
    }

    private void parse() {

        int count = 0;
        boolean last = false;

        while (!last){

            try {
                String body = initResponse(count);
                JSONObject obj = new JSONObject(body);
                sb.append(obj.getString("html"));
                count+=obj.getInt("num");
                last = obj.getBoolean("last");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String initResponse(int count) throws IOException {

        return Jsoup
                .connect(Constants.POST_URL)
                .ignoreContentType(true)
                .header("Referer", Constants.GET_URL)
                .header("Cookie", arr[0])
                .data("count", String.valueOf(count))
                .data("csrfmiddlewaretoken", arr[1])
                .method(Connection.Method.POST)
                .execute()
                .charset("UTF-8")
                .body();
    }

    private void initRequest() throws IOException {

        Connection.Response get = Jsoup
                    .connect(Constants.GET_URL)
                    .userAgent(Constants.USER_AGENT)
                    .referrer(Constants.REFERER)
                    .method(Connection.Method.GET)
                    .execute();

        arr = new String[2];
        arr[0] = get.header("Set-Cookie").replaceAll(";.*", "");
        arr[1] = Jsoup.parse(get.body()).select("input[name='csrfmiddlewaretoken']").val();

    }
}
