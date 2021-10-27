package model.util.data;

public abstract class Handler {

    protected String prevData;
    protected Handler nextHandler;


    public  void setNext(Handler h){
        this.nextHandler = h;
    }

    public void logMessage( String data){
        if(nextHandler != null) {
            prevData = data;
        }
    }

    protected abstract String addData();
}
