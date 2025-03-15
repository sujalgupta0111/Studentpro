
import jakarta.servlet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import com.conn.conn;

@WebServlet("/SearchUser")
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext ctx = request.getServletContext();
		Integer count = (Integer) ctx.getAttribute("count");
		if (count == null) {
			count = 0;
		}
		
		

		HttpSession session = request.getSession(false);
		if (session == null)
			response.sendRedirect("loginfailed.html");
		else {
			String name = request.getParameter("nameformSearch");
			System.out.println(name);

			PrintWriter out = response.getWriter();
			// jdbc code
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c = conn.getConnection();
				String Query = "select * from stu where name like ?";
				PreparedStatement p = c.prepareStatement("select * from stu where name like ?");
				p.setString(1, "%" + name + "%");
				ResultSet rs = p.executeQuery();
				boolean flag = true;
				ArrayList<HashMap<String, Object>> users = new ArrayList<>();
				
				while (rs.next()) {
					flag = false;
					HashMap<String, Object> u = new HashMap<>();
					u.put("id", rs.getString("id"));
					u.put("name", rs.getString("name"));
					u.put("roll", rs.getString("roll"));
					u.put("gender", rs.getString("gender"));
					u.put("age", rs.getInt("age"));
					u.put("adress", rs.getString("adress"));
					u.put("email", rs.getString("email"));
					count++;
					users.add(u);

				}
				ctx.setAttribute("count", count);
				if (flag) {
					response.sendRedirect("nouserfound.html");
				} else {
					request.setAttribute("users", users);
					RequestDispatcher rd = request.getRequestDispatcher("PrintUser");
					rd.forward(request, response);
				}
				c.close();

			} catch (Exception e) {
				out.print(e);
			}
		}
	}

}
