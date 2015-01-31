package homeWork.tal.nemi.servlet;
import homeWork.tal.nemi.model.StockStatus;
import homeWork.tal.nemi.service.DatastoreService;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Stock trend api entry.
 * Parameters:
 * 	symbol which we like to get trend of.
 * @author hanang
 */
@SuppressWarnings("serial")
public class StockServlet extends AbstractAlgoServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String symbol = req.getParameter("symbol");
		List<StockStatus> stockHistory = DatastoreService.getInstance().getStockHistory(symbol, 30);
		resp.setContentType("application/json");
		resp.getWriter().print(withoutNullObjects().toJson(stockHistory));
	}
}
