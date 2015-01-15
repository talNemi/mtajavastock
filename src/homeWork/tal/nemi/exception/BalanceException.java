package homeWork.tal.nemi.exception;

public class BalanceException extends Exception{

		private static final long serialVersionUID = 1L;

		public BalanceException(){
			super("The balance will become negetive beacuse of this transaction");
		}
}
