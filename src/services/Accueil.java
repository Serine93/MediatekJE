package services;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import mediatek2022.*;

public class Accueil extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {   
            	
        Utilisateur current_user = Authentification.getUser();

        String requete = request.getParameter("requete");

        if (current_user.isBibliothecaire())
            response.sendRedirect("/Biblio?requete=" + requete);
        else
            response.sendRedirect("Abo?requete=" + requete);
    }

}

