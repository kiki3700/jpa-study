import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Address;
import domain.Member;
import domain.MemberDto;
import domain.Team;

public class Paging {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			for(int i = 0 ; i < 20; i++) {
				Member member = new Member();
				member.setUsername("이성현");
				member.setAge(i+1);
				em.persist(member);
			}
			em.flush();
			em.clear();
			
			List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
			.setFirstResult(1)
			.setMaxResults(10)
			.getResultList(); //소팅을 해야 제대로 확인가능
			
			System.out.println("result.size : " + result.size());
			result.stream().forEach(System.out::println);
			
			
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
