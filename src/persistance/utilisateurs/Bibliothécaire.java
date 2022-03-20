package persistance.utilisateurs;

import persistance.mediatek.User;

public class Bibliothécaire extends User {

    public Bibliothécaire(String login_user, String password, Object[] data){
        super(login_user, password, data);
    }


    @Override
    public boolean isBibliothecaire(){
        return true;
    }
}