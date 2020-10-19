package com.library.wol.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.library.wol.model.Book_ReserveDto;
import com.library.wol.model.DataDto;
import com.library.wol.model.LoanDto;

public class LoanDao extends SqlSessionDaoSupport{
	//대여중인 도서 리스트
	public List<LoanDto> loanselect(String user_id) {	
		return getSqlSession().selectList("loan.loanselect", user_id);
	}
	//도서 반납
	public void returnbook(Map<String, String> map) {
		getSqlSession().update("loan.returnbook",map);
		getSqlSession().update("loan.returnbook1",map);
		getSqlSession().update("loan.updateReserve_date",map);
	}
	//도서 연장 유효성 검사
	public String extensioncheck(Map<String, String> map) {
		return getSqlSession().selectOne("loan.extensioncheck", map);
	}
	//도서 다중연장 검사
	public int extensioncheck2(Map<String, String> map) {
		return getSqlSession().selectOne("loan.extensioncheck2", map);
	}
	//도서 기간 연장
	public void bookextension(Map<String, String> map) {
		getSqlSession().update("loan.bookextension",map);
	}
	//예약중인 도서리스트
	public List<Book_ReserveDto> loan_reserve(String user_id) {	
		return getSqlSession().selectList("loan.loan_reserve", user_id);
	}
	//예약취소
	public void cancelreserve(Map<String, String> map) {
		getSqlSession().delete("loan.cancelreserve",map);
		getSqlSession().update("loan.cancelreserve1",map);
	}
	//반납한 도서리스트
	public List<LoanDto> loan_history(String user_id) {	
		return getSqlSession().selectList("loan.loan_history", user_id);
	}
}
