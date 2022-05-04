package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import model.card;

public class AddCard extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("***********");

		String cardS = null;
		request.setAttribute("AddCardSuccess", cardS);
		

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();
		List<String> errors = new LinkedList<String>();
		List<String> success = new LinkedList<String>();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String uid = user.getUid();
				String cid = user.getuCID();
				card uCard = new card();
				uCard.setName(request.getParameter("owner"));
				uCard.setCvv(request.getParameter("cvv"));
				uCard.setCardNumber(request.getParameter("cardNumber"));
				String dMon = request.getParameter("expiration-date-month");
				String yr = request.getParameter("expiration-date-year");
				uCard.setExpDate(yr + "-" + dMon);
				uCard.setCardType(request.getParameter("card-Type"));
				uCard.setBilAddress(request.getParameter("billAddress"));
				
				try {
					
					
					ProfileDOA d = new ProfileDOA(uid, cid);
					if (d.updateCREDIT_CARD(uCard))
						success.add("Credit Card Added Successfully!");
					else
						errors.add("Oh Snap! Some error occured. Try to change a few things up and try again.");

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
