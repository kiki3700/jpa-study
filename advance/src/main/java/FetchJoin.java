import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Member;
import domain.Team;

public class FetchJoin {
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
			
			String query = "select m from Member m";
			List<Member> result = em.createQuery(query, Member.class)
					.getResultList();
			for(Member m : result) {
				System.out.println(m.getUsername()+" "+m.getTeam().getName());
			}
//			
//			//fetch join query
//			String fetchJoinQuery = "select m from Member m join fetch m.team";
//			List<Member> fetchResult = em.createQuery(fetchJoinQuery).getResultList();
//			for(Member m : result) {
//				System.out.println(m.getUsername()+" "+m.getTeam().getName());
//			}
//			//collection fetch join
//			String collectionFetchJoin = "select t from Team t join fetch t.members";
//			List<Team> collectionFetchJoinResult = em.createQuery(collectionFetchJoin, Team.class)
//					.getResultList();
//			for(Team t : collectionFetchJoinResult) {
//				System.out.println(t.getName()+" "+t.getMembers().size());
//				for(Member m : t.getMembers()) {
//					System.out.println("-> "+m.getId());
//				}
//			}
//			
			//distinct collection fetch join
			String distinctCollectionFetchJoin = "select distinct t from Team t join fetch t.members";
			List<Team> distinctCollectionFetchJoinResult = em.createQuery(distinctCollectionFetchJoin, Team.class)
					.getResultList();
			for(Team t : distinctCollectionFetchJoinResult) {
				System.out.println(t.getName()+" "+t.getMembers().size());
				for(Member m : t.getMembers()) {
					System.out.println("-> "+m.getId());
				}
			}
			//general join
			String generalJoin= "select t from Team t join t.members m";
			List<Team> generalJoinResult = em.createQuery(generalJoin, Team.class)
					.getResultList();
			for(Team t : generalJoinResult) {
				System.out.println(t.getName()+" "+t.getMembers().size());
				for(Member m : t.getMembers()) {
					System.out.println("-> "+m.getId());
				}
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

