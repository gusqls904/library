package com.library.wol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.library.wol.model.SignDto;
import com.library.wol.service.LoginDao;
import com.library.wol.service.SignUpDao;
import com.library.wol.service.SignUpService;

@Controller
public class SignUpController {
	@Autowired
	SignUpDao dao;	
	@Autowired
	SignUpService service;


	
	@RequestMapping(value = "sign.do")
	public String main() {
		return "sign";
	}

	@RequestMapping(value="checkid.do",method = RequestMethod.POST)
	@ResponseBody
	 public String idcheck(String user_id) {
		String aa = service.checkid(user_id);
	      return aa;
	   }
	

	@RequestMapping(value = "signinsert.do")
	public ModelAndView reservationPro(SignDto dto) {
		ModelAndView mav = new ModelAndView("joinalert");
		int check = service.signup(dto);
		mav.addObject("check", check);
		return mav;
	}
	

	@RequestMapping(value = "mailauthentication.do",method = RequestMethod.POST)
	public String mail() {
		return "mailauthentication";
	}
	
	
}
