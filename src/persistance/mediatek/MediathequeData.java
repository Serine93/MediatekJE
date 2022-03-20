package persistance.mediatek;


import java.util.List;

import persistance.baseSQL.DataBase;
import mediatek2022.*;

/**
 * classe mono-instance  dont l'unique instance est connue de la médiatheque
 * via une auto-déclaration dans son bloc static
 */
public class MediathequeData implements PersistentMediatheque {

	/**
	 * La base de données 
	 */
	private DataBase db;

	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {
		db = new DataBase();
	}

	/**
     * Renvoie la liste de tous les documents disponibles
     * @return la liste de documents disponibles
     */
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		synchronized(this.db){
			return db.tousLesDocumentsDisponibles();
		}
	}

	/**
     * Récupère l'utilisateur correspondant au login et mot de passe saisit dans la BD et le renvoie
     * @param login_user le login de l'utilisateur à rechercher
     * @param pwd le mot de passe de l'utilisateur à rechercher
     * @return l'utilisateur s'il existe dans la base de données, sinon renvoie null
     */
	@Override
	public Utilisateur getUser(String login, String password) {
		synchronized(this.db){
			return db.getUser(login, password);
		}
	}

	/**
     * Récupère le document correspondant au numDocument dans la BD et le renvoie
     * @param numDocument le numéro de document
     * @return Renvoie le document si existe dans la base de données, sinon renvoie null
     */
	@Override
	public Document getDocument(int numDocument) {
		synchronized(this.db){
			return db.getDocument(numDocument);
		}
	}

	/**
     * Ajoute un document dans la base de données
     * @param type le type de documents
     * @param args les différents attributs de ce document : son titre, son auteur
     */
	@Override
	public void ajoutDocument(int type, Object... args) {
		synchronized(this.db){
			db.ajoutDocument(type, args);
		}
	}

}
