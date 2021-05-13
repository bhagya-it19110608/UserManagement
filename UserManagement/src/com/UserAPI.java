package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAPI
 */
@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet {
	
	
	User userObj =new User();
       
    
    public UserAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = userObj.insertUser(request.getParameter("firstName"), 
				 request.getParameter("lastName"), 
				request.getParameter("email"), 
				request.getParameter("gender"),
				request.getParameter("occupation"),
				request.getParameter("phone"),
				request.getParameter("username"),
				request.getParameter("password")); 
				response.getWriter().write(output); 
				
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = userObj.updateUser(paras.get("hiduIdSave").toString(), 
		 paras.get("firstName").toString(), 
		 paras.get("lastName").toString(), 
		paras.get("email").toString(), 
		paras.get("gender").toString(),
		paras.get("occupation").toString(),
		paras.get("phone").toString(),
		paras.get("username").toString(),
		paras.get("password").toString()); 
		response.getWriter().write(output); 
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = userObj.deleteUser(paras.get("uId").toString()); 
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	
	

}
