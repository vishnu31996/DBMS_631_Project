package controller;

	

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;


	public class SecureAccessFilter  implements Filter {
		public void destroy() {
		}

		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			HttpServletRequest request1=(HttpServletRequest)request;
			HttpSession session=request1.getSession();
			if(session!=null) {
				User user=(User)session.getAttribute("user");
				if(user!=null) {
					
					chain.doFilter(request, response);
					return;
				}
			}
			List<String> errors=new LinkedList<>();
			errors.add("Please login before access pages");
			request.setAttribute("errors",errors);
			request.getRequestDispatcher("/com/login.jsp").forward(request1, response);
		}

		public void init(FilterConfig fConfig) throws ServletException {
			
		}

	}


