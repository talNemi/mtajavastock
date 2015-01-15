package homeWork.tal.nemi.servlet;

import homeWork.tal.nemi.exception.BalanceException;
import homeWork.tal.nemi.exception.PortfolioFullException;
import homeWork.tal.nemi.exception.StockAlreadyExistsException;
import homeWork.tal.nemi.exception.StockNotExistException;
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

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");{

			PortfolioService portfolioService = new PortfolioService();
			Portfolio portfolio;
			try {
				portfolio = portfolioService.getPortfolio();
				resp.getWriter().println(portfolio.getHtmlString());
			}
			catch (StockAlreadyExistsException e){
				resp.getWriter().println(e.getMessage());
			}
			catch (BalanceException e) {
				resp.getWriter().println(e.getMessage());
			}
			catch (StockNotExistException e) {
				resp.getWriter().println(e.getMessage());
			}
			catch (PortfolioFullException e) {
				resp.getWriter().println(e.getMessage());
			}

		}
	}
}