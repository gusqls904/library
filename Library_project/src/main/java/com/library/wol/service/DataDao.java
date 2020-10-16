package com.library.wol.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.library.wol.model.BookLoanDto;
import com.library.wol.model.DataDto;

public class DataDao extends SqlSessionDaoSupport{
	
	//새 도서 등록
	public void insertbook(Map<String, String> map) {
		getSqlSession().insert("data.newinsertbook", map);
	}
	
	//도서 검색
	public List<DataDto> search(Map<String, String> map) {
	return getSqlSession().selectList("data.search",map);
}
	
	//도서 대여
	public void loaninsert(Map<String, String> map) {
		getSqlSession().insert("data.loaninsert", map);
		getSqlSession().update("data.loanupdate", map);
		getSqlSession().update("data.returnupdate", map);
	}
	
	//도서예약검사
	public String inspectreserve(Map<String, String> map) {
		return getSqlSession().selectOne("data.inspectreserve",map);
	}
	
	//도서예약
	public void insertreserve(Map<String, String> map) {
		getSqlSession().insert("data.insertreserve", map);
	}
	public void book_count(Map<String, String> map) {
		getSqlSession().update("data.book_count", map);
	}
	
	//유효성 검사
	public List<BookLoanDto> reservecheck(Map<String, String> map) {
		return getSqlSession().selectList("data.reservecheck", map);
	}
	
}
