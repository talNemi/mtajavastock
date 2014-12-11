package homeWork.tal.nemi.servlet;

import homeWork.tal.nemi.model.Portfolio;
import homeWork.tal.nemi.model.Stock;
import homeWork.tal.nemi.service.PortfolioService;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
* PortfolioServlet class is used as a server app.
* Create new portfolioService and new portfolio, Initializes the portfolio.
* and print them.
*/

public class PortfolioServlet extends HttpServlet 
{


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio1 = portfolioService.getPortfolio();
		Portfolio portfolio2 = new Portfolio(portfolio1);


		resp.setContentType("text/html");
		portfolio2.setTitle("Potfolio #2");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		portfolio1.removeStock(0);
		resp.getWriter().println("<h1><b>After we remove the stock</h1></b>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		portfolio2.getStock()[2].setBid((float)55.55);
		resp.getWriter().println("<h1><b>After we change the Bid</h1></b>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
	}

}