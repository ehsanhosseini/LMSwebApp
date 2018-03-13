package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.entity.Branch;

/**
 * Servlet implementation class AdminServletBorrower
 */
@WebServlet({"/addBranch","/deleteBranch","/editBranch","/pageBranches"})
public class AdminServletBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServletBranch() {
        super();
    }
    public AdminService adminService = new AdminService();
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		Integer branchId = null;
		String forwardPath = "/viewbranches.jsp";
		switch (reqUrl) {
		case "/editBranch":
			try {
				request.setAttribute("branch",
						adminService.readBranchByPK(Integer.parseInt(request.getParameter("branchId"))));
				forwardPath = "editbranch.jsp";
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "/deleteBranch":
			branchId = Integer.parseInt(request.getParameter("branchId"));
			Branch branch = new Branch();
			branch.setBranchId(branchId);
			try {
				adminService.saveBranch(branch);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Branch branch = new Branch();
		branch.setBranchName(request.getParameter("branchName"));
		branch.setBranchName(request.getParameter("address"));
		if (request.getParameter("branchId") != null) {
			branch.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		}
		
		try {
			adminService.saveBranch(branch);
			request.setAttribute("message", "Branch added successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "Branch add failed");
		}
		request.setAttribute("message", "Branch added successfully");

		RequestDispatcher rd = request.getRequestDispatcher("/viewbranches.jsp");
		rd.forward(request, response);
		
		
		
	}
}
	