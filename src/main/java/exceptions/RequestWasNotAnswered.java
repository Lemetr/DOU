package exceptions;

public class RequestWasNotAnswered extends Exception{
    public RequestWasNotAnswered() {
        super("Jsoup.connect does not answered, Connection.Response == null");
    }
}
