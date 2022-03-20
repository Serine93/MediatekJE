package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {   

       /* boolean erreur = request.getParameter("erreur").equals("oui");

        String msgErreur = erreur ? "L'utilisateur ou le mot de passe est incorrect" : "";*/

		PrintWriter out = response.getWriter();
    	response.setContentType("text/html");
    	
		out.println("<head>");
    	out.println("<title>" + "Connexion" + "</title>");
    	out.println("</head>");
    	
    	out.println("<body bgcolor=\"white\">");

        out.println("<form method=\"GET\" action=\"Authentification\">");
        out.println("<label >Login:</label>");
        
        out.println("<br>");
        out.println("<input type=\"text\" name=\"login_user\">");
        out.println("<label >Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" name=\"pwd\">");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Connexion\">");

        out.println("</form> ");

        //out.println("<p color = \"red\">" + msgErreur + "</p>");
        
        out.println("</body>");
        out.println("</html>");




    }
}