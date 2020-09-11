package exceptions;

public class RequestWasNotAnswered extends Exception{
    public RequestWasNotAnswered(String message) {
        super(message);
    }
}
