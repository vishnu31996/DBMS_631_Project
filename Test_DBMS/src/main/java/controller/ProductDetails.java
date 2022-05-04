package controller;

import java.io.IOException;
import java.rmi.ServerException;
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
import model.User;

public class ProductDetails extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Product> prodDetails = new LinkedList<Product>();

		request.setAttribute("prodDetails", prodDetails);
		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();
		User user = (User) session.getAttribute("user");
		String cid = user.getuCID();
		ProductDOA pDOA = new ProductDOA(cid);		
		try {
			prodDetails.addAll(pDOA.getProductDetails());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("/customer/Home.jsp");
		dis.forward(request, response);
		return;

	}
}
