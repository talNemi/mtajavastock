package homeWork.tal.nemi.model;

import homeWork.tal.nemi.Stock;

import java.util.Date;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.*;


public class Portfolio extends HttpServlet {
	 
	 private static final int MAX_PORTFOLIO_SIZE=5;
	 private int portfolioSize;
	 private String title;
	 private Stock[]stocks;
	 private StockStatus[] stockStatus;
	 
	 public Portfolio() {
		 
		 portfolioSize =0;
		 title = "";
		 stocks= new Stock[MAX_PORTFOLIO_SIZE];
		 stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		 
	 	}

	 public void addStock(Stock stock){
		 stocks[portfolioSize]=stock;
		 portfolioSize++;
	 }
	
	 public Stock[] getStocks(){ 
		 return stocks;
	 }
	 public String getHtmlString(){
		 String HtmlString= new String("<h1>"+title+"</h1>");
		 for (int i = 0; i < portfolioSize; i++) { 
			 HtmlString=HtmlString+(stocks[i].getHtmlDescription()+"<br>");
		 }
		 
		 return HtmlString;
	 }
	 
	 public class StockStatus{
		 private String symbol;
		 private float currentBid, currentAsk;
		 private Date date;
		 private int recommendation;
		 private int stockQuantity;
		 private final static int DO_NOTHING=0;
		 private final static int BUY=1;
		 private final static int SELL=2;
	 }
}
