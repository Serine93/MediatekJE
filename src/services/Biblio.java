package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class Biblio extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {   
        PrintWriter out = response.getWriter();
    	response.setContentType("text/html");

        String requete = request.getParameter("requete").equals("success") ? "La requête a bien été effectuée" : request.getParameter("requete").equals("fail") ? "La requête a échouée" : "";

        out.println("<html>");
    	out.println("<head>");
    	out.println("<title>BIBLIOTHECAIRE</title>");
    	out.println("</head>");
    	
    	out.println("<body bgcolor=\"white\">");

        out.println("<h1>Bienvenue " + Authentification.getUser().name() + " (statut : Bibliothécaire) !</h1>");

        out.println("<p>" + requete + "</p>");

        out.println("<br/>");

        out.println("<a href = 'TousDocuments' >Voir tous les documents disponibles</a>");

        out.println("<br/>");

        out.println("<a href = 'AjouterDocument' >Ajouter un document à la Médiathèque</a>");

        out.println("</body>");
        out.println("</html>");


    }

}

