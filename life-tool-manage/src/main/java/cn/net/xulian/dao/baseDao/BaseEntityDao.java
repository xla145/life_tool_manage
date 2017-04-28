package cn.net.xulian.dao.baseDao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * 基础DAO类
 *
 * @author Zhi
 * @time 2016年4月27日18:25:23
 */
public class BaseEntityDao {
	@PersistenceContext
    private EntityManager entityManager;

    public BaseEntityDao() {
        super();
    }

    public BaseEntityDao(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * 按顺序设置Query参数
     *
     * @param query
     * @param paras
     */
    private void setParameters(Query query, Map<String, Object> paras) {
        if (paras == null || paras.isEmpty()) {
            return;
        }
        Set<String> set = paras.keySet();
        for (String key : set) {
            query.setParameter(key, paras.get(key));
        }
    }

    /**
     * 按顺序设置Query参数
     *
     * @param query
     * @param paras
     */
    private void setParameters(Query query, List<Object> paras) {
        if (paras == null || paras.isEmpty()) {
            return;
        }
        for (int i = 0; i < paras.size(); i++) {
            query.setParameter(i, paras.get(i));
        }
    }

    /**
     * 根据SQL语句获取一条记录
     *
     * @param sql
     * @param paras
     * @return
     */
    public Object findOneBySql(String sql, List<Object> paras) {
        Query query = entityManager.createNativeQuery(sql);
        setParameters(query, paras);
        return query.getSingleResult();
    }

    /**
     * 根据SQL语句查询列表
     *
     * @param sql      已拼装好带"?"的SQL语句
     * @param paras    参数
     * @param offset   起始查询号
     * @param pageSize 分页大小
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> findListBySql(String sql, Map<String, Object> paras, int offset, int pageSize) {
        Query rquery = entityManager.createNativeQuery(sql);
        setParameters(rquery, paras);
        rquery.setFirstResult(offset);
        rquery.setMaxResults(pageSize);
        return rquery.getResultList();
    }

    /**
     * 根据SQL语句查询列表
     *
     * @param sql      已拼装好带"?"的SQL语句
     * @param paras    参数
     * @param offset   起始查询号
     * @param pageSize 分页大小
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> findListBySql(String sql, List<Object> paras, int offset, int pageSize) {
        Query rquery = entityManager.createNativeQuery(sql);
        setParameters(rquery, paras);
        rquery.setFirstResult(offset);
        rquery.setMaxResults(pageSize);
        return rquery.getResultList();
    }

    /**
     * 根据SQL语句查询列表
     *
     * @param sql
     * @param paras
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> findListBySql(String sql, Map<String, Object> paras) {
        Query rquery = entityManager.createNativeQuery(sql);
        setParameters(rquery, paras);
        return rquery.getResultList();
    }

    /**
     * 根据SQL语句查询分页记录
     *
     * @param sql           已拼装好"?"的SQL语句
     * @param paras
     * @param pageable
     * @param iResultHander
     * @return
     */
    public Page<Object> findListBySql(String sql, Map<String, Object> paras, Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<Object>(findListBySql(sql, paras));
        }
        String csql = "select count(1) from (" + sql + ") t";
        return findListBySql(sql, csql, paras, pageable);
    }

    /**
     * 根据SQL语句查询分页记录
     *
     * @param qsql     用于查询返回记录的SQL语句
     * @param csql     用于查询记录条数的SQL语句
     * @param paras
     * @param pageable
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page<Object> findListBySql(String qsql, String csql, Map<String, Object> paras, Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<Object>(findListBySql(qsql, paras));
        }
        Query rquery = entityManager.createNativeQuery(qsql);
        Query cquery = entityManager.createNativeQuery(csql);
        setParameters(rquery, paras);
        setParameters(cquery, paras);

        rquery.setFirstResult(pageable.getOffset());
        rquery.setMaxResults(pageable.getPageSize());

        List<Object> list = rquery.getResultList();
        long total = ((BigInteger) cquery.getSingleResult()).longValue();

        return new PageImpl<Object>(list, pageable, total);
    }

    /**
     * 根据SQL语句查询列表
     *
     * @param sql
     * @param paras
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> findListBySql(String sql, List<Object> paras) {
        Query rquery = entityManager.createNativeQuery(sql);
        setParameters(rquery, paras);
        return rquery.getResultList();
    }

    /**
     * 根据SQL语句查询分页记录
     *
     * @param sql      已拼装好"?"的SQL语句
     * @param paras
     * @param pageable
     * @return
     */
    public Page<Object> findListBySql(String sql, List<Object> paras, Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<Object>(findListBySql(sql, paras));
        }
        String csql = "select count(1) from (" + sql + ") t";
        return findListBySql(sql, csql, paras, pageable);
    }

    /**
     * 根据SQL查询SQL语句
     *
     * @param qsql
     * @param csql
     * @param paras
     * @param pageable
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page<Object> findListBySql(String qsql, String csql, List<Object> paras, Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<Object>(findListBySql(qsql, paras));
        }
        Query rquery = entityManager.createNativeQuery(qsql);
        Query cquery = entityManager.createNativeQuery(csql);
        setParameters(rquery, paras);
        setParameters(cquery, paras);

        rquery.setFirstResult(pageable.getOffset());
        rquery.setMaxResults(pageable.getPageSize());

        List<Object> list = rquery.getResultList();
        long total = ((BigInteger) cquery.getSingleResult()).longValue();

        return new PageImpl<Object>(list, pageable, total);
    }

    @SuppressWarnings("Duplicates")
    public <T> Page<T> findPageBySql(String sql, Map<String, Object> params, Pageable pageable, Class<T> c) {
        Query query = entityManager.createNativeQuery(sql);
        setParameters(query, params);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(c));

        long total = 0;
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());

            String countSql = "select count(0) from (" + sql + ") t";
            Query countQuery = entityManager.createNativeQuery(countSql);
            total = ((BigInteger) countQuery.getSingleResult()).longValue();
        }

        @SuppressWarnings("unchecked")
        List<T> content = query.getResultList();
        return new PageImpl<>(content, pageable, pageable != null ? total : content.size());
    }

    @SuppressWarnings("Duplicates")
    public <T> Page<T> findPageBySql(String sql, List params, Pageable pageable, Class<T> c) {
        Query query = entityManager.createNativeQuery(sql);
        //noinspection unchecked
        setParameters(query, params);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(c));

        long total = 0;
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());

            String countSql = "select count(0) from (" + sql + ") t";
            Query countQuery = entityManager.createNativeQuery(countSql);
            total = ((BigInteger) countQuery.getSingleResult()).longValue();
        }

        @SuppressWarnings("unchecked")
        List<T> content = query.getResultList();
        return new PageImpl<>(content, pageable, pageable != null ? total : content.size());
    }

    public <T> List<T> findListBySql(String hql, Map<String, Object> params, Class<T> c) {
        Query query = entityManager.createNativeQuery(hql);
        setParameters(query, params);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(c));

        //noinspection unchecked
        return query.getResultList();
    }

    public <T> List<T> findListBySql(String hql, List params, Class<T> c) {
        Query query = entityManager.createNativeQuery(hql);
        //noinspection unchecked
        setParameters(query, params);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(c));

        //noinspection unchecked
        return query.getResultList();
    }
}
