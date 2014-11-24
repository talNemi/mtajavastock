package homeWork.tal.nemi;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet{
	
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/html");
		javaStock Stock1 = new javaStock(("<b>PIH</b>"), (float)12.4, (float)13.1);
		javaStock Stock2 = new javaStock(("<b>AAL</b>"), (float)5.5, (float)5.78);
		javaStock Stock3 = new javaStock(("<b>CAAS</b>"), (float)31.5, (float)31.2);
		resp.getWriter().println("<br>");
		resp.getWriter().println(Stock1.stockHtmlDetailsString()+"<br><br/>");
		resp.getWriter().println(Stock2.stockHtmlDetailsString()+"<br><br/>");
		resp.getWriter().println(Stock3.stockHtmlDetailsString()+"<br><br/>");
		
	}
}
