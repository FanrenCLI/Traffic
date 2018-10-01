package com.fanren.Traffic.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fanren.Traffic.DAO.UserMapper;
import com.fanren.Traffic.pojo.User;
import com.fanren.Traffic.pojo.UserExample;
import com.fanren.Traffic.pojo.UserExample.Criteria;


/**
 * @author FanrenCLI
 * @Time 2018年9月24日-上午7:43:26
 * @version 0.0.1
 * 用户操作控制类
 */
@Service
public class UserService {

	@Resource
	private UserMapper usermapper;
	
	
	public List<User> getuser(String username,String password){
		
		UserExample userexample=new UserExample();
		Criteria criteria=userexample.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		return usermapper.selectByExample(userexample);
	}


	public List<User> getuserbyName(String username) {
		// TODO Auto-generated method stub
		UserExample userexample=new UserExample();
		Criteria criteria=userexample.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		return usermapper.selectByExample(userexample);
	}


	public void updataPwd(String new_pwd,String username) {
		// TODO Auto-generated method stub
		User user= new User();
		user.setPassword(new_pwd);
		UserExample userexample=new UserExample();
		Criteria criteria=userexample.createCriteria();
		criteria.andUsernameEqualTo(username);
		usermapper.updateByExampleSelective(user, userexample);
	}


	
}
