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

/**
 * Servlet implementation class AdminServlet
 */

@WebServlet({"/addAuthor","/deleteAuthor","/editAuthor","/pageAuthors"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public AdminServlet() {
        super();
      
    }
    public AdminService adminService = new AdminService();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		Integer authorId = null;
		String forwardPath = "/viewauthors.jsp";
		switch (reqUrl) {
		case "/editAuthor":
			try {
				request.setAttribute("author",
						adminService.getAuthorsByPK(Integer.parseInt(request.getParameter("authorId"))));
				forwardPath = "editauthor.jsp";
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "/deleteAuthor":
			authorId = Integer.parseInt(request.getParameter("authorId"));
			Author author = new Author();
			author.setAuthorId(authorId);
			try {
				adminService.saveAuthor(author);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "/pageAuthors":
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("authors", adminService.getAuthors(null, pageNo));
			} catch (SQLException e) {
				request.setAttribute("authors", null);
				e.printStackTrace();
			}
			break;
		default:
			break;
		
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		Author author = new Author();
		author.setAuthorName(request.getParameter("authorName"));
		if (request.getParameter("authorId") != null) {
			author.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
		}
		
		try {
			adminService.saveAuthor(author);
			request.setAttribute("message", "Author added successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "Author add failed");
		}
		request.setAttribute("message", "Author added successfully");

		RequestDispatcher rd = request.getRequestDispatcher("/viewauthors.jsp");
		rd.forward(request, response);
		
		
	}

}
