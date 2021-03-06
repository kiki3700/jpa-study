

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import relation.Member;
import relation.Team;


public class Warnning {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {

			
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
			member.setName("memberA");
			member.setTeam(team);
			em.persist(member);

			
//			em.flush();
//			em.clear();
			
			Team findTeam = em.find(Team.class, team.getId());
			List<Member> members = findTeam.getMembers();
			System.out.println("=============");
			for(Member m : members) {
				System.out.println("m : "+m.getName());
			}
			System.out.println("=============");
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	}
}
