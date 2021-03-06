package relation;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Member {
	@Id @GeneratedValue
	private Long id;
	
	@Column(name = "USERNAME")
	private String name;
//	@Column(name ="TEAM_ID")
//	private Long teamid;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TEAM_ID")
	private Team team;
	
	
	private String createBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}

	
	
}
