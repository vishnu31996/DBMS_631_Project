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

import model.AdminDOA;
import model.Cart;
import model.ProfileDOA;
import model.TransactionModel;
import model.User;

public class AdminData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();

		List<String> data1 = new LinkedList<String>();
		List<String> data2 = new LinkedList<String>();
		List<String> data3 = new LinkedList<String>();
		List<String> data4 = new LinkedList<String>();
		List<String> data5 = new LinkedList<String>();
		List<String> data6 = new LinkedList<String>();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String uid = user.getUid();
				String cid = user.getuCID();

				String d1 = request.getParameter("date1");
				String d2 = request.getParameter("date2");

				try {

					AdminDOA d = new AdminDOA(d1, d2);
					data1 = d.getFeqprod();
					data2 = d.getAdmin2();
					data3 = d.getAdmin3();
					data4 = d.getAdmin4();
					data5 = d.getAdmin5();
					data5 = d.getAdmin6();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			} else {
				System.out.println("----" + user);
			}

			request.setAttribute("data1", data1);
			request.setAttribute("data2", data2);
			request.setAttribute("data3", data3);
			request.setAttribute("data4", data4);
			request.setAttribute("data5", data5);
			request.setAttribute("data6", data6);
			RequestDispatcher dis = request.getRequestDispatcher("/mgmt/admin.jsp");
			dis.forward(request, response);
			return;

		}
	}
}