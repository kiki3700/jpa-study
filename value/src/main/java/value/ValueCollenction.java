package value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import value.domain.Address;
import value.domain.AddressEntity;
import value.domain.Member;

public class ValueCollenction {
	 public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Member member = new Member();
			member.setName("member1");
			member.setHomeAddress(new Address("서울", "대방", "1234"));
			
			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("족발");
			member.getFavoriteFoods().add("피자");
			
			member.getAddressHistory().add(new AddressEntity("부산", "사하", "1232"));
			member.getAddressHistory().add(new AddressEntity("대전", "세종", "1232"));
			
			em.persist(member);
			
			em.flush();
			em.clear();
			
			System.out.println("==========start============");
			Member findMember = em.find(Member.class, member.getId());
			
			/*
			 * 하면 안된다.
			 */
//			findMember.getHomeAddress().setCity("뉴욕");
			Address a = findMember.getHomeAddress();
			findMember.setHomeAddress(new Address("뉴욕", a.getStreet(), a.getZipcode()));
			
			//치킨을 한식으로 바꾸기
			//스트링이여서 방법이 없다. 지우고 다시 넣어라
			findMember.getFavoriteFoods().remove("치킨");
			findMember.getFavoriteFoods().add("한식");
			
			//대전은 놔두고 부산 만 바꾸기
			findMember.getAddressHistory().remove(new AddressEntity("부산", "사하", "1232"));
			findMember.getAddressHistory().add(new AddressEntity("파리", "9구", "1232"));
			
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	 }
}
