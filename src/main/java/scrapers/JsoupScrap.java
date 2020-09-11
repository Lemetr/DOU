package scrapers;

import JsonParsers.JsonParserImpl;
import constants.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupScrap {

    public static String getScrap(){

        StringBuilder sb = new StringBuilder();
        Document document = Jsoup.parse(new JsonParserImpl().parse().toString());
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
}
