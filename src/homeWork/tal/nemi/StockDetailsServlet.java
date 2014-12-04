package homeWork.tal.nemi;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet{
	
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/html");
		Stock Stock1 = new Stock(("<b>PIH</b>"), (float)12.4, (float)13.1);
		Stock Stock2 = new Stock(("<b>AAL</b>"), (float)5.5, (float)5.78);
		Stock Stock3 = new Stock(("<b>CAAS</b>"), (float)31.5, (float)31.2);
		resp.getWriter().println("<br>");
		resp.getWriter().println(Stock1.getHtmlDescription()+"<br><br/>");
		resp.getWriter().println(Stock2.getHtmlDescription()+"<br><br/>");
		resp.getWriter().println(Stock3.getHtmlDescription()+"<br><br/>");
		
	}
}
