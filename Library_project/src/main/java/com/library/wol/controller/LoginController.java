package com.library.wol.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.wol.model.BoardCriteria;
import com.library.wol.model.BoardVO;
import com.library.wol.model.SignDto;
import com.library.wol.service.BoarderDao;
import com.library.wol.service.BoarderService;
import com.library.wol.service.LoginDao;
import com.library.wol.service.LoginService;


@Controller
public class LoginController {
	
	BoardVO boardvo;
	@Autowired
	BoarderService Bs;
	@Autowired
	BoarderDao Bdao;
	

	SignDto dto;
	@Autowired
	LoginService service;
	@Autowired
	LoginDao dao;
	
	int idcheck;
	
	
	@RequestMapping(value ="login.do",method = RequestMethod.POST)
	@ResponseBody
	public int main(String user_id,String pw,HttpSession Session) {
	HashMap<String, String> m = new HashMap<String, String>();
	m.put("user_id", user_id);
	m.put("password",pw);
	String User_iddb = service.checkidpw(m);
	if(User_iddb != null && User_iddb.equals(user_id)) {
		idcheck = 1;
		Session.setAttribute("user_id", user_id); 
		
	}
	else {
		idcheck = 0;
	}
	return idcheck;
	}
	
	
	
	@RequestMapping("logout.do")
	   public String list2(@RequestParam(value = "p", defaultValue = "1") int pageNum,
	       @RequestParam(value = "per", defaultValue = "10") int per, Model m,HttpSession Session) {
	      BoardCriteria list = Bs.list(pageNum, per);
	      m.addAttribute("boardList", list);
	      int number = list.getCount() - (pageNum - 1) * per;
	      System.out.println(number);
	      m.addAttribute("number", number);
	  	Session.invalidate();
	      return "main";
	   }
}
