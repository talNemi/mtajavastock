package homeWork.tal.nemi.model;

import java.util.Date;

import homeWork.tal.nemi.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * instance of this class represents a stock
 */

public class StockStatus extends Stock {

	private ALGO_RECOMMENDATION recommendation;
	protected int stockQuantity;
	
	/**
	 * This constructor is used to initialize members in StockStatus
	 */
	public StockStatus (){
		
		super();
		recommendation=ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity=0;
	}
	/**
	 * The copy constructor is used to duplicate an instance with all of his members.
	 * @param stockStatus
	 */
	public StockStatus (StockStatus stockStatus){
		
		super(stockStatus);
		recommendation=stockStatus.getRecommendation();
		stockQuantity=stockStatus.getStockQuantity();
	}
	
	public StockStatus(Stock stock) {
		super(stock);
	}
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}	
	
}