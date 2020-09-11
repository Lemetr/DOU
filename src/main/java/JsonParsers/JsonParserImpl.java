package JsonParsers;

import org.json.JSONObject;
import connection.ReqestResponseImpl;
import connection.RequestResponse;

public class JsonParserImpl implements JsonParser{

    @Override
    public StringBuilder parse() {

        RequestResponse rr = new ReqestResponseImpl();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        boolean last = false;

        while (!last){

            String body = rr.initResponse(count);
            JSONObject obj = new JSONObject(body);
            sb.append(obj.getString("html"));
            count+=obj.getInt("num");
            last = obj.getBoolean("last");

        }
        return sb;
    }
}
