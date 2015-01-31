package homeWork.tal.nemi.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.*;

import homeWork.tal.nemi.exception.BalanceException;
import homeWork.tal.nemi.exception.IllegalQuantityException;
import homeWork.tal.nemi.exception.PortfolioFullException;
import homeWork.tal.nemi.exception.StockAlreadyExistsException;
import homeWork.tal.nemi.exception.StockNotExistException;
import homeWork.tal.nemi.model.StockStatus;
import homeWork.tal.nemi.service.PortfolioService;


/**
 * @author Tal
 * An instance of this class represents a portfolio.
 * This instance of portfolio is used to manage the stocks in one place.
 */


public class Portfolio {

	public static enum ALGO_RECOMMENDATION {DO_NOTHING, BUY, SELL};
	public static final int MAX_PORTFOLIO_SIZE=5;
	public int portfolioSize;
	private int stockStatusSize;
	public String title;
	//private StockStatus[] stockStatus;
	private List<StockStatus> stockStatus;
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
		//stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		stockStatus = new ArrayList<StockStatus>(MAX_PORTFOLIO_SIZE);
	}

	/**
	 * The copy constructor is used to duplicate an instance with all of his members.
	 * Inside the loop a new stock is created (inside an array) and it receives the details of the parameter stock.
	 * Furthermore, other members of stock outside the loop are being transfered to the new stock.
	 * @param portfolio
	 */
	public Portfolio(Portfolio portfolio)
	{
		this();
		title=portfolio.title;
		portfolioSize=portfolio.portfolioSize;
		stockStatusSize=portfolio.stockStatusSize;
		stockStatus.addAll(portfolio.stockStatus);
		balance=portfolio.balance;
	}

	
	public Portfolio(List <StockStatus> stockStatus)
	{
		
		this();
		
		for (int i = 0; i < stockStatus.size(); i++) {
			this.stockStatus.add(stockStatus.get(i));
		}

		
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
		float ret = 0;		
		for (int i=0; i < stockStatus.size() ; i++){
			ret += stockStatus.get(i).getStockQuantity() * stockStatus.get(i).getBid();
		}

		return ret;
		
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
	public void removeStock(String symbol) throws StockNotExistException, IllegalQuantityException 
	{	
		int index = get_index_of_symbol(symbol);
		
		if(index ==-1){
			throw new StockNotExistException(symbol);
		}
		
		sellStock(symbol, -1);
		
		while (index < stockStatus.size())
		{
			stockStatus.remove(index);
			index++;
		}
	}
	
	/**
	 * get symbol and find the place in the array - the index of symbol 
	 * @param symbol
	 * @return
	 */
	private int get_index_of_symbol(String symbol)
	{
		for(int i = 0; i < stockStatus.size(); i++)
		{
			if(stockStatus.get(i).getSymbol().toLowerCase().equals(symbol.toLowerCase()))
			{
				return i;
			}
		}
		return -1; 
	}
	/*
	public void removeStock(String stockSymbol) throws StockNotExistException{
		int i=0;
		boolean isFound=false;
		for (; i < portfolioSize; i++)
		{
			if (stockStatus.get(i).symbol.equals(stockSymbol))
			{
				sellStock(stockSymbol, -1);
				isFound=true;
				break;
			}
		}
		if (isFound==false) {
			System.out.println(stockSymbol+" hasn't been found");
			throw new StockNotExistException(stockSymbol);
		}
		else{
				for (; i < portfolioSize; i++)
				{
					if (stockStatus.get(i+1)==null)
					{
						portfolioSize--;
						stockStatusSize--;
						break;
					}
					else
					{
						//stockStatus.get(i)=stockStatus[i+1];
						stockStatus.
						
					}
				}
		}
	}
	*/
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
			if (stockStatus.get(i).getSymbol().equals(symbol)) {
				Symbolfound=true;
				break;
			}
		}
		if(Symbolfound==true)
		{
			if (quantity==-1) {
				amount=(int) Math.floor((balance/stockStatus.get(i).ask));
				balance=balance - amount*stockStatus.get(i).ask;
				stockStatus.get(i).stockQuantity=stockStatus.get(i).stockQuantity+amount;
			}
			else{
				if(stockStatus.get(i).ask*quantity>balance)
				{
					System.out.println("Not enough balance to complete purchase");
					throw new BalanceException();
				}
				stockStatus.get(i).stockQuantity=stockStatus.get(i).getStockQuantity()+quantity;
				balance=balance - stockStatus.get(i).ask*quantity;
			}
		}
		else
		{
			System.out.println(symbol+" was not found");
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
			if (stockStatus.get(i).getSymbol().equals(symbol)) {
				Symbolfound=true;
				break;
			}
		}
		if(Symbolfound==true)
		{
			if (quantity==-1) {
				balance=balance+stockStatus.get(i).stockQuantity*stockStatus.get(i).bid;
				stockStatus.get(i).stockQuantity=0;
			}
			else if (quantity>stockStatus.get(i).stockQuantity||quantity<0) {
				System.out.println(symbol+" hasn't been sold - Not enough stocks to sell"+"\n"+"you have only "+ stockStatus.get(i).stockQuantity);
			}
			else{
				stockStatus.get(i).stockQuantity=stockStatus.get(i).stockQuantity-quantity;
				balance=balance + stockStatus.get(i).bid*quantity;
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
	
	/*
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException{
		
		System.out.println("addStock");
		int i=0;
		while(stockStatus.get(i)!=null)
		{
			if (stockStatus.get(i).getSymbol().equals(stock.getSymbol())) {
				System.out.println("The stock "+stock.getSymbol()+" already exists in portfolio");
				throw new StockAlreadyExistsException(stock.symbol);
			}
			i++;
		}
		if(portfolioSize==MAX_PORTFOLIO_SIZE||portfolioSize>MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Cant add new stock,"
					+ " portfolio can only have " +MAX_PORTFOLIO_SIZE +" stocks");
			throw new PortfolioFullException();
		}
		else
		{
			System.out.println("create new stockStatus");
			for ( i = 0; i < stockStatus.size(); i++) {
				
				if (stockStatus.get(i).getSymbol().equals(stock.getSymbol())) {
					System.out.println(stockStatus.get(i).getSymbol() + " Already exists in the portfolio");
					
					throw new StockAlreadyExistsException(stock.getSymbol());
				}
			}
			
			stockStatus.add(new StockStatus(stock));
			
			StockStatus stockStatus = new StockStatus();
			stockStatus.ask=stock.getAsk();
			stockStatus.bid=stock.getBid();
			stockStatus.symbol=stock.getSymbol();
			stockStatus.setStockQuantity(0);
			stockStatus.date=new Date(stock.getDate().getTime());
			stockStatus.setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			
			
			//this.stockStatus[stockStatusSize]=stockStatus;
			stockStatusSize++;
			portfolioSize++;
			
		}
	}
	*/
	
public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException {
		
		if(stockStatus.size()==MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Cant add new stock, portfolio can have only "+ MAX_PORTFOLIO_SIZE +" stocks");
			throw new PortfolioFullException();
		}
		
		for (int i = 0; i < stockStatus.size(); i++) {
			
			if (stockStatus.get(i).getSymbol().equals(stock.getSymbol())) {
				System.out.println(stockStatus.get(i).getSymbol() + " Already exists in the portfolio");
				
				throw new StockAlreadyExistsException(stock.getSymbol());
			}
		}
		
		stockStatus.add(new StockStatus(stock));		
	}




	public StockStatus[] getStocks() {
		StockStatus[] ret = new StockStatus[stockStatus.size()];
		ret =  stockStatus.toArray(ret);
		return ret;
	}

	/**
	 * This method returns a string composed of the stocks' details.
	 * At the end of the string the total sum of the portfolio/stocks/balance is presented.
	 * @param none
	 * @return A string with the information of all stocks.
	 */

	public String getHtmlString(){

		String HtmlString= new String("<h1><u>"+title+"</u></h1>"); 
		for (int i = 0; i < portfolioSize; i++) 
		{ 
			if(stockStatus.get(i)!=null)
			{
			HtmlString=HtmlString+(stockStatus.get(i).getHtmlDescription()+"<br>");
			}
		}
		HtmlString+="<br>"+"Total Portfolio Value: "+getTotalValue()
				+"$, "+"<br>"+"Total Stocks value:" + getStocksValue()
				+"$, "+"<br>"+"Balance: "+getBalance()+"$"+"<br>";

		return HtmlString;
	}

	public StockStatus findBySymbol(String symbol) throws BalanceException, StockAlreadyExistsException, StockNotExistException, PortfolioFullException {

		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();

		System.out.println("method findBySymbol(String symbol)");
		System.out.println("portfolio.stockStatusSize in findBySymbol is:" + portfolio.stockStatusSize);
		for (int i = 0; i < portfolio.stockStatusSize; i++) {
			if (symbol.equals(portfolio.stockStatus.get(i).symbol)) {
				System.out.println("worked");
				return portfolio.stockStatus.get(i);
			}
		}
		System.out.println("error");
		return null;
		
		
	}
	/*
	public StockStatus findBySymbol(String symbol) throws StockNotExistException {
		for(int i=0; i<stockStatus.size(); i++){
			if(stockStatus.get(i).getSymbol().toLowerCase().equals(symbol.toLowerCase()))
			{
				return stockStatus.get(i);
			}
		}
		
		throw new StockNotExistException(symbol);
	} 
	
*/
	public void printPortfolio(Portfolio portfolio)
	{
		System.out.println("method printPortfolio");
		System.out.println("portfolioSize in portfolioPrint is:" + portfolio.portfolioSize);
		for (int i = 0; i < portfolio.portfolioSize; i++) {
			System.out.println(portfolio.stockStatus.get(i).getHtmlDescription());
		}
	}
	
	public void printStockStatusList (List<StockStatus> stockStatuses)
	{
		System.out.println("method printStockStatusList");
		System.out.println("stockStatuses.size() in printStockStatusList is:" + stockStatuses.size());
		for (int i = 0; i < stockStatuses.size(); i++) {
			System.out.println(stockStatuses.get(i).symbol);
		}
	}
	public List<StockStatus> getStocksStatus() {
		return stockStatus;
	}
	public void setStocksStatus(List<StockStatus> stocksStatus) {
		this.stockStatus = stocksStatus;
	}
	
	public int getPortfolioSize() {
		return stockStatus.size();
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}

}