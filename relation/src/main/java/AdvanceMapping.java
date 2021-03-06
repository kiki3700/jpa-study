

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import relation.Movie;

public class AdvanceMapping {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		try {
			Movie movie = new Movie();
			movie.setDirector("AAA");
			movie.setActor("bbbb");
			movie.setPrice(1000);
			movie.setName("바랍과 함께 사라지다");
			
			em.persist(movie);
			
			em.flush();
			em.clear();
			
			Movie findMovie = em.find(Movie.class, movie.getId());
			System.out.println("movie name "+ findMovie.getName());
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	}
}
