package persistance.baseSQL;

import mediatek2022.*;
import persistance.documents.*;
import persistance.utilisateurs.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase{

    private Connection con;

    public DataBase(){
        try{
            con = connexion();
        }
        catch (Exception e){
            System.out.println("ZAZZZZZAZAZAZAZAZAZAZAZAZAZAZAZAZAAZAZAZAZAZAZAZAZAZAZA");
            System.exit(1);
        }
    }

    /**
     * Prépare la connexion à la base de données afin d'éxécuter des requêtes SQL par la suite
     * @return la connexion à la base de données
     */
    private static Connection connexion() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String DB_username = "root";
        String DB_password = "";
        String DB_URL = "jdbc:mysql://localhost:3306/jee";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(DB_URL, DB_username, DB_password);
        return conn;
    }

    /**
     * Renvoie la liste de tous les documents disponibles
     * @return la liste de documents disponibles
     */
    public List<Document> tousLesDocumentsDisponibles() {
        ResultSet résultats = null;

        List<Document> documents = new ArrayList<>();

        String requete = "Select * FROM DOCUMENT WHERE disponible = ?";

        try {
            PreparedStatement docs = con.prepareStatement(requete);;

            docs.setInt(1, 1);

            résultats = docs.executeQuery();

            while(résultats.next()){
                String type = résultats.getString("type_doc");

                Document doc = null;

                switch (type){
                    case "DVD" :
                        doc = new Dvd(résultats.getInt("id_doc"), résultats.getString("title"), résultats.getString("author"));
                        break;

                    case "LIVRE" :
                        doc = new Livre(résultats.getInt("id_doc"), résultats.getString("title"), résultats.getString("author"));
                        break;

                    case "CD" :
                        doc = new Cd(résultats.getInt("id_doc"), résultats.getString("title"), résultats.getString("author"));
                        break;
                }

                documents.add(doc);

            }

            return documents;

        }
        catch (SQLException e) {
            return null;
        }

    }


    /**
     * Récupère le document correspondant au numDocument dans la BD et le renvoie
     * @param numDocument le numéro de document
     * @return Renvoie le document si existe dans la base de données, sinon renvoie null
     */
    public Document getDocument(int numDocument) {
        ResultSet résultats = null;

        String requete = "Select * FROM DOCUMENT WHERE id_doc = ?";

        try {
            PreparedStatement searchDoc = con.prepareStatement(requete);;

            searchDoc.setInt(1, numDocument);

            résultats = searchDoc.executeQuery();

            if (résultats.next()) {

                String type = résultats.getString("type_doc");

                switch (type){
                    case "DVD" :
                        return new Dvd(numDocument, résultats.getString("title"), résultats.getString("author"));

                    case "LIVRE" :
                        return new Livre(numDocument, résultats.getString("title"), résultats.getString("author"));

                    case "CD" :
                        return new Cd(numDocument, résultats.getString("title"), résultats.getString("author"));
                }

            }

            else
                return null;

        } catch (SQLException e) {
            return null;
        }

        return null;
    }

    /**
     * Ajoute un document dans la base de données
     * @param type le type de documents
     * @param args les différents attributs de ce document : son titre, son auteur
     */
    public void ajoutDocument(int type, Object... args) {
        String requete;

        String typeDoc = null;

        String titre = String.valueOf(args[0]);

        String auteur = String.valueOf(args[1]);

        switch (type) {

            case 1 : {
                typeDoc = "DVD";
                break;
            }
            case 2 : {
                typeDoc = "LIVRE";
                break;
            }
            case 3 : {
                typeDoc = "CD";
                break;
            }
        }

        requete = "INSERT INTO 'document' ('title', 'author', 'type_doc', 'disponible') VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ajoutDoc = con.prepareStatement(requete);
            ajoutDoc.setString(1,titre);
            ajoutDoc.setString(2,auteur);
            ajoutDoc.setString(3,typeDoc);
            ajoutDoc.setInt(4,1);
            ajoutDoc.executeUpdate();

            System.out.println("Ajout du document réussie");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du document");
        }
    }


    /**
     * Récupère l'utilisateur correspondant au login et mot de passe saisit dans la BD et le renvoie
     * @param login_user le login de l'utilisateur à rechercher
     * @param pwd le mot de passe de l'utilisateur à rechercher
     * @return l'utilisateur s'il existe dans la base de données, sinon renvoie null
     */
    public Utilisateur getUser(String login_user, String pwd) {
        ResultSet résultats = null;

        //String encryptedpassword = encryptedPassword(pwd);
        String requete = "SELECT * FROM User WHERE login_user = ? AND pwd = ?";

        try {

            PreparedStatement searchUser = con.prepareStatement(requete);

            searchUser.setString(1, login_user);
            searchUser.setString(2, pwd);

            résultats = searchUser.executeQuery();
            résultats.next();

            int isBibli = résultats.getInt("isBibliothecaire");

            return isBibli == 1 ? new Bibliothécaire(login_user, pwd, null) : new Abonnee(login_user, pwd, null);





        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Crypte le mot de passe passé en paramètre grâce au cryptage MD5 et le renvoies
     * @param password le mot de passe à encrypter
     * @return le mot de passe encrypté
     */
    /*private static String encryptedPassword(String password) {
        String encryptedpassword = null;
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return encryptedpassword;
    }*/

    /**
     * Permet la mise à jour de la base de données pour un retour de document
     * @param numDoc le document rendu
     */
    public void retour(int numDoc) {
        Document doc = getDocument(numDoc);

        String requete = "UPDATE Document SET disponible = ? WHERE id_doc = ?";

        try {
            PreparedStatement updateDoc = con.prepareStatement(requete);;

            updateDoc.setInt(1, 1);
            updateDoc.setInt(2, numDoc);

            updateDoc.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * Permet la mise à jour de la base de données pour un emprunt de document
     * @param numDoc le document emprunté
     */
    public void emprunt(int numDoc) throws Exception {
        try{
            Document doc = getDocument(numDoc);
            String requete = "UPDATE Document SET disponible = ? WHERE id_doc = ?";

            PreparedStatement updateDoc = con.prepareStatement(requete);;
            updateDoc.setInt(1, 0);
            updateDoc.setInt(2, numDoc);

            updateDoc.executeUpdate();
        } catch (Exception e){
            throw new Exception(e);
        }


        }


}
