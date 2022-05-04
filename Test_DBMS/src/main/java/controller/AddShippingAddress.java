package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InputMismatchException;
import model.ProfileDOA;
import model.RegistrationDAO;
import model.Shipping;
import model.User;

public class AddShippingAddress  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();
		List<String> errors = new LinkedList<String>();
		List<String> success = new LinkedList<String>();
		
		if (session != null) {
			User user = (User) session.getAttribute("user");

			if (user != null) {
				String uid = user.getUid();

				String cid = user.getuCID();
				Shipping ship=new Shipping();
				ship.setSAName(request.getParameter("sAddName"));
				ship.setRecepientName(request.getParameter("sRName"));
				ship.setStreet(request.getParameter("sSName"));
				ship.setSNumber(request.getParameter("sSNum"));
				ship.setCity(request.getParameter("sCity"));
				ship.setZip(request.getParameter("sZip"));
				ship.setState(request.getParameter("sState"));
				ship.setCountry(request.getParameter("sCountry"));
				
				ProfileDOA pDoa = new ProfileDOA(uid, cid);
				try {
					pDoa.updateShipping_Address(ship);
					success.add("Shipping Address Updated Successfully!");
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errors.add("Oh Snap! Some error occured. Try to change a few things up and try again.");
				}

			} else {
				System.out.println("----" + user);
			}
			
			
			request.setAttribute("Shiperrors", errors);
			request.setAttribute("ShipSuccess", success);
			RequestDispatcher dis = request.getRequestDispatcher("/ProfileServlet");
			dis.forward(request, response);
			return;
		}
	}
}
