package com.library.wol.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.wol.model.Book_ReserveDto;
import com.library.wol.model.LoanDto;
import com.library.wol.service.LoanService;

@Controller
public class LoanController {

	@Autowired
	LoanService service;

	// 대출 도서 현황
	@RequestMapping(value = "/loan.do")
	public String loan(Model m, HttpSession session) {
		List<LoanDto> list = new ArrayList<LoanDto>();

		String user_id = (String) session.getAttribute("user_id"); 

		list = service.loanselect(user_id);
		m.addAttribute("list", list);

		return "loan";
	}

	// 도서 반납
	@RequestMapping(value = "/returnbook.do")
	public String returnbook(String book_id,HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();

		String user_id = (String) session.getAttribute("user_id"); 
		
		map.put("book_id", book_id);
		map.put("user_id", user_id);
		
		service.returnbook(map);

		return "redirect:/loan.do";
	}
	
	//도서 기간 연장
	@RequestMapping(value = "/bookextension.do")
	@ResponseBody
	public String bookextension(String book_id,Model m,HttpSession session,
			HttpServletResponse response) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
			
		String user_id = (String) session.getAttribute("user_id"); 
		String res = null;
		map.put("user_id", user_id);
		map.put("book_id", book_id);
		
		String reserve_no = service.extensioncheck(map);
		System.out.println(reserve_no);
		if (reserve_no == null) {
			 service.bookextension(map);
	         res = "0";
		}else if(reserve_no.equals("1")){
	         res = "1";
			 }
		return res;
	}

	// 예약 도서 현황
	@RequestMapping(value = "/loan_reserve.do")
	public String loan_reserve(Model m, HttpSession session) {

		List<Book_ReserveDto> list = new ArrayList<Book_ReserveDto>();

		String user_id = (String) session.getAttribute("user_id"); 

		list = service.loan_reserve(user_id);

		m.addAttribute("list", list);

		return "loan_reserve";
	}

	// 예약 취소
	@RequestMapping(value = "/cancelreserve.do")
	public String cancelreserve(String book_id, Model m, HttpSession session) {

		String user_id = (String) session.getAttribute("user_id"); 
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("user_id", user_id);
		map.put("book_id", book_id);
		
		service.cancelreserve(map);
		
		return "redirect:loan_reserve.do";
	}
	
	// 반납 도서현황
	@RequestMapping(value = "/loan_history.do")
	public String loan_history(Model m, HttpSession session) {
		List<LoanDto> list = new ArrayList<LoanDto>();

		String user_id = (String) session.getAttribute("user_id"); 
		
		list = service.loan_history(user_id);
		
		m.addAttribute("list", list);
		
		return "loan_history";
	}


}
