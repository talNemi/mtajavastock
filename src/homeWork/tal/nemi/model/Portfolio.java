package homeWork.tal.nemi.model;

import java.util.Date;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.*;


/**
* Portfolio class have all the stocks and the stocks status array
* and then print them. 
*/

public class Portfolio 
{
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private StockStatus[] stockSatus;
	private String title;
	private int portfolioSize = 0;
	

/**
* then create a new portfolio
*/
	
	public Portfolio(Stock[] newStocks, StockStatus[] newStockStatus, int newPortfolioSize, String newTitle)
	{
		stocks = newStocks;
		stockSatus = newStockStatus;
		title = newTitle;
		portfolioSize = newPortfolioSize;
	}
	
/**
* copy the data from portfolio and create a new portfolio with the same data
*/
	
	public Portfolio(Portfolio portfolio)
	{
		this(new Stock[MAX_PORTFOLIO_SIZE], new StockStatus[MAX_PORTFOLIO_SIZE], 0, "UNKNOWE");
		
		for(int i = 0; i < portfolio.portfolioSize ; i++){
			stocks[i] = new Stock(portfolio.stocks[i]);
			stockSatus[i] = new StockStatus(portfolio.stockSatus[i]);
		}
		this.setTitle(portfolio.getTitle());
		this.portfolioSize = portfolio.portfolioSize;
		}
	
/**
* addStock method, get stock parameter and add him to the stocks array.
*/

	public void addStock(Stock stock)
	{
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
/**
* getStock method, return the stocks array.
*/

	public Stock[] getStock()
	{
		return stocks;
	}
	
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}
	
/**
* removeStock method is to remove the index we want
*/
	
	public void removeStock(int stocksLocation)
	{
		if(stocksLocation == portfolioSize)
		{
			this.portfolioSize--;
		}
		else
		{
			this.portfolioSize--;
			for(int i = stocksLocation; i<= portfolioSize-1; i++)
			{
				this.stocks[i] = this.stocks[i+1];
			}
		}
	}

/**
* getHtmlString is a print method
* running a loop over the stocks array and print different by symbol,Ask,Bid and date. 
*/

	public String getHtmlString()
	{
		int i = 0;
			String getHtmlString = "<br><b><h1>"+ this.getTitle()+":</h1></b><br><br>" ;
			for(i=0;i<portfolioSize;i++)
				getHtmlString += stocks[i].getHtmlDescription()+"<br>";
			return getHtmlString;
	}

	public class StockStatus
	{
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuntity;
		
/**
* create new StockStatus
*/
	public StockStatus(String string, float cBid, float cAsk, Date date1, int recom, int stockQ)
	{
		symbol = string;
		currentBid = cBid;
		currentAsk = cAsk;
		date = date1;
		recommendation = recom;
		stockQuntity = stockQ;
	}
		
/**
* Copy data from the StockStatus and creates new StockStatus with the same data 
*/	
	public StockStatus(StockStatus stockStatus){
		if(this.symbol != null)
	{
			this.symbol = stockStatus.symbol;
			this.currentAsk = stockStatus.currentAsk;
			this.currentAsk = stockStatus.currentBid;
			this.date = stockStatus.date;
			this.recommendation = stockStatus.recommendation;
			this.stockQuntity = stockStatus.stockQuntity;
		}
	}
		
		public String getSymbol() 
		{
			return symbol;
		}
		
		public void setSymbol(String symbol)
		{
			this.symbol = symbol;
		}
		
		public float getCurrentBid() 
		{
			return currentBid;
		}
		
		public void setCurrentBid(float currentBid) 
		{
			this.currentBid = currentBid;
		}
		
		public float getCurrentAsk() 
		{
			return currentAsk;
		}
		
		public void setCurrentAsk(float currentAsk)
		{
			this.currentAsk = currentAsk;
		}
		
		public Date getDate() 
		{
			return date;
		}
		
		public void setDate(Date date) 
		{
			this.date = date;
		}
		
		public int getRecommendation()
		{
			return recommendation;
		}
		
		public void setRecommendation(int recommendation)
		{
			this.recommendation = recommendation;
		}
		
		public int getStockQuntity() 
		{
			return stockQuntity;
		}
		
		public void setStockQuntity(int stockQuntity) 
		{
			this.stockQuntity = stockQuntity;
		}
		
	}
}