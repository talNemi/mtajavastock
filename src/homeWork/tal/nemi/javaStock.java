package homeWork.tal.nemi;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;


public class javaStock extends HttpServlet{

	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private String DATE_FORMAT = "MM/dd/yyyy";
	private SimpleDateFormat date1 = new SimpleDateFormat(DATE_FORMAT);

	
	public javaStock(String stockSymbol, float stockAsk, float stockBid)
	{
		setSymbol(stockSymbol);
		setAsk(stockAsk);
		setBid(stockBid);
		setDate();
	}

	public void setSymbol(String StockSymbol)
	{
		symbol = StockSymbol;	
	}
	
	public String getSymbol()
	{
		return symbol;
	}
	
	public void setAsk(float StockAsk)
	{
		ask = StockAsk;
	}
	
	public float getAsk()
	{
		return ask;
	}
	
	public void setBid(float StockBid)
	{
		bid = StockBid;
	}
	
	public float getBid()
	{
		return bid;
	}
	
	@SuppressWarnings("deprecation")
	public void setDate()
	{
		date = new Date();
		date.setDate(15);
		date.setMonth(10);
		date.setYear(114);
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public String stockHtmlDetailsString()
	{
		return ("<b>Stock Symbol:</b> " + getSymbol() + " ,<b>Ask:</b> "+getAsk()+" ,<b>Bid:</b> "+getBid()+" ,<b>Date:</b> " +date1.format(date));
	}
	
}
