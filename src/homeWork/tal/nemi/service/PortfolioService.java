package homeWork.tal.nemi.service;

import homeWork.tal.nemi.model.Portfolio;
import homeWork.tal.nemi.model.Portfolio.StockStatus;
import homeWork.tal.nemi.model.Stock;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
*the class PortfolioService has all fields of the stocks
*create new portfolio and add the stocks to the new portfolio.
*/

public class PortfolioService { 
	private final static int MAX_PORTFOLIO_SIZE = 5;
	Portfolio myPortfolio;

	public PortfolioService()
	{
		myPortfolio = new Portfolio(new Stock[MAX_PORTFOLIO_SIZE], new StockStatus[MAX_PORTFOLIO_SIZE], 0, "");
	}
	
/**
* getPortfolio method is initializes all fields of the stocks, add them to the new portfolio
* and return the new portfolio. 
*/
	public Portfolio getPortfolio()
	{

		Stock stock1,stock2, stock3;
		
		Date date = new Date();
		
		stock1 = new Stock("PIH",(float)12.4,(float)13.1,date);
		myPortfolio.addStock(stock1);
		
		stock2 = new Stock("AAL",(float)5.5,(float)5.78,date);
		myPortfolio.addStock(stock2);
		
		stock3 = new Stock("CAAS",(float)31.5,(float)31.2,date);
		myPortfolio.addStock(stock3);
		
		myPortfolio.setTitle("Potfolio #1");

		
		return myPortfolio;
	}
}

