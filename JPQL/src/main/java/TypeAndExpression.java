import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Address;
import domain.Member;
import domain.MemberDto;
import domain.MemberType;
import domain.Team;

public class TypeAndExpression {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			String strTypeQuery = "select 'hello', m from Member";
			String intTypeQuery = "select 10L, 10D, 10F from Member";
			String booleanTypeQuery = "select true, false from member";
			String enumTypeQuery = "select m from Member m where m.memberType = domain.MemberType.ADMIN";
			
			Member member = new Member();
			member.setAge(30);
			member.setUsername("이성현");
			member.setMemberType(MemberType.ADMIN);
			em.persist(member);

			em.flush();
			em.clear();
			
			List<Member> result = em.createQuery(enumTypeQuery, Member.class).getResultList();
			Member findMember = result.get(0);
			System.out.println(findMember);
			
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
