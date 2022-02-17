import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Member;
import domain.Team;

public class ConditionExpression {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Member member = new Member();
			member.setUsername("관리자");
			member.setAge(10);
			em.persist(member);
			em.flush();
			em.clear();
			String baseCaseQuery = "select "
					+ "case when m.age <= 10 then '학생요금' "
					+ "when m.age >= 60 then '경로요금' "
					+ "else '일반요금' "
					+ "end "
					+ "from Member m";
			List<String> result = em.createQuery(baseCaseQuery, String.class).getResultList();
			result.stream().forEach(System.out::println);
			
			String simpleCaseQuery
			= "select t.name"
					+ "case t.name"
					+ "when '1' then 'incentive110%' "
					+ "when '2' then 'incentive120%' "
					+ "else 'incentive150% "
					+ "end "
					+ "from Team t";
			String coalesceQuery = "select coalesce(m.username, '이름 없는 회원') from Member m";
			result = em.createQuery(coalesceQuery, String.class).getResultList();
			result.stream().forEach(System.out::println);
			String nullifQuery = "select NULLIF(m.username, '관리자') from Member m";
			result = em.createQuery(coalesceQuery, String.class).getResultList();
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
