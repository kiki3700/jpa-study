import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Member;
import domain.Team;

public class Bulk {
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
			//자동으로 flush 호출 => 디비에 있는걸 영속성 컨텍스트에 적용하는 게
			//아니라 컨텍스트를 db에 적용함
			String query = "update Member m set m.age=20";
			int resultCount = em.createQuery(query).executeUpdate();
			
			System.out.println(resultCount);
			//바뀌지 않는다.
			//영속성 컨텍스트를 날리고 다시 가져와야한다.
			em.clear();
			Member findMember = em.find(Member.class, member1.getId());

			System.out.println("findMember's age = "+ findMember.getAge());
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

