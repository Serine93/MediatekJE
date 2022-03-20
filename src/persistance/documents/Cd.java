package persistance.documents;

import persistance.mediatek.DocumentPhysique;

public class Cd extends DocumentPhysique {
    
    public Cd(int id, String title, String author){
       super(id,title,author);
    }

    @Override
    public String getType(){
        return "CD";
    }
} 