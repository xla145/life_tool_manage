package cn.net.xulian.service;

import cn.net.xulian.vo.UserVO;

//业务逻辑层 
public interface UserService {
	public UserVO findUserInfoById(String id);
}
