

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.util.*;

@WebServlet("/PrintUser")
public class PrintUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
ArrayList<HashMap<String,Object>> users=(ArrayList<HashMap<String,Object>>) request.getAttribute("users");
		
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset='UTF-8'>");
		out.print("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		out.print("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM' crossorigin='anonymous'>");
		out.print("</head>");
		out.print("<body class='bg-light'>");
		out.print("<div class='container mt-5'>");
		out.print("<h1 class='display-4 text-center mb-4 text-primary'>User App</h1>");

		ServletContext ctx = request.getServletContext();
		Integer count = (Integer) ctx.getAttribute("count");

		out.print("<div class='d-flex justify-content-end mb-4'>");
		out.print("<p class='h5'>Search Count: <span class='badge bg-success'>" + count + "</span></p>");
		out.print("</div>");

		out.print("<div class='row'>");
		for (HashMap<String, Object> user : users) {
		    out.print("<div class='col-md-6 col-lg-4 mb-4'>");
		    out.print("<div class='card shadow-sm h-100'>");
		    out.print("<div class='card-body'>");
		    out.print("<h5 class='card-title text-danger'>" + user.get("name") + "</h5>");
		    out.print("<ul class='list-unstyled'>");
		    out.print("<li><strong>id:</strong> " + user.get("id") + "</li>");
		    out.print("<li><strong>Name:</strong> " + user.get("name") + "</li>");
		    out.print("<li><strong>Email:</strong> " + user.get("email") + "</li>");
		    out.print("<li><strong>Roll Number:</strong> " + user.get("roll") + "</li>");
		    out.print("<li><strong>Age:</strong> " + user.get("age") + "</li>");
		    out.print("<li><strong>Gender:</strong> " + user.get("gender") + "</li>");
		    out.print("<li><strong>Address:</strong> " + user.get("address") + "</li>");
		    
		    
		  /* ;
		
		
			
		
			p.executeUpdate();*/
		    
		    /**/
		    out.print("</ul>");
		    out.print("</div>"); // card-body
		    out.print("</div>"); // card
		    out.print("</div>"); // col
		}
		out.print("</div>"); // row
		out.print("</div>"); // container

		out.print("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js' integrity='sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz' crossorigin='anonymous'></script>");
		out.print("</body>");
		out.print("</html>");
		
			
	}

}
