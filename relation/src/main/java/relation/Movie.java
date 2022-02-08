package relation;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
	private String Director;
	private String Actor;
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDirector() {
		return Director;
	}
	public void setDirector(String director) {
		Director = director;
	}
	public String getActor() {
		return Actor;
	}
	public void setActor(String actor) {
		Actor = actor;
	}
	
}
