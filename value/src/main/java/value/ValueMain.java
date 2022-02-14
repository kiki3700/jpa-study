package value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import value.domain.Address;
import value.domain.Member;
import value.domain.Period;


public class ValueMain {
	 public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Member member = new Member();
			member.setName("성현");
			Address address = new Address();
			address.setCity("서울");
			address.setStreet("여의 대방로");
			address.setZipcode("12345");
			member.setPeriod(new Period());
			member.setAddress(address);
			em.persist(member);
			

			
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	 }
}
