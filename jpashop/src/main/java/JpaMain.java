

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.domain.Book;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf  = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		
		try {
			Book book = new Book();
			book.setName("jpa");
			book.setAutor("김영한");
			em.persist(book);
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		//코드

	
		emf.close();
		}
}
