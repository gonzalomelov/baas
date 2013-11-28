package uy.com.group05.baasadmin.pl.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.com.group05.baasadmin.pl.beans.UserSessionManagementBean;

public class FiltroLogueado implements Filter {

	
	private UserSessionManagementBean userSessionManagementBean;

	public UserSessionManagementBean getUserSessionManagementBean(ServletRequest request) {
		 HttpServletRequest req = (HttpServletRequest) request;
		UserSessionManagementBean bean = (UserSessionManagementBean) req.getSession()
				.getAttribute("userSessionManagementBean");
		return bean;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String contextPath = req.getContextPath();
		
		String home = contextPath + "/index.xhtml";
		
		String loginURL = contextPath + "/pages/users/login.xhtml"; 
		
		String register = contextPath + "/pages/users/register.xhtml";
		
		String downlaod = contextPath + "/download.xhtml"; 
		
		userSessionManagementBean = getUserSessionManagementBean(request);
		
		Boolean loggedIn = false;
		if(userSessionManagementBean != null && userSessionManagementBean.getUser() != null)
			loggedIn = true;
		
		
		boolean paginaPublica = false;
		if (req.getRequestURI().equals(contextPath+"/") ||
			req.getRequestURI().equals(home) ||
			req.getRequestURI().equals(loginURL) ||
			req.getRequestURI().equals(downlaod) ||
			req.getRequestURI().equals(register))
		{
			paginaPublica = true;
		}
		
		if(!loggedIn && !paginaPublica) {       
	        res.sendRedirect(loginURL);
	    } else {
			chain.doFilter(request, response);
	    }		
		
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
