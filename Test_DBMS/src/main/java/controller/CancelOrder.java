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

import model.ProductDOA;
import model.ProfileDOA;
import model.User;

public class CancelOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("***********");
		List<String> cartF = new LinkedList<String>();
		List<String> cartS = new LinkedList<String>();
		request.setAttribute("CartF", cartF);
		request.setAttribute("CartS", cartF);

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String uid = user.getUid();

				String Bid = request.getParameter("Bid");
			
				
				String cid = user.getuCID();

				try {

					ProfileDOA d = new ProfileDOA(uid, cid);
					d.cancelOrder(Bid);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					cartF.add("Error Adding product to cart");
				}
				cartS.add("Successfully added");
			} else {
				System.out.println("----" + user);
			}

			RequestDispatcher dis = request.getRequestDispatcher("/TransactionHistory");
			dis.forward(request, response);
			return;

		}
	}
}
