package homeWork.tal.nemi.service;

import homeWork.tal.nemi.model.Portfolio;
import homeWork.tal.nemi.model.Stock;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tal
 * This class creates an instance of portfolioService.
 * We use the portfolioService's instance in order to execute actions regarding the stocks.
 */

public class PortfolioService{
	/**
	 * 
	 * This method is used to create stocks and to insert them into a portfolio.
	 * @param none
	 * @return a portfolio initialized with stocks.
	 *
	 */
	public Portfolio getPortfolio() {

		Portfolio myPortfolio= new Portfolio();
		Stock stock1=new Stock();
		Stock stock2=new Stock();
		Stock stock3=new Stock();

		stock1.setSymbol("PIH");
		stock1.setAsk((float) 10);
		stock1.setBid((float) 8.5);
		stock1.setDate(new Date(114,11,15));
		
		
		stock2.setSymbol("AAL");
		stock2.setAsk((float) 30);
		stock2.setBid((float) 25.5);
		stock2.setDate(new Date(114,11,15));
		
		stock3.setSymbol("CAAS");
		stock3.setAsk((float) 20);
		stock3.setBid((float) 15.5);
		stock3.setDate(new Date(114,11,15));
		
		myPortfolio.updateBalance(10000);
		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);

		myPortfolio.title= "Exercise 8 portfolio";
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");


		return myPortfolio;
	}

}

