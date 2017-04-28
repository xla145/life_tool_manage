package cn.net.xulian.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//User实体类
@Entity
@Table(name="t_user")
public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name ="name",length=100)
	private String name; //姓名

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
