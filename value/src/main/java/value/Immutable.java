package value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import value.domain.Address;
import value.domain.Member;
import value.domain.Period;

public class Immutable {
	 public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Address address = new Address();
			address.setCity("서울");
			address.setStreet("여의 대방로");
			address.setZipcode("12345");
		

			Member member1 = new Member();
			member1.setName("성현");
			member1.setPeriod(new Period());
			member1.setAddress(address);
			em.persist(member1);

			
			Address copyAddress = new Address();
			copyAddress.setCity(address.getCity());
			copyAddress.setStreet(address.getStreet());
			copyAddress.setZipcode(address.getZipcode());
			Member member2 = new Member();
			member2.setName("sh");
			member2.setAddress(copyAddress);
			em.persist(member2);
			
			
			member1.getAddress().setCity("ny");
			
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	 }
}
