package homeWork.tal.nemi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.*;

import homeWork.tal.nemi.exception.BalanceException;
import homeWork.tal.nemi.exception.PortfolioFullException;
import homeWork.tal.nemi.exception.StockAlreadyExistsException;
import homeWork.tal.nemi.exception.StockNotExistException;
import homeWork.tal.nemi.model.StockStatus;


/**
 * @author Tal
 * An instance of this class represents a portfolio.
 * This instance of portfolio is used to manage the stocks in one place.
 */
public class Portfolio {

	protected static enum ALGO_RECOMMENDATION {DO_NOTHING, BUY, SELL};
	private static final int MAX_PORTFOLIO_SIZE=5;
	private int portfolioSize;
	private int stockStatusSize;
	public String title;
	private StockStatus[] stockStatus;
	private float balance;
	
	


	/**
	 * The constructor is used to initialize members with the default values.
	 *portfolioSize - size of the portfolio.
	 *stockStatusSize - size of the stock Status array.
	 *title - the title of the portfolio.
	 *balance= the amount of money in the balance account.
	 *stocks= an array of stocks in the size of MAX_PORTFOLIO_SIZE.
	 *stockStatus - an array of stockStatus in the size of MAX_PORTFOLIO_SIZE.
	 */

	public Portfolio()
	{
		portfolioSize =0;
		stockStatusSize=0;
		title = "PortfolioDefault";
		balance=0;
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}

	/**
	 * The copy constructor is used to duplicate an instance with all of his members.
	 * Inside the loop a new stock is created (inside an array) and it receives the details of the parameter stock.
	 * Furthermore, other members of stock outside the loop are being transfered to the new stock.
	 * @param portfolio
	 */
	public Portfolio(Portfolio portoflio)
	{
		this();
		for (int i = 0; i < portoflio.portfolioSize; i++)
		{	
			stockStatus[i]=new StockStatus(portoflio.stockStatus[i]);
		}
		title=portoflio.title;
		portfolioSize=portoflio.portfolioSize;
		stockStatusSize=portoflio.stockStatusSize;
		balance=portoflio.balance;
	}
	/**
	 * Prints a stockStatus to the console.
	 * @param stockStatus
	 */
	public void printStockStatus (StockStatus stockStatus)
	{
		if(stockStatus==null)
		{
			System.out.println("Empty cell");
			return;
		}
		System.out.println("symbol: "+stockStatus.symbol+
				"\n"+"ask: "+stockStatus.ask+
				"\n"+"bid: "+stockStatus.bid+
				"\n"+"date: "+stockStatus.date+
				"\n"+"stockQuantity: "+stockStatus.stockQuantity+
				"\n"+"recommendation: "+stockStatus.getRecommendation());
	}
	/**
	 * This function is adding money to the current balance
	 * @param amount
	 */
	public void updateBalance(float amount)
	{
		balance=balance+amount;
	}
	/**
	 * This function takes the stock's current bid and multiply it by the amount of stocks.
	 * @return the values off all stocks
	 */
	public float getStocksValue()
	{
		float stocksValue=0;
		int i=0;
		while(stockStatus[i]!=null)
		{
			stocksValue+=stockStatus[i].bid*stockStatus[i].getStockQuantity();
			i++;
		}
		return stocksValue;
		
	}
	/**
	 * 
	 * @return how much money in the balance account.
	 */
	public float getBalance()
	{
		return balance;
	}
	/**
	 * 
	 * @return sum of the balance account + the assets' money value  
	 */
	public float getTotalValue()
	{
		return getBalance()+getStocksValue();
	}
	
	/**
	 * This method gets a stock's symbol as an identifier and removes the stock out of the portfolio.
	 * The first loop is used to identify the desirable stock and to sell it, then removes it both from stocks and StockStatus arrays.
	 * The second loop starts where the missing stock was, then it swaps between members until the array ends in order to organize the array.
	 * Lastly, a reduction of prtfolioSize and StockStatusSize is made.
	 * @param stockSymbol
	 */
	public void removeStock(String stockSymbol) throws StockNotExistException{

		int i=0;
		boolean isFound=false;
		boolean isSold=false;
		for (; i < portfolioSize; i++)
		{
			if (stockStatus[i].symbol.equals(stockSymbol))
			{
				sellStock(stockSymbol, -1);
				isFound=true;
				break;
			}
		}
		if (isFound==false) {
			System.out.println(stockSymbol+" does not found");
			throw new StockNotExistException(stockSymbol);
		}
		else{

				for (; i < portfolioSize; i++)
				{

					if (stockStatus[i+1]==null)
					{
						portfolioSize--;
						stockStatusSize--;
						break;
					}
					else
					{
						stockStatus[i]=stockStatus[i+1];
					}
				}
		}

	}
	/**
	 * 
	 * buyStock is used to purchase stocks, it updates the balance of the balance account.
	 * The first loop is used to identify that the stock is actually in the stocks' array.
	 * 
	 * "-1" for buying all stocks with the money on the balance account.
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	public void buyStock (String symbol,int quantity) throws BalanceException, StockNotExistException{

		boolean Symbolfound=false;
		int amount;
		int i = 0;
		
		for (; i < portfolioSize; i++) {
			if (stockStatus[i].getSymbol().equals(symbol)) {
				Symbolfound=true;
				break;
			}
		}
		if(Symbolfound==true)
		{
			if (quantity==-1) {
				amount=(int) Math.floor((balance/stockStatus[i].ask));
				balance=balance - amount*stockStatus[i].ask;
				stockStatus[i].stockQuantity=stockStatus[i].stockQuantity+amount;
			}
			else{
				if(stockStatus[i].ask*quantity>balance)
				{
					System.out.println("Not enough balance to complete purchase");
					throw new BalanceException();
				}
				stockStatus[i].stockQuantity=stockStatus[i].getStockQuantity()+quantity;
				balance=balance - stockStatus[i].ask*quantity;
			}
		}
		else
		{
			System.out.println(symbol+" does not found");
			throw new StockNotExistException(symbol);
		}
	}
	/**
	 * 
	 * sellStock is similar to buyStock, it purpose is to sell stocks but not to remove them from the portfolio.
	 * The first loop is used to identify that the stock is actually in the stocks' array.
	 * 
	 * "-1" to sell all stocks
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	public void sellStock (String symbol, int quantity) throws StockNotExistException{
		boolean Symbolfound=false;
		int i = 0;
		
		for (; i < portfolioSize; i++) {
			if (stockStatus[i].getSymbol().equals(symbol)) {
				Symbolfound=true;
				break;
			}
		}
		if(Symbolfound==true)
		{
			if (quantity==-1) {
				balance=balance+stockStatus[i].stockQuantity*stockStatus[i].bid;
				stockStatus[i].stockQuantity=0;
			}
			else if (quantity>stockStatus[i].stockQuantity||quantity<0) {
				System.out.println(symbol+" hasn't been sold - Not enough stocks to sell"+"\n"+"you have only "+ stockStatus[i].stockQuantity);
			}
			else{
				stockStatus[i].stockQuantity=stockStatus[i].stockQuantity-quantity;
				balance=balance + stockStatus[i].bid*quantity;
			}
		}
		else
		{
			System.out.println(symbol+" hasn't been sold");
			throw new StockNotExistException(symbol);
		}
		
		
	}
	/**
	 * The addStock method is inserting a new stock into a stock array.
	 * First it checks that the stock isn't already in portfolio.
	 * The method updates StockStatus with the information regarding the stock.
	 * It uses portfolioSize member as a counter.
	 * @param stock
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException{
		
		int i=0;
		while(stockStatus[i]!=null)
		{
			if (stockStatus[i].getSymbol().equals(stock.getSymbol())) {
				System.out.println("The stock "+stock.getSymbol()+" already exists in portfolio");
				throw new StockAlreadyExistsException(stock.symbol);
			}
			i++;
		}
		if(portfolioSize==MAX_PORTFOLIO_SIZE||portfolioSize>MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Can not add new stock,"
					+ " portfolio can only have " +MAX_PORTFOLIO_SIZE +" stocks");
			throw new PortfolioFullException();
		}
		else
		{

			StockStatus stockStatus = new StockStatus();
			stockStatus.ask=stock.getAsk();
			stockStatus.bid=stock.getBid();
			stockStatus.symbol=stock.getSymbol();
			stockStatus.setStockQuantity(0);
			stockStatus.date=new Date(stock.getDate().getTime());
			stockStatus.setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			
			
			this.stockStatus[stockStatusSize]=stockStatus;
			stockStatusSize++;
			portfolioSize++;
		}
	}


	public Stock[] getStocks(){ 
		return stockStatus;
	}

	/**
	 * This method returns a string composed of the stocks' details.
	 * At the end of the string the total sum of the portfolio/stocks/balance is presented.
	 * @param none
	 * @return A string with the information of all stocks.
	 *
	 */
	public String getHtmlString(){

		String HtmlString= new String("<h1><u>"+title+"</u></h1>"); 
		for (int i = 0; i < portfolioSize; i++) 
		{ 
			if(stockStatus[i]!=null)
			{
			HtmlString=HtmlString+(stockStatus[i].getHtmlDescription()+"<br>");
			}
		}
		HtmlString+="<br>"+"Total Portfolio Value: "+getTotalValue()
				+"$, "+"<br>"+"Total Stocks value:" + getStocksValue()
				+"$, "+"<br>"+"Balance: "+getBalance()+"$"+"<br>";

		return HtmlString;
	}

}