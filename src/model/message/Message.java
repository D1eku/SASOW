package model.message;


import java.util.ArrayList;

public class Message {
    /*
    * Lo que se interesa es analizar la influencia para un unico mensaje
    * No para varios tipos de mensajes.
    * Se mide el impacto de un mensaje, su difusion, entre otros.
    * */

    private ArrayList<Emotion> emotions;
    private ArrayList<Reaction> reactionArrayList;
    private ArrayList<MetaInfoMessage> metaInfoMessageArrayList;
    private int reportsCount; //Seria basicamente un contador de los reportes.
    //El reportar no seria tambien una reaccion ?
    private Object impact;//Me quiero referir al impacto, de que esto se tiene que gestionar de una forma
    // y que tiene que haber una clara relacion con las reacciones al mensaje

    public Message(){
        emotions = new ArrayList<>();
    }

    public void addEmotion(String emotion){

    }

}
