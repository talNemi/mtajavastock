package homeWork.tal.nemi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;

public class Stock {
	
	private String stockHtmlDetailsString = "";
	private float ask;
	private float bid;
	private Date date;
	private String symbol;

	/**
	* create new Stock
	*/
	
	public Stock(String string, float Ask, float Bid, Date date1) {
		symbol = string;
		ask = Ask;
		bid = Bid;
		date = date1;
	}

	/**
	* Copy the data from Stock and creates new Stock with the same data 
	*/
	
	public Stock(Stock stock){
		
		this(stock.getsymbol(),stock.getAsk(),stock.getBid(),stock.getDate());
		
	}
	
	public String getsymbol() {
		
		return symbol;
		
	}
	
	public void setsymbol(String Symbol) {
		
		symbol = Symbol;
		
	}
	
	public float getAsk() {
		
		return ask;
		
	}
	
	public void setAsk(float Ask) {
		
		ask = Ask;
		
	}
	
	public float getBid() {
		
		return bid;
		
	}
	
	public void setBid(float Bid) {
		
		bid = Bid;
	
	}
	
	public Date getDate() {
		
		return date;
		
	}
	
	public void setDate(Date newDate) {
		date = newDate;
	}
	
	public String getHtmlDescription() {
		stockHtmlDetailsString = "<b>stock symbol</b> : "+getsymbol()+ "<b> Ask </b> : "+getAsk()+ "<b> Bid </b> : "+getBid()+ "<b> Date </b> : "+getDate();
		return stockHtmlDetailsString;
	}
	

}
