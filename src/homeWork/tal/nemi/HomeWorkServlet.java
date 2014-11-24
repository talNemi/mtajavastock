package homeWork.tal.nemi;

import java.io.IOException;


import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeWorkServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		int num1;
		int num2;
		int num3;
		num1 = 4;
		num2 = 3;
		num3 = 7;
		int result = (num1 + num2) * num3;
		String resultStr = new String("Result of "+"( "+num1+"+"+num2+" )"+"*"+num3+"="+result+"<br/><br/>");
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
		
		int radius = 50;
		double area = Math.PI * Math.pow(radius, 2);
		String line1 = new String ("Area of circle with radius "+(radius)+" is: "+(area)+" square-cm<br/>");
		
		int Hypotenuse = 50;
		double opposite;
		double angleB = Math.toRadians(30);
		opposite = Math.sin(angleB) * Hypotenuse;
		String line2 = new String("Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is: "+(opposite)+ "cm<br/>");

		int base = 20;
		int exp = 13;
		double resultPow = Math.pow(base, exp);
		String line3 = new String("Power of "+base+ " with exp of "+exp+" is: "+(resultPow)+"<br/>");

		String finalStr = (line1 + "<br>" + line2 + "<br>" +line3);
		resp.getWriter().println(finalStr);
	}
}
