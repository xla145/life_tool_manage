package cn.net.xulian.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class UserVO {
	private String id;
	@JSONField(name="entity")
	private String name; //姓名

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
