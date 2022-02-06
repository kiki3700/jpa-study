package helloJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf  = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		
		try {
			Member member = new Member();
			member.setName("C");
			em.persist(member);
			
//			Member member = new Member();
//			member.setId(2L);
//			member.setName("helloB");
//			em.persist(member);

			
//			//조회
//			
//			Member findMember = em.find(Member.class, 1L);
//			System.out.println("member : "+findMember);
//			
//			//수정
//			findMember.setName("helloJPA");
			

			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		//코드

	
		emf.close();
		}
}
