package value.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item{
	private String artist;

	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	
}
