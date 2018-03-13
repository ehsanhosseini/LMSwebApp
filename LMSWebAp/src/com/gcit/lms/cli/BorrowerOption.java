package com.gcit.lms.cli;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;


public class BorrowerOption {
	
	private BorrowerService borrowerService = new BorrowerService();
	Scanner scan = new Scanner(System.in);
	
	public BorrowerOption() {}
	
	public void cardNo() {
	
		List<Borrower> borrower = null;
		int count = 0;
		int cardNo = 0;

		// as long as enter a non valid card nr.
		while ((borrower == null || borrower.isEmpty()) && count != 10) {
			
			count++;
			System.out.println("Enter your Card Number:");  
		   	cardNo = scan.nextInt( );
		   		   			   	
			try {
				borrower = borrowerService.readBorrowerByCardNo(cardNo);
				
			} catch (SQLException e) {	
				e.printStackTrace();
			}				   			
		} 		
			this.firstBorrowMenu(cardNo);				
	}

	public void firstBorrowMenu(int cardNo) {
				
			System.out.println("1)	Check out a book");
		   	System.out.println("2)	Return a Book");
		   	System.out.println("3)	Quit to Previous");
		   	this.secondBorrowMenu(cardNo);
	}
	
	public void secondBorrowMenu(int cardNo) {
		   	int borrowOption = scan.nextInt( );
		   	List<Branch> branchList = new ArrayList<Branch>();
		   	boolean loop = true;
		   	while(loop) {
		   	switch(borrowOption) {
		   	  
		   	  case 1:
		   		  int count = 0;
				  System.out.println("Pick the Branch you want to check out from:");
				  try {
					branchList = borrowerService.readAllBranch();
					for(Branch branch:branchList) {
						count++;
						String branchName = branch.getName();
						String branchAddress = branch.getAddress();
						System.out.print(count  + ") ");
				        System.out.print(branchName);
				        System.out.println(", " + branchAddress);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  count++;
			    	System.out.print( count + ") ");
				    System.out.println("Quit to previous ");
				    int chosenBranch = scan.nextInt( );
				    if (chosenBranch == count) {
				    	//return;
				    	this.firstBorrowMenu(cardNo);
				    } else {
				    	

				    	Branch branch = branchList.get(chosenBranch-1);
				    	
				    	List<Book> bookList = new ArrayList<Book>();
				    	try {
							bookList = borrowerService.readAllBooksAvailable(branch);
							int countNr = 0;
							for (Book book:bookList) {
								countNr++;
								String title = book.getTitle();
								List<Author> authors = book.getAuthors();
								System.out.print(countNr  + ") ");
					            System.out.print(title);
					            
					            if (authors.size() > 0) {
					            	System.out.print(" by");
					            }
					            for (Author author:authors) {
					            	String authorName = author.getAuthorName();
					            	System.out.print(" " + authorName);
					            }
					            System.out.println();
							}
							countNr++;
							 System.out.print( countNr + ") ");
							 System.out.println("Quit to cancel operation ");
							 int chosenBook = scan.nextInt( );
							 if (chosenBook == countNr) {
								 this.firstBorrowMenu(cardNo);
						     } else {
							     Book book = bookList.get(chosenBook-1);
							     //  current_timeStamp
								 String dateOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
								  
								  // current_timeStamp_dueDate
								  Calendar cal = Calendar.getInstance();
								  cal.add(Calendar.DATE, 10);
								  String dueDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
								  
								 // String to Date 
//								 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//							     LocalDate localDate = LocalDate.parse(dueDate, formatter);
//								 System.out.println(localDate);
								 
							     borrowerService.saveBookLoans(book, branch, cardNo, dateOut, dueDate);
							     System.out.println("You checked out a book successfully");
							     this.firstBorrowMenu(cardNo);
						     }
							 
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	
				    }
		   		  break;
		   		  
		   	  case 2:
		   		  int countLoan = 0;
				  System.out.println("Pick the Branch you want to return a book back:");
				  try {
					List<Branch> branchListLoanbook = borrowerService.readBranchHaveLoanedBook(cardNo);
					for(Branch branch:branchListLoanbook) {
						countLoan++;
						String branchName = branch.getName();
						String branchAddress = branch.getAddress();
						System.out.print(countLoan  + ") ");
				        System.out.print(branchName);
				        System.out.println(", " + branchAddress);
				        
				        
					}
					countLoan++;
					System.out.print( countLoan + ") ");
					System.out.println("Quit to previous ");
					
				    int chosenBranchForReturn = scan.nextInt( );
				    Branch branch = branchListLoanbook.get(chosenBranchForReturn-1);
				    
				    
				    if (chosenBranchForReturn == countLoan) {
				    	this.firstBorrowMenu(cardNo);
				    } 
				    List<Book> bookList = new ArrayList<Book>();
			    	bookList = borrowerService.readBooksInBranchInBorrowerCheckedout(branch, cardNo);
			    	int countNr = 0;
					for (Book book:bookList) {
						countNr++;
						String title = book.getTitle();
						List<Author> authors = book.getAuthors();
						System.out.print(countNr  + ") ");
			            System.out.print(title);
			            
			            if (authors.size() > 0) {
			            	System.out.print(" by");
			            }
			            for (Author author:authors) {
			            	String authorName = author.getAuthorName();
			            	System.out.print(" " + authorName);
			            }
			            System.out.println();
					}
					int chosenBookForReturn = scan.nextInt( );
					Book chosenBook = bookList.get(chosenBookForReturn-1);
					
					String dateIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
					
					List<BookLoans> bookLoansList = new ArrayList<BookLoans>();
					// retieve bookloan by ids to get DateOut
			//bookLoansList = borrowerService.readBookLoanByIds(chosenBook.getBookId(),branch.getBranchId(),cardNo);
//					Date dateOut = null;
//					for(BookLoans bookLoan: bookLoansList) {
//						dateOut =  (Date) bookLoan.getDateOut();
//					}System.out.println(dateOut);
					borrowerService.updateDateInBookLoans(dateIn, chosenBook, branch, cardNo);
					//borrowerService.updateDateInBookLoans(chosenBook, branch, cardNo);
			    	
				  } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				  }				 			 
		   		  break;
		   		  
		   	  case 3:
		   		  //LibraryManagementApp.MAIN_MENU();
		   		loop = false;
		   		  break;
		   		  

		   	}
	   	}
	}

}
