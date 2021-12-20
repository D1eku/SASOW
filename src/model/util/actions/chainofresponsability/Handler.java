package model.util.actions.chainofresponsability;

public interface Handler {
    void setNext(Handler h);
    void request(String request);
}
