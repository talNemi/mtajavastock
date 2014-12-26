package homeWork.tal.nemi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;

/**
 * @author Tal
 * Class Stock
 * This class creates an instance of a stock with all of it members.
 * Stock class is used to initialize stocks through certain methods.
 */

public class Stock
{

	private String symbol;
	private float bid;
	private float ask;
	private Date date;
	private SimpleDateFormat sdf;


/**
* Constructor
* The constructor is used to initialize members with default values.
*
*/
	public Stock()
	{
		symbol="noting";
		bid=0;
		ask=0;
		date = new Date();
		sdf = new SimpleDateFormat("dd/MM/yy");
	}

/**
* Copy constructor
* The copy constructor is used to duplicate an instance with all of his members.
*@param stock
*/
	
	public Stock (Stock stock)
	{

		date = new Date();
		sdf = new SimpleDateFormat("dd/MM/yy");
		setSymbol(stock.getSymbol());
		setBid(stock.getBid());
		setAsk(stock.getAsk());
		setDate(new Date(stock.date.getTime()));
		
	}

// getters
	public String getSymbol() 
	{
		return symbol;
	}
	public float getBid() 
	{
		return bid;	
	}
	public float getAsk() 
	{
		return ask;
	}
	public Date getDate()
	{
		return date;
	}

//setters
	public void setSymbol(String SymbolValue)
	{
		symbol=SymbolValue;
	}

	public void setBid(float bidValue)
	{

		bid=bidValue;

	}
	public void setAsk(float askValue)
	{

		ask=askValue;

	}
	 public void setDate(Date date) 
	 {
         this.date = date;
	 }

/**
* getHtmlDescription()
* This function is used to print the data of one stock at a time.
* @param none
* @return string with the information regarding the stock.
*/
	 
	public String getHtmlDescription()
	{
		String stockHtmlDetailsString = new String ("<b>Stock symbol</b>: "+getSymbol()+
				"<b>, ask</b>: "+getAsk()+
				"<b>, bid</b>: "+getBid()+
				"<b>, date</b>: "+sdf.format(getDate()));
		return stockHtmlDetailsString;
	}
}