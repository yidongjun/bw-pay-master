package com.bw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bw.mapper.UserMapper;
import com.bw.pojo.User;
import com.bw.pojo.UserExample;
import com.bw.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	public void saveUser(User user) {
		userMapper.insert(user);
	}

	public void updateUserById(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	public void deleteUserById(String userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

	public User getUserById(String userId) {
		
		return userMapper.selectByPrimaryKey(userId);
	}
	
	public List<User> getUserList() {
		
		UserExample ue = new UserExample();
		List<User> userList = userMapper.selectByExample(ue);
		
		return userList;
	}

}
