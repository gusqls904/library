package com.library.wol.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.library.wol.model.Book_ReserveDto;
import com.library.wol.model.DataDto;
import com.library.wol.model.LoanDto;

public class LoanDao extends SqlSessionDaoSupport{
	
	public List<LoanDto> loanselect(String user_id) {	
		return getSqlSession().selectList("loan.loanselect", user_id);
	}
	
	public void returnbook(Map<String, String> map) {
		getSqlSession().update("loan.returnbook",map);
		getSqlSession().update("loan.returnbook1",map);
	}
	
	public void bookextension(Map<String, String> map) {
		getSqlSession().update("loan.bookextension",map);
	}
	
	public List<Book_ReserveDto> loan_reserve(String user_id) {	
		return getSqlSession().selectList("loan.loan_reserve", user_id);
	}
	
	public void cancelreserve(Map<String, String> map) {
		getSqlSession().delete("loan.cancelreserve",map);
		getSqlSession().update("loan.cancelreserve1",map);
	}
	
	public List<LoanDto> loan_history(String user_id) {	
		return getSqlSession().selectList("loan.loan_history", user_id);
	}

	public String extensioncheck(Map<String, String> map) {
		return getSqlSession().selectOne("loan.extensioncheck", map);
	}
	
	
	
}
