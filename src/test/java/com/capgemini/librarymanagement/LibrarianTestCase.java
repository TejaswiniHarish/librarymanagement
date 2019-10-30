package com.capgemini.librarymanagement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.librarymanagement.dao.LibrarianDAO;
import com.capgemini.librarymanagement.dto.BooksInventory;
import com.capgemini.librarymanagement.dto.Users;

import junit.framework.TestCase;

@SpringBootTest
public class LibrarianTestCase extends TestCase {

	@Autowired
	LibrarianDAO librarianDAO;

	@Test
	void addNewBook() {
		BooksInventory booksInventory = new BooksInventory();
		booksInventory.setBookId("1111");
		booksInventory.setBookName("Machine Learning");
		booksInventory.setfirstAuthor("AAAA");
		booksInventory.setsecondAuthor("ZZZ");
		booksInventory.setPublisher("SSSS");
		booksInventory.setYearofpublication("2019");
		BooksInventory check = librarianDAO.addNewBook(booksInventory);
		assertNotNull(booksInventory);

	}

	@Test
	void deleteBook() {
		boolean check = librarianDAO.deleteBook("1111");
		assertTrue(check);
	}

	@Test
	void updateBook() {
		BooksInventory booksInventory = new BooksInventory();
		booksInventory.setBookId("1111");
		booksInventory.setBookName("ADE");
		booksInventory.setfirstAuthor("AAAA");
		booksInventory.setsecondAuthor("ZZZ");
		booksInventory.setPublisher("SSSS");
		booksInventory.setYearofpublication("2019");
		BooksInventory check = librarianDAO.updateBook(booksInventory);
		assertNotNull(booksInventory);
	}

	@Test
	void addStudent() {
		Users users = new Users();
		users.setId("114");
		users.setName("nisha");
		users.setEmailId("nisha@gmail.com");
		users.setPassword("nisha123");
		users.setRole("Student");
		Users check = librarianDAO.addNewStudent(users);
		assertNotNull(users);
	}

	@Test
	void deleteStudent() {
		boolean check = librarianDAO.deleteStudent("111");
		assertTrue(check);
	}

	@Test
	void searchStuddent() {
		List<Users> check = librarianDAO.searchStudent();
		assertNotNull(check);
	}
}

