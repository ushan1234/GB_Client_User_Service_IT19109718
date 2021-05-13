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
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User itemObj = new User();
		String output = itemObj.AddUser(request.getParameter("fname"), 
				request.getParameter("lname"), 
				request.getParameter("age"), 
				request.getParameter("pnumber"), 
				request.getParameter("address"), 
				request.getParameter("email"), 
				request.getParameter("password"),
				request.getParameter("category"));
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	// Convert request parameters to a Map
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

		 
		 
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User itemObj = new User();
		Map paras = getParasMap(request); 
		 String output = itemObj.updateUser(paras.get("hidItemIDSave").toString(), 
		 paras.get("fname").toString(), 
		 paras.get("lname").toString(), 
		 paras.get("age").toString(), 
		 paras.get("pnumber").toString(), 
		 paras.get("address").toString(), 
		 paras.get("email").toString(),
		 paras.get("password").toString(), 
		 paras.get("category").toString());
		response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User itemObj = new User();
		Map paras = getParasMap(request); 
		 String output = itemObj.deleteUser(paras.get("idusers").toString()); 
		response.getWriter().write(output);
	}

}
