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
import model.ProductDOA;
import model.ProfileDOA;
import model.RegistrationDAO;
import model.User;

public class UpdateRegServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpSession session = request1.getSession();
		if (session != null) {
			User user = (User) session.getAttribute("user");

			if (user != null) {
				String uid = user.getUid();

				String cid = user.getuCID();
				
				user.setuFname(request.getParameter("uFname"));
				user.setuLname(request.getParameter("uLname"));
				user.setuAddress(request.getParameter("uAddress"));
				user.setuPhone(request.getParameter("uPhone"));
				user.setSILVER_AND_ABOVE(request.getParameter("SAB"));
				ProfileDOA pDoa = new ProfileDOA(uid, cid);
				try {
					pDoa.updateReg(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println("----" + user);
			}

			RequestDispatcher dis = request.getRequestDispatcher("/ProfileServlet");
			dis.forward(request, response);
			return;
		}
	}
}
