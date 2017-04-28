package cn.net.xulian.dao.baseDao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


import cn.net.xulian.model.BaseEntity;

public class BaseDaoImpl<T extends BaseEntity> extends SimpleJpaRepository<T, Serializable> implements BaseDao<T> {

    private final EntityManager entityManager;
    
    public BaseDaoImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    public BaseDaoImpl(JpaEntityInformation<T, Serializable> information, EntityManager entityManager){
    	super(information, entityManager);
    	this.entityManager = entityManager;
    }
    
    @Override
    public <S extends T> S save(S entity) {
//        entity.setModificationTimestamp(new Timestamp(System.currentTimeMillis()));
        return super.save(entity);
    }

	/**
     * 只做逻辑删除
     */
	@Override
	public void delete(T entity) {
		entity.setDr(BaseEntity.IS_DELETE_YES);
		//save(entity);
		entityManager.flush();
	}

	@Override
	public void delete(Serializable id) {
		T entity = findOne(id);
		entity.setDr(BaseEntity.IS_DELETE_YES);
		entityManager.flush();
		//this.save(entity);
	}
	
	@Override
	public T findOne(Serializable id){
		T t = super.findOne(id);
		if(null != t && !t.hasDeleted()){
			return t;
		}
		return null;
	}

	@Override
	public List<T> findAll() {
		List<T> all = super.findAll();
		return filterDeleted(all);
	}

	@Override
	public List<T> findAll(Iterable<Serializable> ids) {
		List<T> all = super.findAll(ids);
		return filterDeleted(all);
	}


	@Override
	public List<T> findAll(Sort sort) {
		List<T> all = super.findAll(sort);
		return filterDeleted(all);
	}
	
	

	@Override
	public T findOne(Specification<T> spec) {
		T t = super.findOne(spec);
		if(null != t && !t.hasDeleted())
			return t;
		return null;
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		List<T> all = super.findAll(spec);
		return filterDeleted(all);
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		List<T> all = super.findAll(spec, sort);
		return filterDeleted(all);
	}
	


	
//	@Override
//	public T findById(Serializable id) {
//		return findOne(id);
//	}
	
	/**
	 * 筛选掉标记为删除的记录
	 * @param all
	 * @return
	 */
	
	
	private List<T> filterDeleted(List<T> all) {
		List<T> list = new ArrayList<T>();
		
		if(null == all)
			return null;
		for(T t : all){
			if(!t.hasDeleted()){
				list.add(t);
			}
		}
		return list;
	}
	
	
}
