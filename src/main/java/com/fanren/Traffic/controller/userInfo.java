package com.fanren.Traffic.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanren.Traffic.pojo.User;
import com.fanren.Traffic.service.UserService;

/**
 * @author FanrenCLI
 * @Time 2018年10月3日-上午11:57:10
 * @version 0.0.1
 */
@Controller
public class userInfo {

	@Autowired
	private UserService userservice;
	
	@RequestMapping("/user_data")
	@ResponseBody
	public User getUserInfo(@Param(value="username") String username) {
		
		List<User> list_user=userservice.getuserbyName(username);
		if(list_user.size()==0) {
			return null;
		}
		User user =new User();
		user.setId(list_user.get(0).getId());
		user.setUsername(list_user.get(0).getUsername());
		user.setTel(list_user.get(0).getTel());
		user.setIdCard(list_user.get(0).getIdCard());
		return user;
	}
	
	@RequestMapping("/pwd_change")
	@ResponseBody
	public String changePwd(@Param(value="username") String username,
						  @Param(value="old_pwd") String old_pwd,
						  @Param(value="new_pwd") String new_pwd) {
		List<User> list_user=userservice.getuser(username, old_pwd);
		if(list_user.size()==0) {
			String error="error";
			return error;
		}else {
			userservice.updataPwd(new_pwd,username);
			String success="success";
			return success; 
		}
		
	}
	
	
	
	
	
	
}
