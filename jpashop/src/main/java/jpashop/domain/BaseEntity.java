package jpashop.domain;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	private LocalDateTime createDate;
	private LocalDateTime modifiedDate;
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
