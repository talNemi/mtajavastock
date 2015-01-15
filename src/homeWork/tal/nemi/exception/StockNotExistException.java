package homeWork.tal.nemi.exception;

public class StockNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockNotExistException (String symbol) {
		super("Stock " + symbol + " does not exists!");
	}

}
