package cn.net.xulian.dao;

import cn.net.xulian.dao.baseDao.BaseDao;
import cn.net.xulian.model.User;

//实体Dao层 可以自己定义增删改查的方法也可直接使用jpa的方法
public interface UserDao extends BaseDao<User>{
	
}
