package controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserDOA;
import model.UserValidate;


public class LoginServelet extends HttpServlet {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		user.setUid(request.getParameter("uid"));
		user.setUpass(request.getParameter("upass"));
		List<String> errors=new LinkedList<String>();
		request.setAttribute("errors",errors);
		request.setAttribute("uId", user.getUid());
		
		UserValidate.loginVerify(user,errors);
		if(errors.isEmpty()) {
			UserDOA dao=new UserDOA();
			try {
			user.setuCID(dao.loginVerify(user));
			
			
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(10000);
			session.setAttribute("user",user);
			String uid=user.getUid();			
			String role=user.getUrole();
			
			

			ServletContext context=getServletContext();
			Map<String,User> users=(Map<String,User>)context.getAttribute("users");
			context.setAttribute("uid",uid);
			context.setAttribute("role",role);
			
			if(users==null) {
				users=new HashMap<>();
				context.setAttribute("users",users);
			}
			users.put(user.getUid(),user);
			//users.put(user.getuCID(),user);
			if(user.getUrole()==null)
			{
				errors.add("Invalid UserID or Password");
				RequestDispatcher dis=request.getRequestDispatcher("/com/login.jsp");
				dis.forward(request, response);
				return;
			}
			
			session.setAttribute("uid",user.getUid());
			if(user.getUrole()!=null&&user.getUrole().equalsIgnoreCase("admin")) {
				RequestDispatcher dis=request.getRequestDispatcher("/mgmt/admin.jsp");
				dis.forward(request, response);
				return;
			}
			RequestDispatcher dis=request.getRequestDispatcher("/secureAccess/loadPage.jsp");
			dis.forward(request, response);
			return;
			}catch(InputMismatchException e) {
				RequestDispatcher dis=request.getRequestDispatcher("/com/login.jsp");
				dis.forward(request, response);
				return;
			}catch(SQLException e) {
				RequestDispatcher dis=request.getRequestDispatcher("/com/error.jsp");
				dis.forward(request, response);
				return;
			}
			
		}
		RequestDispatcher dis=request.getRequestDispatcher("/com/login.jsp");
		dis.forward(request, response);
		return;
	
	}

}
