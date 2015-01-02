package homeWork.tal.nemi.servlet;

import homeWork.tal.nemi.model.Portfolio;
import homeWork.tal.nemi.model.Stock;
import homeWork.tal.nemi.service.PortfolioService;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Tal
 * Class PortfolioServlet
 * This class creates an instance of portfolioServlet.
 * This servlet of portfolio is used for presenting data on a web page.
 * It uses an instance of portfolioService in order to retrieve data from the Internet.
 */

public class PortfolioServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");{

			PortfolioService portfolioService = new PortfolioService();
			Portfolio portfolio = portfolioService.getPortfolio();
			
			resp.getWriter().println(portfolio.getHtmlString());
			
		}
	}
}