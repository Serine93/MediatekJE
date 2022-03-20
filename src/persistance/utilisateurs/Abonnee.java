package persistance.utilisateurs;

import persistance.mediatek.User;

public class Abonnee extends User {

    public Abonnee(String name, String password, Object[] data){
        super(name, password, data);
    }


    @Override
    public boolean isBibliothecaire(){
        return false;
    }
}