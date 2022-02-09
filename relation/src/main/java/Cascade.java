import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import relation.Child;
import relation.Parent;

public class Cascade {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Parent parent = new Parent();
			Child c1 = new Child();
			Child c2 = new Child();	
			parent.addChild(c1);
			parent.addChild(c2);
			
			em.persist(parent);
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	}
}
