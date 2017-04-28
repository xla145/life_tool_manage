package cn.net.xulian.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.xulian.dao.UserDao;
import cn.net.xulian.model.User;
import cn.net.xulian.service.UserService;
import cn.net.xulian.vo.UserVO;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	
	@Override
	public UserVO findUserInfoById(String id) {
		User user = userDao.findOne(id);
		UserVO userVO = new UserVO();
		if(user==null){
			return null;
		}else{
			BeanUtils.copyProperties(user, userVO);
			return userVO;
		}
	}
	
}
