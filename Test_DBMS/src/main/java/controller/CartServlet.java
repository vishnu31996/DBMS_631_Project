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

public class CartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Cart> cartDetails = new LinkedList<Cart>();
		float cartTotal=0;
		String cStatus=null;
		
		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String cid = user.getuCID();
				String uid = user.getUid();
				
				System.out.println(cid+"-"+uid);
				ProductDOA pDOA = new ProductDOA(cid);
				try {
					cartDetails.addAll(pDOA.getCartDetails(cid));
					cStatus=pDOA.getSilverAndAbove();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		for(Cart c:cartDetails) {
			cartTotal+=Float.parseFloat(c.getpPricesold());
		}
		request.setAttribute("cartDetails", cartDetails);
		request.setAttribute("cartTotal", cartTotal);
		request.setAttribute("cStatus", cStatus);
		System.out.println(cStatus+"- Status");
		RequestDispatcher dis = request.getRequestDispatcher("/customer/Cart.jsp");
		dis.forward(request, response);
		return;
	}

}
