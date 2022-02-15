




import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import domain.Member;
import domain.Team;

public class JPQL1 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			
			Member member = new Member();
			Team team = new Team();
			team.setName("이나인페이");
			member.setUsername("이성현");
//			member.setTeam(team);
			member.setAge(30);
			
			em.persist(member);
			
			TypedQuery<Member> tq = em.createQuery("select m from Member m",Member.class);
			Query q = em.createQuery("select m.username, m.age from Member m");
			
			//결과 조회
			//하나이 상의 결과 조회
			List<Member> resultList = tq.getResultList();
			
			for(Member m : resultList) {
				System.out.println(m);
			}
			//하나만 조회
			//익셉션이 두개나 터진다. size가 0 or size>1
			Member m = tq.getSingleResult();
			
			//파라미터 바인딩 - 이름 기준 위치기준
			//이름 기준
			TypedQuery<Member> queryByName = em.createQuery("select m from Member m where m.username = :username",Member.class)
			.setParameter("username", member.getUsername());
			//위치 기준
			TypedQuery<Member> queryByOrder = em.createQuery("select m from Member m where m.username = ?1",Member.class)
			.setParameter(1, member.getUsername());
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

