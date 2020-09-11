package connection;

import constants.Constants;
import exceptions.NoSetCookieException;
import exceptions.RequestWasNotAnswered;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.IOException;

public class ReqestResponseImpl implements RequestResponse {

    private String[] arr;

    public ReqestResponseImpl() {
        init();
    }

    @Override
    public String initResponse(int count) {
        String body = null;
        try {
             body = Jsoup.connect(Constants.POST_URL)
                    .ignoreContentType(true)
                    .header("Referer", Constants.GET_URL)
                    .header("Cookie", arr[0])
                    .data("count", String.valueOf(count))
                    .data("csrfmiddlewaretoken", arr[1])
                    .method(Connection.Method.POST)
                    .execute()
                    .charset("UTF-8")
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    @Override
    public void initRequest() {

        Connection.Response get = null;

        try {
            get = Jsoup
                    .connect(Constants.GET_URL)
                    .userAgent(Constants.USER_AGENT)
                    .referrer(Constants.REFERER)
                    .method(Connection.Method.GET)
                    .execute();
            if (get==null)
                throw new RequestWasNotAnswered("Jsoup.connect does not answered, Connection.Response == null");
        } catch (IOException | RequestWasNotAnswered e) {
            e.printStackTrace();
        }


        if (!get.hasHeader("Set-Cookie")) {
            try {
                throw new NoSetCookieException("In Request there is no Header 'Set-Cookie'.");
            } catch (NoSetCookieException e) {
                e.printStackTrace();
            }
        }

        arr = new String[2];
        arr[0] = get.header("Set-Cookie").replaceAll(";.*", "");
        arr[1] = Jsoup.parse(get.body()).select("input[name='csrfmiddlewaretoken']").val();
    }
}
