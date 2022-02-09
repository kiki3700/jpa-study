import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import relation.Child;
import relation.Parent;

public class Orphan {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Child c1 = new Child();
			Child c2 = new Child();
			
			Parent p = new Parent();
			p.addChild(c1);
			p.addChild(c2);
			
			em.persist(p);
			
			em.flush();
			em.clear();
			
			Parent findParent=em.find(Parent.class, p.getId());
			findParent.getChildList().remove(0);
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		emf.close();
	}
}
