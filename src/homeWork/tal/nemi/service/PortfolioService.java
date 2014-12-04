package homeWork.tal.nemi.service;

import homeWork.tal.nemi.Stock;
import homeWork.tal.nemi.model.Portfolio;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PortfolioService extends HttpServlet {

	public Portfolio getPortfolio() {
	
		Portfolio myPortfolio= new Portfolio();
		Stock Stock1 = new Stock(("<b>PIH</b>"), (float)12.4, (float)13.1);
		Stock Stock2 = new Stock(("<b>AAL</b>"), (float)5.5, (float)5.78);
		Stock Stock3 = new Stock(("<b>CAAS</b>"), (float)31.5, (float)31.2);
			
		myPortfolio.addStock(Stock1);
		myPortfolio.addStock(Stock2);
		myPortfolio.addStock(Stock3);
		
		return myPortfolio;
	}
	
}

