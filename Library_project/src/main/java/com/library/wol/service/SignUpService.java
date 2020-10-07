package com.library.wol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.wol.model.SignDto;

@Service
public class SignUpService {

	@Autowired
	SignUpDao dao;

	public String checkid(String user_id) {
		return dao.checkid(user_id);
	} 
	
	public int  signup(SignDto dto) {
		return dao.signup(dto);
	} 

}