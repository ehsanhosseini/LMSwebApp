package com.gcit.lms.cli;



import com.gcit.lms.dao.BranchDAO;

//import com.gcit.lms.dao.Connector;
import com.gcit.lms.entity.*;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.ConnectionUtil;
import com.gcit.lms.service.LibrarianService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarianOption {
	
	private LibrarianService librarianService = new LibrarianService();
	Scanner scan = new Scanner(System.in);

	public LibrarianOption() {
		
	}
	
	public void firstMenu() {
//		  
//		System.out.println("1)	Enter Branch you manage");  
//	    System.out.println("2)	Quite to previous");
//	    int manageBranch = scan.nextInt( );
	    secondMenu(); 
	}

	public void secondMenu() {
				   	       
      List<Branch> allBranch = null;
   
      boolean loop = true;
      while(loop) {
    	  int count = 0;
    	  System.out.println("1)	Enter Branch you manage");  
  	    System.out.println("2)	Quite to previous");
  	    int manageBranch = scan.nextInt( );
   	  switch(manageBranch) {
   	  
   	  case 1:
   		try {
			allBranch = librarianService.readAllBranch();
			
			for (Branch branch:allBranch) {
				count++;
				String BranchName = branch.getName();
				String BranchAddress = branch.getAddress();
				System.out.print(count  + ") ");
		        System.out.print(BranchName);
		        System.out.println(", " + BranchAddress);		       		        
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  
   		count++;
    	System.out.print( count + ") ");
	    System.out.println("Quit to previous ");
	    
	    
   		this.third(allBranch, manageBranch);
   		
   		break;
   		
   	  case 2:
	  
//		LibraryManagementApp.MAIN_MENU(); 
		loop = false;
	    break;
    
   	  }
      }
   	
	}
	
	// chose a Branch or quite
	public void third(List<Branch> branchList, Integer manageBranch) {
		
		int branchNr = scan.nextInt( );
		int count = branchList.size();
		
		// if chose the last option 'quite to previous'
		if (branchNr == count+1) {
			return;
			//this.firstMenu();
		}
		
		int countBranch = 0;
		int branchId = 0;
		Branch branchlistItem = branchList.get(branchNr-1);
		
		fourth(branchlistItem,manageBranch);		
	}
	
	public void fourth(Branch branch, Integer manageBranch) {
		
		System.out.println("1)	Update the details of the Library"); 
		System.out.println("2)	Add copies of Book to the Branch");
		System.out.println("3)	Quit to previous");
		fifth(branch,manageBranch);
	}
	
	public void fifth(Branch branch, Integer manageBranch) {
		int libraryOption = scan.nextInt( );
		boolean loop = true;
		while(loop) {
		switch (libraryOption) {
		
		case 1: 
			int branchId = branch.getBranchId();
			String branchName = branch.getName();
			System.out.println("You have chosen to update the Branch with Branch Id:" + branchId +" and "
			+ "Branch Name: "+ branchName + ".");
			System.out.println("Enter ‘quit’ at any prompt to cancel operation.");

										 
			System.out.println("Please enter new branch name or enter N/A for no change:");
			String newBranchName = scan.next( );
			if(!newBranchName.equals("N/A")) {
				if (newBranchName.equals("quit")) {
					return;
				} else {
					branch.setName(newBranchName);
					try {					
						librarianService.updateBranch(branch);
						System.out.println("Branch name is successfully updated");							
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			} 
			System.out.println("Please enter new branch address or enter N/A for no change:");
			String newBranchAddress = scan.next( );
			
			if(!newBranchAddress.equals("N/A")) {
				if (newBranchName.equals("quit")) {System.out.println(newBranchName);
					return;
				} else {
					branch.setAddress(newBranchAddress);
					try {					
						librarianService.updateBranch(branch);
						System.out.println("Branch address is successfully updated");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
			
			this.fourth(branch,manageBranch);
			
		case 2: 
			System.out.println("Pick the Book you want to add copies of, to your branch:");
			List<BookCopies> bookCopy = null;
			try {
				List<Book> bookList = librarianService.readAllBooksInBranch(branch);
				int count = 0;
				for (Book book:bookList) {
					count++;
					String title = book.getTitle();
					List<Author> authors = book.getAuthors();
					System.out.print(count  + ") ");
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
				count++;
				 System.out.print( count + ") ");
				 System.out.println("Quit to cancel operation ");
				 
				 int chosenBook = scan.nextInt( );
				 // quit
				 if (chosenBook >= count) {
					// this.fourth(branch, manageBranch);
					 return;
				 }
				 Book book = bookList.get(chosenBook-1);
				 System.out.println("the book is : " + book.getTitle());

				 // is a List but just one Item
				 bookCopy = librarianService.readBookCopies(book, branch);
				 
				 System.out.print("Existing number of copies: ");
				 int existCopies = 0;
				 BookCopies bookCopyObject = new BookCopies();
				 for (BookCopies bc:bookCopy) {
						existCopies = bc.getNoCopies(); 
						bookCopyObject = bc;
				 }
				 
				 System.out.println(existCopies);
				 
				 // enter new number
				 System.out.print("Enter new number of copies:");
				 int newCopies = scan.nextInt( );
				 bookCopyObject.setNoCopies(newCopies);
				 librarianService.updateBookCopies(bookCopyObject, book, branch);
				 this.fourth(branch,manageBranch);
				 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			break;
			
		case 3: 
			loop = false;
			break;
		}
		}
		
	}
}
