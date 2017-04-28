package cn.net.xulian.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//提取实体类的共有属性
@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 7988377299341530426L;

	public final static int IS_DELETE_YES = 1;// 标记删除
	public final static int IS_DELETE_NO = 0;// 未删除,保留的

	@Id
	@Column(name = "id")
	protected String id;

//	@Column(name = "createon")
//	protected Timestamp creationTimestamp = new Timestamp(System.currentTimeMillis());
//	@Column(name = "modifyon")
//	protected Timestamp modificationTimestamp = new Timestamp(System.currentTimeMillis());
//
//	@Column(name = "createby")
//	protected String createby;
//	@Column(name = "modifyby")
//	protected String modifyby;

	@Column(name = "dr")
	protected int dr;// 是否删除。0:未删除;1:已删除

	/**
	 * 主键，对应id字段
	 */
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	/**
	 * 创建日期，对应ts_insert字段
	 */
//	public Timestamp getCreationTimestamp(){
//		return creationTimestamp;
//	}
//
//	public void setCreationTimestamp(Timestamp creationTimestamp){
//		this.creationTimestamp = creationTimestamp;
//	}

	/**
	 * 修改日期，对应ts_update字段
	 */
//	public Timestamp getModificationTimestamp(){
//		return modificationTimestamp;
//	}
//
//	public void setModificationTimestamp(Timestamp modificationTimestamp){
//		this.modificationTimestamp = modificationTimestamp;
//	}
//
//	public String getCreateby(){
//		return createby;
//	}
//
//	public void setCreateby(String createby){
//		this.createby = createby;
//	}
//
//	public String getModifyby(){
//		return modifyby;
//	}
//
//	public void setModifyby(String modifyby){
//		this.modifyby = modifyby;
//	}

	/**
	 * 是否删除，对应dr字段
	 * 
	 * @return
	 */
	 public int getDr() {
		 return dr;
	 }
	
	 public void setDr(int dr) {
		 this.dr = dr;
	 //
	 }

	/**
	 * 判断是否已删除
	 * 
	 * @return
	 */
	public boolean hasDeleted(){
		if(this.getDr() == 0){
			return false;
		}else{
			return true;
		}
	}
}
