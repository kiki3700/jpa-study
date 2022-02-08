package relation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

public class Proxy {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Member member1 = new Member();
			member1.setName("member1");
			em.persist(member1);
			
//			Member member2 = new Member();
//			member2.setName("member2");
//			em.persist(member2);
			
			em.flush();
			em.clear();
			
//			Member m1 = em.find(Member.class, member1.getId());
//			Member m2 = em.getReference(Member.class, member1.getId());
//			
////			System.out.println("m1 instance of member "+(m1 instanceof Member));
//			System.out.println("m2 is "+(m2.getClass()));
//			System.out.println("a == a "+ (m2==m1));
//			Member findMember = em.find(Member.class, member.getId());
//			Member findMember = em.getReference(Member.class, member.getId());
//			System.out.println(findMember);
//			System.out.println("findmember .id  :"+ findMember.getId());
//			System.out.println("member name : "+ findMember.getName());
			
			//예외처리
//			Member refMember = em.getReference(Member.class, member1.getId());
//			System.out.println("refMember = "+ refMember.getClass());
//			
////			em.detach(refMember);
//			em.clear();
//			
//			System.out.println("refmember = "+ refMember.getName());
			
			
			// 프록시 확인 메서드
			Member m1 = em.getReference(Member.class, member1.getId());
			System.out.println("is Loaded : "+emf.getPersistenceUnitUtil().isLoaded(m1));
			
			//프록시 강제 초기화
			Hibernate.initialize(m1);
			
			System.out.println("is Loaded : "+emf.getPersistenceUnitUtil().isLoaded(m1));
			
			//프록시 클래스 확인 방법
			System.out.println("refMember = "+m1.getClass());
			

			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		emf.close();
	}
}
