package persistance.documents;

import persistance.mediatek.DocumentPhysique;

public class Livre extends DocumentPhysique {
    
    public Livre(int id, String title, String author){
       super(id,title,author);
    }

     @Override
    public String getType(){
        return "LIVRE";
    }
}