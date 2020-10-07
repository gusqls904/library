package com.library.wol.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.wol.model.SignDto;

@Service
public class LoginService {

	@Autowired
	LoginDao dao;

	public String checkidpw(HashMap<String, String> m) {
		return dao.checkidpw(m);
	} 
	
	public int namemailcheck(HashMap<String, String> m) {
		return dao.namemailcheck(m);
	}
	
	public int pwupdate(SignDto dto) {
		return dao.pwupdate(dto);
	}
	
	public SignDto profileselect(String userid) {
		return dao.profileselect(userid);
	}
	
	public int profileupdate(SignDto dto) {
		return dao.profileupdate(dto);
	}
	
}