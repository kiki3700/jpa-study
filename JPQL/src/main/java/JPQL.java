

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import domain.Member;


public class JPQL {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			
			
			List<Member> memberList = em.createQuery(
					"select m From Member m where m.name like '%kim%'",
					Member.class
					).getResultList();
			
			//Criteria 사용준비
			CriteriaBuilder cb = em.getCriteriaBuilder(); 
			CriteriaQuery<Member> query = cb.createQuery(Member.class);
			
			Root<Member> m = query.from(Member.class);
			
			CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"), "kim"));
			List<Member> resultMember = em.createQuery(cq)
					.getResultList();
			String queryStr = "select MEMBER_ID, CITY FROM MEMBER";
			em.createNativeQuery(queryStr, Member.class).getResultList();
			
			
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
