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
		String resultStr = new String("<h1>Result of"+"( "+num1+"+"+num2+" )"+"*"+num3+"="+result+"</h1>");
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
		
		double radius = 50;
		double area = Math.PI * Math.pow(radius, 2);
		String line1 = new String ("<h1>Area of circle with radius "+(radius)+" is: "+(area)+"square­cm<h1>");
		
		double Hypotenuse = 50;
		double opposite;
		double angleB = 30;
		opposite = Math.sin(angleB) * Hypotenuse;
		String line2 = new String("<h1>Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is: "+(opposite)+ "cm<h1>");

		double base = 20;
		double exp = 13;
		double resultPow = Math.pow(base, exp);
		String line3 = new String("<h1>Power of 20 with exp of 13 is "+(resultPow)+"<h1>");

		String finalStr = (line1 + "<br>" + line2 + "<br>" +line3);
		resp.getWriter().println(finalStr);
	}
}
