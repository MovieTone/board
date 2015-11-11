package com.board;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDao {
	
	private Session session;
	private SessionFactory sessionFactory;
	
	public UserDao(){
		connect();
	}
	
	public void connect() {
		
		try {
			Configuration conf = new Configuration();
			sessionFactory = conf.configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
		} catch (Exception e) {
			System.out.println("Невозможно подключиться к базе данных");
			e.printStackTrace();
		}
		
	}
	
	public String create(String user, String password) {
		
		String userId = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			userId = (String) session.save(new User(user, password));
			tx.commit();
		} 
		catch(org.hibernate.exception.DataException e){
			System.out.println("Ошибка ввода данных!");
			userId = null;
			e.printStackTrace();
		}
		catch (Exception e) {
			if (tx != null) 
				tx.rollback();
			userId = null;
			System.out.println("Ошибка обработки транзакции");
			e.printStackTrace();
		}
		finally {
			closeConn();
		}
		return userId;
		
	}
	
	public boolean isValid(String user, String password) {
		
		Transaction tx = null;
		boolean notFound = true;
		try {
			tx = session.beginTransaction();
			String query = "FROM User u WHERE u.user='" + user + "' and u.password ='" + password + "'";
			List list = null;
			list = session.createQuery(query).list();
			notFound = list.isEmpty();
		} catch(Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Ошибка обработки транзакции");
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return !notFound;
		
	}
	
	public User get(String name){
		
		Transaction tx = null;
		User user = null; 
		try {
			tx = session.beginTransaction();
			user = session.get(User.class, name);
			tx.commit();
		} 
		catch (Exception e) {
			if (tx != null) 
				tx.rollback();
			System.out.println("Ошибка обработки транзакции");
			e.printStackTrace();
		}
		finally {
			closeConn();
		}
		return user;
		
	}
	
	public void closeConn() {
		if (session != null)
			session.close();
		if (sessionFactory != null)
			sessionFactory.close();
	}
	
}

