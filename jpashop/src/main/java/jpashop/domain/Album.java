package jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item {
	private String aritist;
	private String etc;
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAritist() {
		return aritist;
	}
	public void setAritist(String aritist) {
		this.aritist = aritist;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
}
