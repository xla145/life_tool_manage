package cn.net.xulian.dao.baseDao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import cn.net.xulian.model.BaseEntity;


/**
 * Data Access Object基类，已经包含了常用的增删改查操作。<br>
 * 使用时只需要继承接口，不需要实现类，spring自动通过cglib生成实现类
 * @param <T> 实体类型
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, Serializable>/*JpaRepository<T, Serializable>*/, JpaSpecificationExecutor<T> {
//	T findById(Serializable id);
	
}