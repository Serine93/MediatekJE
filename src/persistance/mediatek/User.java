package persistance.mediatek;

public abstract class User implements mediatek2022.Utilisateur{

    /**
     * Le login de l'utilisateur
     */
    private String login_user;

    /**
     * le mot de passe de l'utilisateur
     */
    private String pwd;

    /**
     * Les diverses informations supplémentaires de l'utilisateur
     */
    private Object[] data;  

    public User(String login_user, String pwd, Object[] data){
        this.login_user = login_user;
        this.pwd = pwd;
        this.data = data;
    }

    /**
     * Renvoie le nom de login de l'utilisateur
     * @return le login de l'utilisateur
     */
    @Override
    public String name() {
        return this.login_user;
    }

    /**
     * Permet de savoir si l'utilisateur est un bibliothécaire
     * @return true si l'utilisateur est un bibliothécaire, sinon false
     */
    @Override
    public abstract boolean isBibliothecaire();

    /**
     * Renvoie l'ensemble des informations supplémentaires de l'utilisateur
     * @return la liste des informations de l'utilisateur
     */
    @Override
    public Object[] data() {
        return this.data;
    }
}
