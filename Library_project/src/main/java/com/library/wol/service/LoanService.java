package com.library.wol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.wol.model.Book_ReserveDto;
import com.library.wol.model.DataDto;
import com.library.wol.model.LoanDto;

@Service
public class LoanService {

	@Autowired
	LoanDao dao;

	public void setDao(LoanDao dao) {
		this.dao = dao;
	}

	public List<LoanDto> loanselect(String user_id) {
		return dao.loanselect(user_id);
	}
	
	public void returnbook(Map<String, String> map) {
		dao.returnbook(map);
	}
	
	public List<Book_ReserveDto> loan_reserve(String user_id) {
		return dao.loan_reserve(user_id);
	}
	
	public void cancelreserve(Map<String, String> map) {
		dao.cancelreserve(map);
	}
	
	public void bookextension(Map<String, String> map) {
		dao.bookextension(map);
	}
	
	public List<LoanDto> loan_history(String user_id) {
		return dao.loan_history(user_id);
	}

	public String extensioncheck(Map<String, String> map) {
		return dao.extensioncheck(map);
	}
	
	
	

}
