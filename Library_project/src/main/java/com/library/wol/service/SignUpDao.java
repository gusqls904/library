package com.library.wol.service;



import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.library.wol.model.SignDto;


public class SignUpDao extends SqlSessionDaoSupport {

	public String checkid(String user_id) {
		return getSqlSession().selectOne("sign.checkid",user_id);
	}
	
	public int signup(SignDto dto) {
		return getSqlSession().insert("sign.signup",dto);
	}
	
	public void pwupdate(SignDto dto) {
		getSqlSession().insert("sign.pwupdate",dto);
	}
}
