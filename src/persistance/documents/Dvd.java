package persistance.documents;

import persistance.mediatek.DocumentPhysique;

public class Dvd extends DocumentPhysique {
    
    public Dvd(int id, String title, String author){
       super(id,title,author);
    }

     @Override
    public String getType(){
        return "DVD";
    }
}