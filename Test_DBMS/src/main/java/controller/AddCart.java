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
import model.Product;
import model.ProductDOA;
import model.User;

public class AddCart extends HttpServlet {

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

				String pid = request.getParameter("Pid");
				String cid=user.getuCID();
				System.out.println("UID -" + uid + " added PID - " + pid);
				ProductDOA pDoa = new ProductDOA(cid);
				try {
					pDoa.updateCart(uid, pid);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					cartF.add("Error Adding product to cart");
				}
				cartS.add("Successfully added");
			} else {
				System.out.println("----" + user);
			}
			
			RequestDispatcher dis = request.getRequestDispatcher("/secureAccess/loadPage.jsp");
			dis.forward(request, response);
			return;

		}
	}
}
