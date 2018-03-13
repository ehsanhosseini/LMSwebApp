package com.gcit.lms.cli;

import java.sql.SQLException;
import java.util.Scanner;

public class LibraryManagementApp {
	

	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		AdminService adminService = new AdminService();
//		Author author = new Author();
//		author.setAuthorName("test");
//		adminService.saveAuthor(author);
		MAIN_MENU();
		
	}	
	public static void MAIN_MENU() {
		
		boolean loop = true;
        while(loop) {
		  System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you.");	      
	      System.out.println("1)	Librarian");
	      System.out.println("2)	Administrator");
	      System.out.println("3)	Borrower");
	      Scanner scan = new Scanner(System.in);
	      
	      int category = scan.nextInt( );
		      
		  switch(category) {
		  
		  case 1:   LibrarianOption lib = new LibrarianOption();
		  			lib.firstMenu();		  			
		  			break;
		  case 2:   AdministratorOption admin = new AdministratorOption();
		  			admin.mainMenu();
		  			break;
		  case 3:   BorrowerOption borrow = new BorrowerOption(); 
		  			borrow.cardNo();
		  			break;		  
		  }
		  
        }
    }
}


