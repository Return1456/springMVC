package com.zdq.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdq.springmvc.model.User;
import com.zdq.springmvc.model.UserService;

@Controller
public class Logincheck {
	private HttpServletRequest httpServletRequest;
	private UserService userService;
	@Autowired
	public void setHttpServletRequest(HttpServletRequest httpServletRequest){
		this.httpServletRequest=httpServletRequest;
	}
	@Autowired
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	@RequestMapping("/login")
public String check(){//��ת����½����
	return "login/login";
}
	@RequestMapping("/logincheck")
public String logincheck(String username,String password){ //��½����
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
	if(userService.checkUser(user)){
	    if(httpServletRequest!=null){	//��½�ɹ�����session�������˺�
	    	HttpSession session =httpServletRequest.getSession();
			session.setAttribute("user", user.getUsername());
	    }
		return "main/main";
	}else{
		return "login/login";
	}	
}
	@RequestMapping("/register")
public String register(){  //��ת��ע�����
	return "login/register";
}
	
	
	@RequestMapping("/doregister")
public String doregister(@RequestParam("username") String username,@RequestParam("password")String password){ //ע��
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		userService.addUser(user);
		return "login/login";
}
	
	@RequestMapping("/registercheck")
	@ResponseBody
	public String registercheck(String username){   //ע���û���Ψһ�Լ���
		if(userService.registercheck(username)){
		//	System.out.print("��ע����˺�");
			return "Account available";
		}else{
			//System.out.print("���˺��ѱ�ע��");
			return "Account registered";
		}
	}
}
