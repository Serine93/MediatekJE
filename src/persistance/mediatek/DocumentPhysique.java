package persistance.mediatek;

import persistance.baseSQL.DataBase;
import mediatek2022.Utilisateur;

public abstract class DocumentPhysique implements mediatek2022.Document{

    /**
     * L'identifiant du document
     */
    private int id;

    /**
     * Le titre du document
     */
    private String title;

    /**
     * L'auteur du document
     */
    private String author;

    /**
     * La disponibilité du document dans la Médiathèque
     */
    private boolean disponible;

    /**
     * L'utilisateur qui utilise le document
     */
    private Utilisateur user;

    /**
     * L'accès à la bd
     */
    private DataBase db;

    public DocumentPhysique(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
        this.disponible = true;
        this.user = null;
        this.db = new DataBase();
    }

    /**
     * Permet de savoir si le document est disponible
     * @return true si le document est disponible, sinon false
     */
    @Override
    public boolean disponible() {
        return this.disponible;
    }

    /**
     * Permet l'emprunt du document par un utilisateur
     * @param u l'utilisateur empruntant le document
     */
    @Override
    public void emprunt(Utilisateur u) throws Exception {
        synchronized(this.db) {
            if (disponible) {
                this.user = u;
                this.disponible = false;
                this.db.emprunt(this.id);
            }
            else {
                throw new Exception();
            }
        }

    }

    /**
     * Permet le retour du document à la Médiathèque
     */
    @Override
    public void retour() {
        synchronized(this.db){
            this.user = null;
            this.disponible = true;
            this.db.retour(this.id);
        }
    }

    public abstract String getType();

    @Override
    public String toString(){
        return this.title + ":" + this.author + ":" + this.getType();
    }

}
