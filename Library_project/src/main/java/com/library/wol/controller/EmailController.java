package com.library.wol.controller;

import java.io.Console;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.library.wol.model.EmailVO;
import com.library.wol.model.SignDto;
import com.library.wol.service.EmailService;
import com.library.wol.service.LoginDao;
import com.library.wol.service.LoginService;
import com.mysql.cj.Session;

@Controller
public class EmailController {

	String bb;

	SignDto dto;
	@Autowired
	LoginService service;
	@Autowired
	LoginDao dao;
	String disc;
	String receiver;

	

	@Autowired
	private EmailService emailService;
	@RequestMapping(value = "send.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String sendMail(String emailadd) throws Exception {
		EmailVO email = new EmailVO();
		receiver = emailadd; 
		System.out.println(emailadd);
		String subject = "Email 입니다.";
		disc = emailService.numberGen(6, 1);
		String content = "인증번호 이메일 입니다." + "인증번호는(" + disc + ")입니다";
		email.setReceiver(receiver);
		email.setSubject(subject);
		email.setContent(content);
		boolean result = emailService.sendMail(email);
		Gson json = new Gson();
		return json.toJson(result);
	}
	

	@RequestMapping(value = "mailcheck.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String mailcheck() throws Exception {
		return disc;
	}


	@RequestMapping(value = "idpwfind.do")
	public String idpwfind() {
		return "Idpwfind";
	}
	
	

	@RequestMapping(value = "pwchacge.do")
	public String pwchacge() {		
		return "Passchange";
	}
	
		
	@RequestMapping(value = "pwupdate.do")
	public ModelAndView pwupdate(String password) {
		ModelAndView mav = new ModelAndView("joinalert");
		dto = new SignDto();
		dto.setMail(receiver);
		dto.setPassword(password);
		int pwchange = service.pwupdate(dto);
		mav.addObject("pwchange", pwchange);
		return mav;	
	}
	

	@RequestMapping(value = "namemailcheck.do")
	@ResponseBody
	public int main(String name, String email) {
		HashMap<String, String> m = new HashMap<String, String>();
		m.put("name", name);
		m.put("email", email);
		int namemailcheck = service.namemailcheck(m);
		return namemailcheck;
	}
	
	@RequestMapping(value = "authentication.do")
	@ResponseBody
	public String authentication() {	
		return disc;
	}

}