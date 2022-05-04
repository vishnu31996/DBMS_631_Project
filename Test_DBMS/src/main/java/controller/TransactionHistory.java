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

import model.Cart;
import model.ProfileDOA;
import model.TransactionModel;
import model.User;

public class TransactionHistory extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();

		List<TransactionModel> transactionDetails = new LinkedList<TransactionModel>();
		List<Cart> cartDetails = new LinkedList<Cart>();
		List<String> errors = new LinkedList<String>();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String uid = user.getUid();
				String cid = user.getuCID();

				try {

					ProfileDOA d = new ProfileDOA(uid, cid);
					if (!d.verifyShipingAddress()) {
						errors.add("Add Shipping Address!");
					}
					if (!d.verifycardDetails()) {
						errors.add("Add Payment Details!");
					}

					if (errors.isEmpty()) {
						transactionDetails = d.getTransactionHistory();
						
					} else {
						request.setAttribute("Shiperrors", errors);

						RequestDispatcher dis = request.getRequestDispatcher("/ProfileServlet");
						dis.forward(request, response);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			} else {
				System.out.println("----" + user);
			}
			Double Tcost = 0.0;
			for (Cart c : cartDetails) {
				Tcost += Double.valueOf(c.getpPricesold());
			}
			
			request.setAttribute("transactionDetails", transactionDetails);
			RequestDispatcher dis = request.getRequestDispatcher("/customer/Transaction.jsp");
			dis.forward(request, response);
			return;

		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();

		List<TransactionModel> transactionDetails = new LinkedList<TransactionModel>();
		List<Cart> cartDetails = new LinkedList<Cart>();
		List<String> errors = new LinkedList<String>();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String uid = user.getUid();
				String cid = user.getuCID();

				try {

					ProfileDOA d = new ProfileDOA(uid, cid);
					if (!d.verifyShipingAddress()) {
						errors.add("Add Shipping Address!");
					}
					if (!d.verifycardDetails()) {
						errors.add("Add Payment Details!");
					}

					if (errors.isEmpty()) {
						transactionDetails = d.getTransactionHistory();
						
					} else {
						request.setAttribute("Shiperrors", errors);

						RequestDispatcher dis = request.getRequestDispatcher("/ProfileServlet");
						dis.forward(request, response);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			} else {
				System.out.println("----" + user);
			}
			Double Tcost = 0.0;
			for (Cart c : cartDetails) {
				Tcost += Double.valueOf(c.getpPricesold());
			}
			
			request.setAttribute("transactionDetails", transactionDetails);
			RequestDispatcher dis = request.getRequestDispatcher("/customer/Transaction.jsp");
			dis.forward(request, response);
			return;

		}
	}
}
