package com.library.wol.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.library.wol.model.SignDto;


public class LoginDao extends SqlSessionDaoSupport {

	public String checkidpw(HashMap<String, String> m) {
		return getSqlSession().selectOne("sign.checkidpw",m);
	}
	
	public int namemailcheck(HashMap<String, String> m) {
		return getSqlSession().selectOne("sign.namemailcheck",m);
	}
	
	public int pwupdate(SignDto dto) {
		return getSqlSession().insert("sign.pwupdate",dto);
	}
	
	public SignDto profileselect(String userid) {
		return getSqlSession().selectOne("sign.profileselect",userid);
	}
	

	public int profileupdate(SignDto dto) {
		return getSqlSession().update("sign.profileupdate",dto);
	}
	

}
