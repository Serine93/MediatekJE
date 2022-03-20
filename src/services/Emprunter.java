package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import mediatek2022.*;

public class Emprunter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {   

		Utilisateur user = Authentification.getUser();
		String id_doc = "";
		id_doc = request.getParameter("id_doc"); 

		//si c'est la première fois que l'utilisateur arrive sur cette page
		if (id_doc.equals("")){
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<head>");
			out.println("<title>" + "Emprunter" + "</title>");
			out.println("</head>");
			
			out.println("<body bgcolor=\"white\">");

			out.println("<form method=\"GET\" action=\"Emprunter\">");
			out.println("<label >Numéro du document à emprunter:</label>");
			
			out.println("<br>");
			out.println("<input type=\"number\" name=\"id_doc\">");
			out.println("<br>");
			out.println("<input type=\"submit\" value=\"Emprunter\">");

			out.println("</form> ");

			out.println("<a href = 'Accueil' >Vers l'accueil</a>");
			
			out.println("</body>");
			out.println("</html>");
		}

		//l'utilisateur a saisit un chiffre
		else {
			Document doc = mediatek2022.Mediatheque.getInstance().getDocument(Integer.parseInt(id_doc));

			if (doc == null)
				response.sendRedirect("Emprunter");
			
			try{
				doc.emprunt(user);
				response.sendRedirect("Accueil?requete=success");
			} catch (Exception e){
				response.sendRedirect("Accueil?requete=fail");
			}
			
		}

		

    }
}