package com.library.wol.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.library.wol.model.BoardVO;
import com.library.wol.model.BookLoanDto;
import com.library.wol.model.DataDto;

public class DataDao extends SqlSessionDaoSupport{

	public void insertbook(Map<String, String> map) {
		getSqlSession().insert("data.insertbook", map);
	}
	
	public List<DataDto> select(Map<String, String> map) {
	return getSqlSession().selectList("data.select",map);
}

	public void loaninsert(Map<String, String> map) {
		getSqlSession().insert("data.loaninsert", map);
		getSqlSession().update("data.returnupdate", map);
		getSqlSession().update("data.loanupdate", map);
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

	public List<BookLoanDto> reservecheck(Map<String, String> map) {
		return getSqlSession().selectList("data.reservecheck", map);
	}
	
}
