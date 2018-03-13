package com.gcit.lms.cli;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;




public class AdministratorOption {
	
	private AdminService adminService = new AdminService();
	Scanner scan = new Scanner(System.in);
	boolean loop;
	public void mainMenu() {

	   			
			   	System.out.println("1)	Add/Update/Delete Author");
			   	System.out.println("2)	Add/Update/Delete Book");
	   			System.out.println("3)	Add/Update/Delete Publishers");
				System.out.println("4)	Add/Update/Delete Library Branches");
				System.out.println("5)	Add/Update/Delete Borrowers");
				System.out.println("6)	Over-ride Due Date for a Book Loan");
				this.firstManu();
	}
	
	public void firstManu() {
		loop = true;
		while(loop) {
			   	int editOption = scan.nextInt( );			   	
			   	
			   	switch(editOption) {
			   		
			   	case 1:
			   		
			   		System.out.println("1)	Add Author");
			   		System.out.println("2)	Update Author");
			   		System.out.println("3)	Delete Author");

			   		int authorOption = scan.nextInt( );
			   		
			   		switch(authorOption) {
			   		
			   		case 1:
			   			System.out.println("please give a author name");
			   			String authorName = scan.next( );
			   			
				   		Author author = new Author();
				   		author.setAuthorName(authorName);
				   		try {
							adminService.saveAuthor(author);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   		loop = false;
				   		//this.mainMenu();
				   		break;
			   		case 2: 
			   			System.out.println("please select a Author to edit");
			   			int countAuthor = 0;
			   			List<Author> authorList = new ArrayList<Author>();
			   			try {
							authorList = adminService.readAllAuthors();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			for(Author authorItem:authorList) {
			   				countAuthor++;
							String authorEdit = authorItem.getAuthorName();

							System.out.print(countAuthor  + ") ");
					        System.out.println(authorEdit);
			   			}
			   			
			   			int authorEditOption = scan.nextInt( );			   			
			   			Author chosenAuthor = authorList.get(authorEditOption-1);
			   			
			   			System.out.println("please give a new author name");
			   			String givenAuthorName = scan.next( );
			   			chosenAuthor.setAuthorName(givenAuthorName);
			   			
			   			try {
							adminService.saveAuthor(chosenAuthor);  // change from updateAuthor to saveAuthor
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   					   			
			   			break;
			   		case 3: 
			   			System.out.println("please select a author to delete");
			   			
			   			int countAuthorDelete = 0;
			   			List<Author> authorListToDelete = new ArrayList<Author>();
			   			try {
			   				authorListToDelete = adminService.readAllAuthors();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			for(Author authorItem:authorListToDelete) {
			   				countAuthorDelete++;
							String authorEditName = authorItem.getAuthorName();

							System.out.print(countAuthorDelete  + ") ");
					        System.out.println(authorEditName);
			   			}
			   			
			   			int authorDeleteOption = scan.nextInt( );
			   			Author chosenAuthorDelete = authorListToDelete.get(authorDeleteOption-1);
			   			
			   			try {
							adminService.saveAuthor(chosenAuthorDelete);  // change from deleteAuthor to saveAuthor
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   			
			   		}			   					   		

			   	break;
			   	case 2:
			   		System.out.println("1)	Add Book");
			   		System.out.println("2)	Update Book");
			   		System.out.println("3)	Delete Book");
			   		
			   		int bookOption = scan.nextInt( );
			   		
			   		switch(bookOption) {
			   		
			   		case 1:
			   			
			   			System.out.println("please give a book name");
			   			String bookName = scan.next( );
			   			
				   		Book book = new Book();
				   		book.setTitle(bookName);
				   		try {
							adminService.saveBook(book);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   		this.mainMenu();
				   		break;
				   		
			   		case 2:
			   			System.out.println("please select a Book to edit");
			   			int countBook = 0;
			   			List<Book> bookList = new ArrayList<Book>();
			   			try {
							bookList = adminService.readAllBooks();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			for(Book bookItem:bookList) {
			   				countBook++;
							String bookEditName = bookItem.getTitle();

							System.out.print(countBook  + ") ");
					        System.out.println(bookEditName);
			   			}
			   			
			   			int bookEditOption = scan.nextInt( );			   			
			   			Book chosenBook = bookList.get(bookEditOption-1);
			   			
			   			System.out.println("please give a new book name");
			   			String givenBookName = scan.next( );
			   			chosenBook.setTitle(givenBookName);
			   			
			   			try {
							adminService.updateBook(chosenBook);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   			
			   		case 3:
			   			System.out.println("please select a book to delete");
			   			
			   			int countBookDelete = 0;
			   			List<Book> bookListToDelete = new ArrayList<Book>();
			   			try {
			   				bookListToDelete = adminService.readAllBooks();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			for(Book bookItem:bookListToDelete) {
			   				countBookDelete++;
							String bookEditName = bookItem.getTitle();

							System.out.print(countBookDelete  + ") ");
					        System.out.println(bookEditName);
			   			}
			   			
			   			int bookDeleteOption = scan.nextInt( );
			   			Book chosenBookDelete = bookListToDelete.get(bookDeleteOption-1);
			   			
			   			try {
							adminService.deleteBook(chosenBookDelete);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   		}

			   	break;
			   	case 3:
			   		System.out.println("1)	Add Publisher");
			   		System.out.println("2)	Update Publisher");
			   		System.out.println("3)	Delete Publisher");
			   		
			   		int pubOption = scan.nextInt( );
			   		int id = 0;
			   		switch(pubOption) {
			   		
			   		case 1:
			   			System.out.println("please give a Publisher name to add a new publisher");
			   			String pubName = scan.next( );
			   			
			   			Publisher publisherNew = new Publisher();
			   			publisherNew.setName(pubName);
			   			
			   			try {
							id = adminService.savePublisherWithID(publisherNew);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   			
			   		case 2:
			   			System.out.println("please select a new publisher edit");
			   			
			   			
			   			List<Publisher> publisherListEdit  = new ArrayList<Publisher>();
			   			try {
							publisherListEdit = adminService.readAllPublishers();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			int count = 0;
			   			for (Publisher publisherEdit: publisherListEdit) {
			   				count++;
							String pubNameEdit = publisherEdit.getName();

							System.out.print(count  + ") ");
					        System.out.println(pubNameEdit);
			   			}
			   			int chosenPublisherIndex = scan.nextInt( );
			   			
			   			Publisher chosenPublisher = publisherListEdit.get(chosenPublisherIndex-1);
			   			
			   			System.out.println("please give a new author name");
			   			String givenPublisherName = scan.next( );
			   			chosenPublisher.setName(givenPublisherName);
			   			
			   			try {
							adminService.updatePublisher(chosenPublisher);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   		
			   			this.mainMenu();
			   			break;
			   			
			   		case 3:
			   			System.out.println("please select a Publisher to delete");
			   			
			   			int countPublisherDelete = 0;
			   			List<Publisher> publisherListToDelete = new ArrayList<Publisher>();
			   			try {
			   				publisherListToDelete = adminService.readAllPublishers();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			for(Publisher publisherItem:publisherListToDelete) {
			   				countPublisherDelete++;
							String publisherEditName = publisherItem.getName();

							System.out.print(countPublisherDelete  + ") ");
					        System.out.println(publisherEditName);
			   			}
			   			
			   			int publisherDeleteOption = scan.nextInt( );
			   			Publisher chosenPublisherDelete = publisherListToDelete.get(publisherDeleteOption-1);
			   			
			   			try {
							adminService.deletePublisher(chosenPublisherDelete);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;			   			
			   		}
			   		
			   		break;
			   		
			   	case 4:
			   		System.out.println("1)	Add Library Branches");
			   		System.out.println("2)	Update Library Branches");
			   		System.out.println("3)	Delete Library Branches");
			   		

			   		int branchOption = scan.nextInt( );
			   		
			   		switch(branchOption) {
			   		
			   		case 1:
			   			System.out.println("please give a branch name");
			   			String branchNameAdd = scan.next( );
			   			
				   		Branch branch = new Branch();
				   		branch.setName(branchNameAdd);
				   		try {
							adminService.saveBranch(branch);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   			
			   		case 2:
			   			System.out.println("please select a new Branch edit");
			   			
			   			
			   			List<Branch> branchListEdit  = new ArrayList<Branch>();
			   			try {
							branchListEdit = adminService.readAllBranch();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			int count = 0;
			   			for (Branch branchEdit: branchListEdit) {
			   				count++;
							String branchNameEdit = branchEdit.getName();

							System.out.print(count  + ") ");
					        System.out.println(branchNameEdit);
			   			}
			   			int chosenBranchIndex = scan.nextInt( );
			   			
			   			Branch chosenBranch = branchListEdit.get(chosenBranchIndex-1);
			   			
			   			System.out.println("please give a new branch name");
			   			String givenBranchName = scan.next( );
			   			chosenBranch.setName(givenBranchName);
			   			
			   			try {
							adminService.updateBranch(chosenBranch);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   			
			   		case 3:
			   			System.out.println("please select a Branch to delete");
			   			
			   			int countBranchDelete = 0;
			   			List<Branch> branchListToDelete = new ArrayList<Branch>();
			   			try {
			   				branchListToDelete = adminService.readAllBranch();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			for(Branch branchItem:branchListToDelete) {
			   				countBranchDelete++;
							String branchEditName = branchItem.getName();

							System.out.print(countBranchDelete  + ") ");
					        System.out.println(branchEditName);
			   			}
			   			
			   			int branchDeleteOption = scan.nextInt( );
			   			Branch chosenBranchDelete = branchListToDelete.get(branchDeleteOption-1);
			   			
			   			try {
							adminService.deleteBranch(chosenBranchDelete);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   		}
			   		
			   		break;
			   	case 5:
			   		System.out.println("1)	Add Borrowers");
			   		System.out.println("2)	Update Borrowers");
			   		System.out.println("3)	Delete Borrowers");

			   		int borrowerOption = scan.nextInt( );
			   		
			   		switch(borrowerOption) {
			   		
			   		case 1:
			   			System.out.println("please give a Borrower name");
			   			String borrowerNameAdd = scan.next( );
			   			
				   		Borrower borrower = new Borrower();
				   		borrower.setName(borrowerNameAdd);
				   		try {
							adminService.saveBorrower(borrower);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   			
			   		case 2:
			   			System.out.println("please select a new Borrower edit");
			   			
			   			
			   			List<Borrower> borrowerListEdit  = new ArrayList<Borrower>();
			   			try {
							borrowerListEdit = adminService.readAllBorrowers();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			int count = 0;
			   			for (Borrower borrowerEdit: borrowerListEdit) {
			   				count++;
							String borrowerNameEdit = borrowerEdit.getName();

							System.out.print(count  + ") ");
					        System.out.println(borrowerNameEdit);
			   			}
			   			int chosenBorrowerIndex = scan.nextInt( );
			   			
			   			Borrower chosenBorrower = borrowerListEdit.get(chosenBorrowerIndex-1);
			   			
			   			System.out.println("please give a new borrower name");
			   			String givenBorrowerName = scan.next( );
			   			chosenBorrower.setName(givenBorrowerName);
			   			
			   			try {
							adminService.updateBorrower(chosenBorrower);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   			
			   		case 3:
			   			System.out.println("please select a Borrower to delete");
			   			
			   			int countBorrowerDelete = 0;
			   			List<Borrower> borrowerListToDelete = new ArrayList<Borrower>();
			   			try {
			   				borrowerListToDelete = adminService.readAllBorrowers();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			for(Borrower borrowerItem:borrowerListToDelete) {
			   				countBorrowerDelete++;
							String borrowerEditName = borrowerItem.getName();

							System.out.print(countBorrowerDelete  + ") ");
					        System.out.println(borrowerEditName);
			   			}
			   			
			   			int borrowerDeleteOption = scan.nextInt( );
			   			Borrower chosenBorrowerDelete = borrowerListToDelete.get(borrowerDeleteOption-1);
			   			
			   			try {
							adminService.deleteBorrower(chosenBorrowerDelete);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			this.mainMenu();
			   			break;
			   		}
			   		
			   		break;
			   	case 6:
			   		System.out.println("Over-ride Due Date for a Book Loan");
			   		List<BookLoans> bookLoansList = new ArrayList<BookLoans>();
			   		
			   		
					try {
						bookLoansList = adminService.readAllBookLoans();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			   		int count = 0;
			   		for(BookLoans bookLoans:bookLoansList) {
		   				count++;
						Timestamp bookLoansEditDateOut = bookLoans.getDateOut();
						Date bookLoansEditDueDate = bookLoans.getDueDate();
						Date bookLoansEditDateIn = bookLoans.getDateIn();

						System.out.print(count  + ") ");
				        System.out.print(bookLoansEditDateOut);
				        System.out.print(" ");
				        System.out.print(bookLoansEditDueDate);
				        System.out.print(" ");
				        System.out.println(bookLoansEditDateIn);
				        System.out.println(" ");
		   			}
		   			
		   			int chosenBookLoans = scan.nextInt( );
		   			BookLoans chosenBookLoansObject = bookLoansList.get(chosenBookLoans-1);
		   			
		   			System.out.println("give a new dueDate, how many days later?");
		   			int chosenDayLater = scan.nextInt( );
		   			Calendar cal = Calendar.getInstance();
		   			cal.setTime(chosenBookLoansObject.getDueDate());
				    cal.add(Calendar.DATE, chosenDayLater);
					Date chosenDueDate = cal.getTime();
					  //String chosenDueDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());

		   			Timestamp dateIn = chosenBookLoansObject.getDateIn();
		   			
		   			Integer bookId = chosenBookLoansObject.getBook().getBookId();
		   			Integer branchId = chosenBookLoansObject.getBranch().getBranchId();
		   			Integer cardNo = chosenBookLoansObject.getBorrower().getCardNo();
		   			System.out.println(chosenDueDate);
		   			System.out.println(dateIn);
		   			System.out.println(bookId);
		   			System.out.println(branchId);
		   			System.out.println(cardNo);
		   			try {
						adminService.updateDueDateBookLoans(chosenDueDate, bookId, branchId, cardNo, dateIn);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		   			
			   		
			   		loop = false;
			   		break;
			  }
			   	
		      } 	
   		
		}

				
}
