package relation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			

			
			
			Member member = new Member();
			member.setName("member1");
			member.setTeam(team); // 단방향 연관관계 설정, 참조 저장
			em.persist(member);
			

			
//			Long teamId = findMember.getTeamId();
//			Team findTeam = em.find(Team.class, findTeamId);
			em.flush();
			em.clear();
			
			Member findMember = em.find(Member.class, member.getId());
			System.out.println(findMember.getName());
			System.out.println(findMember.getTeam().getName());
			List<Member> members = findMember.getTeam().getMembers();
			System.out.println(members.size());
			for(Member m : members) {
				System.out.println("m ="+m.getName());
			}
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	}
}
