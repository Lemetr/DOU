package connection;

public interface RequestResponse {

    default void init(){
        initRequest();
    }
    String initResponse(int i);
    void initRequest();
}
