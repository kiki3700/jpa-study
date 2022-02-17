import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Address;
import domain.Member;
import domain.MemberDto;
import domain.Team;

public class Join {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			for(int i = 0 ; i < 20; i++) {
				Team team = new Team();
				team.setName(i%2+"");
				em.persist(team);
				
				Member member = new Member();
				member.setUsername("이성현");
				member.setAge(i+1);
				member.changeTeam(team);
				em.persist(member);
				


			}
			em.flush();
			em.clear();
			
			String innerJoinQuery = "select m from Member m inner join m.team t where t.name = '1'";
			List<Member> result = em.createQuery(innerJoinQuery, Member.class)
					.getResultList();
			for(Member m : result) {
				System.out.println(m);
			}
			
			String outerJoinQuery = "select m from Member m inner left outer join m.team t";
			result = em.createQuery(outerJoinQuery, Member.class)
					.getResultList();
			
			String setaJoin = "select m from Member m , Team t where m.name = t.name";
			result = em.createQuery(setaJoin, Member.class)
					.getResultList();
			for(Member m : result) {
				System.out.println(m);
			}
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
