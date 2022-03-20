package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class AjouterDocument extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {   

		String args[] = new String[2];
		String titre = "";
		String auteur = "";
		Integer type = -1;
		String[] types = new String[3];

		types[0] = request.getParameter("dvd");
		types[1] = request.getParameter("livre");
		types[2] = request.getParameter("cd");

		titre = request.getParameter("title"); 
		auteur = request.getParameter("author");
		
		for (int i = 0; i < types.length; ++i){
			if (types[i].length() > 1)
				type = i + 1;
		}

		boolean erreur = titre.equals("") || auteur.equals("") || type < 0;

		//première fois que l'utilisateur consulte le formulaire d'ajour de documents
		if (erreur){
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<head>");
			out.println("<title>" + "AjouterDocument" + "</title>");
			out.println("</head>");
			
			out.println("<body bgcolor=\"white\">");

			out.println("<form method=\"GET\" action=\"AjouterDocument\">");

			out.println("<label >Titre:</label>");
			out.println("<input type=\"text\" name=\"title\">");

			out.println("<label >Auteur:</label>");
			out.println("<input type=\"text\" name=\"author\">");
			out.println("<br>");
			out.println("<br>");

			out.println("<div>");
			out.println("<input type=\"radio\" name=\"dvd\">");
			out.println("<label>DVD</label>");
			out.println("</div>");

			out.println("<div>");
			out.println("<input type=\"radio\" name=\"livre\">");
			out.println("<label>Livre</label>");
			out.println("</div>");

			out.println("<div>");
			out.println("<input type=\"radio\" name=\"cd\">");
			out.println("<label>CD</label>");
			out.println("</div>");
			out.println("<br>");

			out.println("<input type=\"submit\" value=\"Ajouter\">");

			out.println("</form> ");

			out.println("<a href = 'Accueil' >Vers l'accueil</a>");
			
			out.println("</body>");
			out.println("</html>");

		}
		//toutes les informations sont renseignées
		else {
			args[0] = titre;
			args[1] = auteur;
			mediatek2022.Mediatheque.getInstance().ajoutDocument(type, (Object) args);

			response.sendRedirect("Accueil?requete=success");
		}
		
    }
}