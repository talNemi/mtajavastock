package homeWork.tal.nemi.exception;

public class StockAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String symbol) {
		super("Stock " + symbol + " is already exists!");
	}

}
