package com.fanren.Traffic.controller;

import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanren.Traffic.pojo.User;
import com.fanren.Traffic.service.UserService;

@Controller
public class login {

	@Autowired
	private UserService userservice;
	
	@RequestMapping("/login")
	@ResponseBody
	public String CheakUserByIdAndPwd(@Param(value="username") String username,
										@Param(value="password") String password
										) throws Exception{
		List<User> list_user=userservice.getuser(username, password);
		if(list_user.size()==0) {
			System.out.println("错误");
			return null;
		}
		String user_name=list_user.get(0).getUsername();
		return user_name;
	}
	
}
