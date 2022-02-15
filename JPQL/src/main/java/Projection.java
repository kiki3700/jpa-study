import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Address;
import domain.Member;
import domain.MemberDto;
import domain.Team;

public class Projection {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Member member = new Member();
			member.setUsername("이성현");
			member.setAge(30);
			
			em.persist(member);
			
			em.flush();
			em.clear();
			
			
			
			List<Member> result = em.createQuery("select m from Member m", Member.class)
				.getResultList();
			
			Member findmember = result.get(0);
			findmember.setAge(20);
			
			
			List<Team> resultTeam = em.createQuery("select t from Member m join m.team t", Team.class)
					.getResultList();
			
			List<Address> reultAddress = em.createQuery("select o.address from Order o", Address.class)
					.getResultList();
			//스칼라 프로젝션
			List resultByQuery = em.createQuery("select m.username, m.age from Member m")
					.getResultList();
			
			//1. 쿼리 타입으로 조회
			Object o = resultByQuery.get(0);
			Object[] oArr = (Object[]) o;
			System.out.println("o[0] "+ oArr[0]);
			System.out.println("o[1] "+ oArr[1]);
			
			//2. object[] 이용
			List<Object[]> resultByObject = em.createQuery("select m.username, m.age from Member m",Object[].class)
					.getResultList();
			oArr = resultByObject.get(0);
			System.out.println("o[0] "+ oArr[0]);
			System.out.println("o[1] "+ oArr[1]);
			//3. new 명령어로 조회
			List<MemberDto> resultByNew = em.createQuery("select new domain.MemberDto(m.username, m.age) from Member m",MemberDto.class)
					.getResultList();
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
