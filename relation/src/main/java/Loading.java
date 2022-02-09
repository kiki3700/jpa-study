import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

import relation.Member;
import relation.Team;

public class Loading {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Team team = new Team();
			team.setName("teama");
			em.persist(team);
			
			Member member1 = new Member();
			member1.setName("member1");
			member1.setTeam(team);
			em.persist(member1);
			
			em.flush();
			em.clear();
			
			Member m = em.find(Member.class, member1.getId());
			System.out.println("m team class = "+m.getTeam().getClass());
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
