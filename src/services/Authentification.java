package services;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import mediatek2022.*;

public class Authentification extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Utilisateur user;

	private static HttpSession session;

	public static Utilisateur getUser(){
		return user;
	}

	public static void deleteUser(){
		user = null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {   

    	response.setContentType("text/html");
    	
    	session = request.getSession(true);
		// on récupère les paramètres de la requète http
    	String login_user = request.getParameter("login_user"); 
       	String pwd = request.getParameter("pwd");
		
		// on crée un User qu'on met en variable dans la session   
        user = mediatek2022.Mediatheque.getInstance().getUser(login_user, pwd);
        if (user != null) {
            session.setAttribute("user", user);
			response.sendRedirect("Accueil");
        }else{
            response.sendRedirect("Connexion");
        }

		


    }

}
