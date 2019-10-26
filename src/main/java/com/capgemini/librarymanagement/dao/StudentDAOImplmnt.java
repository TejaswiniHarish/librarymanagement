package com.capgemini.librarymanagement.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagement.dto.BooksInventory;
import com.capgemini.librarymanagement.dto.BooksRegistration;
import com.capgemini.librarymanagement.dto.BooksTransaction;
import com.capgemini.librarymanagement.exception.CustomException;

@Repository
public class StudentDAOImplmnt implements StudentDAO {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();

	@Override
	public List<BooksInventory> searchForBook(String bookName) {
		List<BooksInventory> booksInventory = null;
		try {
			transaction.begin();
			String query = "from BooksInventory where bookName=: bookName ";
			Query searchQuarry = entityManager.createQuery(query);
			searchQuarry.setParameter("bookName", bookName);
			booksInventory = searchQuarry.getResultList();
			transaction.commit();
			entityManager.close();
		} catch (Exception e) {
			throw new CustomException("No Book Found");
		}
		return booksInventory;
	}

	@Override
	public BooksRegistration makeBookRequest(String bookId) {
		BooksRegistration registration = new BooksRegistration();
		try {
			transaction.begin();
			Random random = new Random();
			registration.setRegistrationId(random.nextInt((1000 - 100) + 1) + 100);
			registration.setUserId(CommonDAOImplmnt.userId);
			registration.setBookId(bookId);
			registration.setRegistrationDate(new Date());
			entityManager.persist(registration);
			transaction.commit();
			entityManager.close();
		} catch (Exception e) {
			transaction.rollback();
			throw new CustomException("Librarian not added");
		}
		return registration;
	}

	@Override
	public Boolean cancelRequestedBook(Integer registrationId) {
		BooksRegistration id = entityManager.find(BooksRegistration.class, registrationId);
		try {
			if (id == null) {
				return false;
			} else {
				transaction.begin();
				entityManager.remove(id);
				transaction.commit();
				entityManager.close();
			}
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		entityManager.close();
		return true;
	}

	@Override
	public List<BooksTransaction> getResponse() {
		List<BooksTransaction> booksTransaction = null;
		try {
			transaction.begin();
			String query = "from BooksTransaction where studentId=: studentId";
			Query getDetailsQuarry = entityManager.createQuery(query);
			getDetailsQuarry.setParameter("studentId", CommonDAOImplmnt.userId);
			booksTransaction = getDetailsQuarry.getResultList();
			transaction.commit();
			entityManager.close();
		} catch (Exception e) {
			throw new CustomException("No Response");
		}
		return booksTransaction;
	}

	@Override
	public List<BooksRegistration> getAllRequestedBook() {
		List<BooksRegistration> registeredBooks = null;
		try {
			transaction.begin();
			String query = "from BooksRegistration where userId=: userId ";
			Query getDetailsQuarry = entityManager.createQuery(query);
			getDetailsQuarry.setParameter("userId", CommonDAOImplmnt.userId);
			registeredBooks = getDetailsQuarry.getResultList();
			transaction.commit();
			entityManager.close();
		} catch (Exception e) {
			throw new CustomException("No Records Found");
		}
		return registeredBooks;
	}

	@Override
	public boolean returnBook(Integer transectionId) {
		
		return false;
	}

}
