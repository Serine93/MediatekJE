package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import mediatek2022.*;
import java.util.List;


public class TousDocuments extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Document> tousLesDocuments = mediatek2022.Mediatheque.getInstance().tousLesDocumentsDisponibles();

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		out.println("<head>");
		out.println("<title>" + "TousDocuments" + "</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");

		out.println("<table border-collapse = \"collapse\">");
		out.println("<tbody>");
		out.println("<tr>");

		out.println("<td border= \"1px solid black;\" \"padding= 10px;\">NOM</td>");
		out.println("<td border= \"1px solid black;\" \"padding= 10px;\">AUTEUR</td>");
		out.println("<td border= \"1px solid black;\" \"padding= 10px;\">TYPE</td>");

		out.println("</tr>");


		for (Document doc : tousLesDocuments){
	
			String[] data = doc.toString().split(":");

			out.println("<tr>");

			out.println("<td border= \"1px solid black;\" \"padding= 10px;\">" + data[0] + "</td>");
			out.println("<td border= \"1px solid black;\" \"padding= 10px;\">" + data[1] + "</td>");
			out.println("<td border= \"1px solid black;\" \"padding= 10px;\">" + data[2] + "</td>");

			out.println("</tr>");
		}

		out.println("</tbody>");
		out.println("</table>");

		out.println("<a href = 'Accueil' >Vers l'accueil</a>");

		out.println("</body>");
		out.println("</html>");


	}
}