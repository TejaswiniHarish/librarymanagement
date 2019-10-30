package com.capgemini.librarymanagement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.librarymanagement.dao.StudentDAO;
import com.capgemini.librarymanagement.dto.BooksInventory;
import com.capgemini.librarymanagement.dto.BooksRegistration;
import com.capgemini.librarymanagement.dto.BooksTransaction;

import junit.framework.TestCase;

@SpringBootTest
public class StudentTestCase extends TestCase {
	
	@Autowired
	StudentDAO studentDAO;
	
	@Test
	void searchForBook() {
		List<BooksInventory> check=studentDAO.searchForBook("Big Data Analyics");
		assertNotNull(check);
	}
	
	@Test
	void makeBookRequest() {
		BooksRegistration check=studentDAO.makeBookRequest("123");
		assertNotNull(check);
	}
	
	@Test
	void getAllRequestedBook() {
		List<BooksRegistration> check=studentDAO.getAllRequestedBook();
		assertNotNull(check);
	}
	
	@Test
	void cancelRequestedBook() {
		boolean check=studentDAO.cancelRequestedBook(123);
		assertTrue(check);
	}
	
	@Test
	void getResponse() {
		List<BooksTransaction> check=studentDAO.getResponse();
		assertNotNull(check);
	}
}
