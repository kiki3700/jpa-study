

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Member;
import domain.Team;

public class DirectoryExpression {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Member member = new Member();
			member.setUsername("관리자");
			member.setAge(10);
			em.persist(member);
			em.flush();
			em.clear();
			// 상태 필드 경로 표현 식 예시
			String query = "select m.username from Member m";
			// m.username뒤엔 .이 붙어 멀 탐색할 수 있는 게 없다.
			
			// 연관관계 필드 경로 표현 식 예시
			String associationFieldQuery = "select m.team from Member m";
			// 묵시적인 내부 조인 가능 => 추가로 탐색할 수 있다. (m.team.name)  이 가능하다.
			List<Team> result = em.createQuery(associationFieldQuery, Team.class)
					.getResultList();
			//컬렉션 값 연관 필드
			String collectionAssociationFieldQuery = "select t.members.size from Team t ";
			//멤버스는 일대 다 관계이기 때문에 어떤걸 꺼내 탐색해야할지 머리가 아프기 때문에
			//alieas를 사용하여 쿼리한다한다.
			List memberResult = em.createQuery(collectionAssociationFieldQuery, Collection.class)
					.getResultList();
			//탐색하고 싶을땐 어떻게 해야할까?
			//명시적인 조인을 써야한다.
			String serchableCollectionAssociationFieldQuery = "select m.username.size from Team t join t.member m";
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
