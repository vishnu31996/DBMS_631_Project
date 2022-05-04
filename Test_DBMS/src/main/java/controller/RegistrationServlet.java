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
import model.RegistrationDAO;
import model.User;
import model.UserValidate;


public class RegistrationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		String id = request.getParameter("uid");
		
		user.setUid(request.getParameter("uid"));
		user.setUpass(request.getParameter("upass"));
		user.setUpass1(request.getParameter("rePass"));
		user.setUrole("customer");
		user.setuFname(request.getParameter("uFname"));
		user.setuLname(request.getParameter("uLname"));
		user.setuAddress(request.getParameter("uAddress"));
		user.setuPhone(request.getParameter("uPhone"));
		user.setSecurityQ(request.getParameter("uSecurityQ"));
		user.setSecurityA(request.getParameter("uSecurityA"));
		user.setSILVER_AND_ABOVE(request.getParameter("SAB"));
		
		LinkedList errors=new LinkedList<>();
		request.setAttribute("errors",errors);
		

		String pass=request.getParameter("upass");
		String pass1=request.getParameter("rePass");

	
		if(pass.equals(pass1))
		{
			
		try {
		HttpSession session = request.getSession();
		session.setAttribute("userid",id);

		RegistrationDAO dao = new RegistrationDAO();
		try {
			int i=dao.addRegistration(user);
			if(i==0) {
				errors.add("User id already registered!!");
				RequestDispatcher dis = request.getRequestDispatcher("/com/registration.jsp");
				dis.forward(request, response);
			}

		} catch (InputMismatchException e) {
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		}
		catch(Exception e)
		{
			errors.add("Email Id Already Exists");
			RequestDispatcher dis = request.getRequestDispatcher("/com/login.jsp");
			dis.forward(request, response);
			return;
		}
		}else {
			errors.add("Passwords doesn't match");
			RequestDispatcher dis = request.getRequestDispatcher("/com/registration.jsp");
			dis.forward(request, response);
			
		}
		List<String> success=new LinkedList<String>();
		request.setAttribute("success",success);
		success.add("Successfully Registered!");
		success.add("Please Login to continue");
		
		RequestDispatcher dis=request.getRequestDispatcher("/com/login.jsp");
		dis.forward(request, response);
		return;
	}
	}
