package exceptions;

public class NoSetCookieException extends Exception {

    public NoSetCookieException() {
        super("In Request there is no Header 'Set-Cookie'.");
    }

}
