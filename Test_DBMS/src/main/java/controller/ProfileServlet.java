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

import model.Product;
import model.ProductDOA;
import model.ProfileDOA;
import model.Shipping;
import model.User;
import model.card;

public class ProfileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();
		List<Shipping> shipDetails = new LinkedList<Shipping>();
		List<card> cardDetails = new LinkedList<card>();
		List<User> userDetails = new LinkedList<User>();

		
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String uid = user.getUid();
				String cid = user.getuCID();

				ProfileDOA doa = new ProfileDOA(uid, cid);

				try {
					shipDetails.addAll(doa.getShipping_Address());
					cardDetails.addAll(doa.getCREDIT_CARD());
					userDetails.addAll(doa.getCUSTOMER());

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("----" + user);
			}
			request.setAttribute("shipDetails", shipDetails);
			request.setAttribute("cardDetails", cardDetails);
			request.setAttribute("userDetails", userDetails);
			request.setAttribute("Shiperrors", request.getAttribute("Shiperrors"));
			request.setAttribute("Shiperrors", request.getAttribute("ShipSuccess"));
			
			RequestDispatcher dis = request.getRequestDispatcher("/customer/ShippingPayment.jsp");
			dis.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();
		List<Shipping> shipDetails = new LinkedList<Shipping>();
		List<card> cardDetails = new LinkedList<card>();
		List<User> userDetails = new LinkedList<User>();

		
	
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String uid = user.getUid();
				String cid = user.getuCID();

				ProfileDOA doa = new ProfileDOA(uid, cid);

				try {
					shipDetails.addAll(doa.getShipping_Address());
					cardDetails.addAll(doa.getCREDIT_CARD());
					userDetails.addAll(doa.getCUSTOMER());

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("----" + user);
			}
			request.setAttribute("shipDetails", shipDetails);
			request.setAttribute("cardDetails", cardDetails);
			request.setAttribute("userDetails", userDetails);
			request.setAttribute("Shiperrors", request.getAttribute("Shiperrors"));
			RequestDispatcher dis = request.getRequestDispatcher("/customer/ShippingPayment.jsp");
			dis.forward(request, response);
			return;
		}
	}
}
