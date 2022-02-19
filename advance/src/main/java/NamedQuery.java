import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Member;
import domain.Team;

public class NamedQuery {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Team teamA = new Team();
			Team teamB = new Team();
			teamA.setName("teamA");
			teamB.setName("teamB");
			em.persist(teamB);
			em.persist(teamA);
			
			Member member1 = new Member();
			member1.setUsername("1");
			member1.setAge(10);
			member1.setTeam(teamA);
			Member member2 = new Member();
			member2.setUsername("2");
			member2.setAge(10);
			member2.setTeam(teamA);
			Member member3 = new Member();
			member3.setUsername("3");
			member3.setTeam(teamB);
			member3.setAge(10);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			
			em.flush();
			em.clear(); 
			
			//멤버클래스에 named 쿼리를 미리 만들어뒀다.
			//사용 방법
			em.createNamedQuery("Member.findByUsername",Member.class)
			.setParameter("username", "1")
			.getResultList();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	}


}
