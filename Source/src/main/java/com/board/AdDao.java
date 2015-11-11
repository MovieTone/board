package com.board;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AdDao {
	
	private Session session;
	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	
	public AdDao(){
		connect();
	}
	
	public void connect() {
		
		try {
			session = sessionFactory.openSession();
		} catch (Exception e) {
			System.out.println("Невозможно подключиться к базе данных");
			e.printStackTrace();
		}
		
	}
	
	public int create(String name, String headline, String rubric, String text) {
		
		int adId = 0;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			adId = (Integer) session.save(new Ad(name, headline, rubric, text));
			tx.commit();
		} 
		catch(org.hibernate.exception.DataException e){
			System.out.println("Ошибка ввода данных!");
			adId = 0;
			e.printStackTrace();
		}
		catch (Exception e) {
			if (tx != null) 
				tx.rollback();
			adId = 0;
			System.out.println("Ошибка обработки транзакции");
			e.printStackTrace();
		}
		finally {
			closeConn();
		}
		return adId;
		
	}
	
	public Ad get(int id){
		
		Transaction tx = null;
		Ad ad = null; 
		try {
			tx = session.beginTransaction();
			ad = session.get(Ad.class, id);
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
		return ad;
		
	}
	
	public boolean edit(int id, String name, String headline, String rubric, String text) {
		
		boolean edited = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Ad ad = session.get(Ad.class, id);
			ad.setName(name);
			ad.setHeadline(headline);
			ad.setRubric(rubric);
			ad.setText(text);
			session.update(ad);
			tx.commit();
		} 
		catch(org.hibernate.exception.DataException e){
			System.out.println("Ошибка ввода данных!");
			edited = false;
			e.printStackTrace();
		}
		catch (Exception e) {
			if (tx != null) 
				tx.rollback();
			edited = false;
			System.out.println("Ошибка обработки транзакции");
			e.printStackTrace();
		}
		finally {
			closeConn();
		}
		return edited;
		
	}
	
	public boolean delete(int id){
		
		boolean deleted = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Ad ad = session.get(Ad.class, id);
			session.delete(ad);
			tx.commit();
		} 
		catch(org.hibernate.exception.DataException e){
			System.out.println("Ошибка ввода данных!");
			deleted = false;
			e.printStackTrace();
		}
		catch (Exception e) {
			if (tx != null) 
				tx.rollback();
			deleted = false;
			System.out.println("Ошибка обработки транзакции");
			e.printStackTrace();
		}
		finally {
			closeConn();
		}
		return deleted;
		
	}
	
	public List list() {
		
		Transaction tx = null;
		List list = null;
		try {
			tx = session.beginTransaction();
			String query = "FROM Ad";
			list = session.createQuery(query).list();
		} catch(Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Ошибка обработки транзакции");
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return list;
		
	}
	
	public List searchList(String rubric, String author, String onlyUser, String currentUser) {
		
		Transaction tx = null;
		List list = null;
		try {
			tx = session.beginTransaction();
			String query = "FROM Ad";
			if (isNotEmpty(rubric)) {
				query += " ad WHERE ad.rubric='" + rubric + "'";
				if (isNotEmpty(author))
					query += " AND ad.name LIKE '" + author + "%'";
				if (isNotEmpty(onlyUser))
					query += " AND ad.name='" + currentUser + "'";
			}
			else if (isNotEmpty(author)){
				query += " ad WHERE ad.name LIKE '" + author + "%'";
				if (isNotEmpty(onlyUser))
					query += " AND ad.name='" + currentUser + "'";
			}
			else if (isNotEmpty(onlyUser))
				query += " ad WHERE ad.name='" + currentUser + "'";
			list = session.createQuery(query).list();
		} catch(Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Ошибка обработки транзакции");
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return list;
		
	}
	
	public void closeConn() {
		if (session != null)
			session.close();
		if (sessionFactory != null)
			sessionFactory.close();
	}
	
	public static boolean isAddable(String name, String headline, String rubric, String text) {
		return true;
	}
	
	public static boolean isNotEmpty(String temp){
		boolean flag = false;
		if (temp != null) 
			if (!temp.equals(""))
				flag = true;
		return flag;
				
	}
	
	public List getAds() {
		String hq = "FROM ads";
		Query query = session.createQuery(hq);
		List res = query.list();
		return res;
	}
	
}

