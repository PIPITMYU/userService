package com.user.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class LoginFilter implements Filter{
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		  HttpServletRequest servletRequest = (HttpServletRequest) request;
		  HttpServletResponse servletResponse = (HttpServletResponse) response;
//		  servletResponse.setContentType("application/json; charset=utf-8");
		  servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		  servletResponse.setCharacterEncoding("UTF-8");
		  servletRequest.setCharacterEncoding("UTF-8");
		  servletResponse.setContentType("text/html;charset=UTF-8");
		  
		  chain.doFilter(request, response);

	}
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
