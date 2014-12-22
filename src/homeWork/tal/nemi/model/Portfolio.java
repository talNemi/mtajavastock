package homeWork.tal.nemi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.*;


/**
 * @author Tal
 * An instance of this class represents a portfolio.
 * This instance of portfolio is used to manage the stocks in one place.
 *@since 5/12/14
 */

public class Portfolio {

	private static final int MAX_PORTFOLIO_SIZE=5;
	private static enum ALGO_RECOMMENDATION {DO_NOTHING, BUY, SELL};
	private int portfolioSize;
	private int stockStatusSize;
	public String title;
	private Stock[]stocks;
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
		stocks= new Stock[MAX_PORTFOLIO_SIZE];
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
			stocks[i]=new Stock(portoflio.stocks[i]);
			stockStatus[i]=new StockStatus(portoflio.stockStatus[i]);
		}
		title=portoflio.title;
		portfolioSize=portoflio.portfolioSize;
		stockStatusSize=portoflio.stockStatusSize;
		balance=portoflio.balance;
	}
	/**
	 * print a stockStatus to the console.
	 * @param stockStatus
	 */
	public void printStockStatus (StockStatus stockStatus)
	{
		System.out.println("symbol: "+stockStatus.symbol+
				"\n"+"ask: "+stockStatus.currentAsk+
				"\n"+"bid: "+stockStatus.currentBid+
				"\n"+"date: "+stockStatus.date);
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
			stocksValue+=stockStatus[i].currentBid*stockStatus[i].stockQuantity;
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
	public boolean removeStock(String stockSymbol){

		int i=0;
		boolean isFound=false;
		boolean isSold=false;
		for (; i < portfolioSize; i++)
		{
			if (stockStatus[i].symbol.equals(stockSymbol))
			{
				isSold=sellStock(stockSymbol, -1);
				isFound=true;
				break;
			}
		}
		if (isFound==false) {
			System.out.println(stockSymbol+" hasn't been found");
			return false;
		}
		else{
			if(isSold==true){

				for (; i < portfolioSize; i++)
				{

					if (stocks[i+1]==null)
					{
						portfolioSize--;
						stockStatusSize--;
						break;
					}
					else
					{
						stocks[i]=stocks[i+1];
						stockStatus[i]=stockStatus[i+1];
					}
				}
			}
			else 
			{
				System.out.println(stockSymbol+" hasn't been sold");
				return false;
			}
		}
		return isFound;

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
	public boolean buyStock (String symbol,int quantity){

		boolean Symbolfound=false;
		int amount;
		int i = 0;
		
		for (; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				Symbolfound=true;
				break;
			}
		}
		if(Symbolfound==true)
		{
			if (quantity==-1) {
				amount=(int) Math.floor((balance/stockStatus[i].currentAsk));
				balance=balance - amount*stockStatus[i].currentAsk;
				return true;
			}
			else{
				if(stockStatus[i].currentAsk*quantity>balance)
				{
					System.out.println("Not enough balance to complete purchase");
					return false;
				}
				stockStatus[i].stockQuantity=stockStatus[i].stockQuantity+quantity;
				balance=balance - stockStatus[i].currentAsk*quantity;
				return true;
			}
		}
		else
		{
			System.out.println(symbol+" purchase has failed");
			return false;
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
	public boolean sellStock (String symbol, int quantity)
	{
		boolean Symbolfound=false;
		int i = 0;
		
		for (; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				Symbolfound=true;
				break;
			}
		}
		if(Symbolfound==true)
		{
			if (quantity==-1) {
				balance=balance+stockStatus[i].stockQuantity*stockStatus[i].currentBid;
				stockStatus[i].stockQuantity=0;
				return true;
			}
			if (quantity==MAX_PORTFOLIO_SIZE||quantity>MAX_PORTFOLIO_SIZE||quantity<0) {
				System.out.println(symbol+" hasn't been sold - Not enough stocks to sell");
				return false;
			}
			else{
				stockStatus[i].stockQuantity=stockStatus[i].stockQuantity-quantity;
				balance=balance + stockStatus[i].currentBid*quantity;
				return true;
			}
		}
		else
		{
			System.out.println(symbol+" hasn't been sold");
			return false;
		}
		
		
	}
	/**
	 * The addStock method is inserting a new stock into a stock array.
	 * First it checks that the stock isn't already in portfolio.
	 * The method updates StockStatus with the information regarding the stock.
	 * It uses portfolioSize member as a counter.
	 * @param stock
	 */
	public void addStock(Stock stock){
		
		int i=0;
		while(stocks[i]!=null)
		{
			if (stocks[i].getSymbol().equals(stock.getSymbol())) {
				System.out.println("The stock "+stock.getSymbol()+" already exists in portfolio");
				return;
			}
			i++;
		}
		if(portfolioSize==MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Can’t add new stock,"
					+ " portfolio can only have " +MAX_PORTFOLIO_SIZE +" stocks");
		}
		else
		{

			StockStatus stockStatus = new StockStatus();
			stockStatus.currentAsk=stock.getAsk();
			stockStatus.currentBid=stock.getBid();
			stockStatus.symbol=stock.getSymbol();
			stockStatus.stockQuantity=0;
			stockStatus.date=new Date(stock.getDate().getTime());
			stockStatus.recommendation=ALGO_RECOMMENDATION.DO_NOTHING;
			
			this.stockStatus[stockStatusSize]=stockStatus;
			stocks[portfolioSize]=stock;
			stockStatusSize++;
			portfolioSize++;
		}
	}


	public Stock[] getStocks(){ 
		return stocks;
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

			HtmlString=HtmlString+(stocks[i].getHtmlDescription()+"<br>");
		}
		HtmlString+="<br>"+"Total Portfolio Value: "+getTotalValue()
				+"$, "+"<br>"+"Total Stocks value:" + getStocksValue()
				+"$, "+"<br>"+"Balance: "+getBalance()+"$"+"<br>";

		return HtmlString;
	}

	/**
	 * An instance of this inner class represents a stock's status.
	 * All of the members represents information related to a specific stock.
	 */
	public class StockStatus { 
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private SimpleDateFormat sdf;
		private ALGO_RECOMMENDATION recommendation;
		private int stockQuantity;


		/**
		 * This constructor is used to initialize members in StockStatus
		 */
		public StockStatus (){
			symbol="";
			currentBid=0;
			currentAsk=0;
			date = new Date();
			recommendation=ALGO_RECOMMENDATION.DO_NOTHING;
			stockQuantity=0;
		}
		/**
		 * The copy constructor is used to duplicate an instance with all of his members.
		 * @param stockStatus
		 */
		public StockStatus (StockStatus stockStatus){
			this();
			symbol=stockStatus.symbol;
			currentBid=stockStatus.currentBid;
			currentAsk=stockStatus.currentAsk;
			date = new Date(stockStatus.date.getTime());
			recommendation=stockStatus.recommendation;
			stockQuantity=stockStatus.stockQuantity;
		}	

	}

}