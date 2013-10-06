package uy.com.group05.baasadmin.pl.controllers;

import java.io.IOException;

import javax.faces.bean.ManagedProperty;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.com.group05.baasadmin.pl.beans.UserSessionManagementBean;
import uy.com.group05.baasadmin.pl.models.UserModel;

public class FiltroDeslogueado implements Filter {

	
	private UserSessionManagementBean userSessionManagementBean;

	public UserSessionManagementBean getUserSessionManagementBean(ServletRequest request) {
		 HttpServletRequest req = (HttpServletRequest) request;
		UserSessionManagementBean bean = (UserSessionManagementBean) req.getSession()
				.getAttribute("userSessionManagementBean");
		return bean;
	}

	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		
		userSessionManagementBean = getUserSessionManagementBean(request);
		
		if (userSessionManagementBean != null) {
			UserModel user = userSessionManagementBean.getUser();

			if (user != null) {
				// User is logged in, so redirect.

				// System.out.println("usuarioLogueado");

				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(req.getContextPath()
						+ "/pages/dashboard/Index.xhtml");

			}
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
