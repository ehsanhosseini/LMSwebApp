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
import com.gcit.lms.entity.Book;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminServletBook
 */
@WebServlet({"/addBook","/deleteBook","/editBook"})
public class AdminServletBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServletBook() {
        super();

    }

    public AdminService adminService = new AdminService();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	/*	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = new Book();
		book.setBookId(bookId);
		try {
			adminService.saveBook(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/viewbook.jsp");
		rd.forward(request, response);
	}
	*/
		
	
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		Integer bookId = null;
		String forwardPath = "/viewbook.jsp";
		switch (reqUrl) {
		case "/editBook":
			try {
				request.setAttribute("book",
						adminService.getBookByPK(Integer.parseInt(request.getParameter("bookId"))));
				forwardPath = "editBook.jsp";
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "/deleteBook":
			bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new Book();
			book.setBookId(bookId);
			try {
				adminService.saveBook(book);
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
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Book book = new Book();
		book.setTitle(request.getParameter("title"));
		if (request.getParameter("bookId") != null) {
			book.setBookId(Integer.parseInt(request.getParameter("bookId")));
		}
		// String[] bookIds = request.getParameterValues("bookIds");
		// for(String s: bookIds){
		// System.out.println(s);
		// }
		try {
			adminService.saveBook(book);
			request.setAttribute("message", "Book added successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "Book add failed");
		}
		request.setAttribute("message", "Book added successfully");

		RequestDispatcher rd = request.getRequestDispatcher("/viewbook.jsp");
		rd.forward(request, response);
			
		

	}

	}


