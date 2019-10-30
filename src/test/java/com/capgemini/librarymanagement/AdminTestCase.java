package com.capgemini.librarymanagement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.librarymanagement.dao.AdminDAO;
import com.capgemini.librarymanagement.dto.Users;

import junit.framework.TestCase;

@SpringBootTest
public class AdminTestCase extends TestCase{

	@Autowired
	AdminDAO adminDao;

	@Test
	void addLibrarian() {
		Users users = new Users();
		users.setId("113");
		users.setName("soham");
		users.setEmailId("soham@gmail.com");
		users.setPassword("soham123");
		users.setRole("Librarian");
		Users check = adminDao.addLibrarian(users);
		assertNotNull(users);
	}

	@Test
	void updateLibrarian() {
		Users users = new Users();
		users.setId("113");
		users.setName("aaa");
		users.setEmailId("teju@gmail.com");
		users.setPassword("teju");
		users.setRole("Librarian");
		Users check = adminDao.updateLibrarian(users);
		assertNotNull(users);

	}

	@Test
	void deleteLibrarian( ) {
		boolean check = adminDao.deleteLibrarian("1111");
		assertTrue(check);
	}
	@Test
	void searchLibrarian() {

		List<Users> check = adminDao.searchLibrarian();
		assertNotNull(check);

	}

}
