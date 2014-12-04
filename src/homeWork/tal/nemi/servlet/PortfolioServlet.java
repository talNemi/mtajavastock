package homeWork.tal.nemi.servlet;

import homeWork.tal.nemi.Stock;
import homeWork.tal.nemi.model.Portfolio;
import homeWork.tal.nemi.service.PortfolioService;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortfolioServlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");{
				
			PortfolioService portfolioService = new PortfolioService();
			Portfolio portfolio = portfolioService.getPortfolio();
			Stock[] stocks = portfolio.getStocks();
			resp.getWriter().println(portfolio.getHtmlString());		
		}
	}
}


