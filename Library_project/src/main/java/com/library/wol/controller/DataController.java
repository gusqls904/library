package com.library.wol.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.library.wol.model.BoardCriteria;
import com.library.wol.model.BookLoanDto;
import com.library.wol.model.DataDto;
import com.library.wol.service.DataService;
import com.library.wol.service.LoanService;

@Controller
public class DataController {

	@Autowired
	DataService service;
	@Autowired
	LoanService loan;

	@RequestMapping(value = "/insertbook.do")
	public String insertbook() {
		return "insertbook";
	}

	@RequestMapping(value = "/data.do")
	public String data() {
		return "data";
	}
	
	@RequestMapping(value = "/dataselect.do")
	public String dataselect() {
		return "dataselect";
	}

	// 신규 독서 등록
	@RequestMapping(value = "/insert.do")
	public String insertBook(String book_name, String author, String publisher, String issueyear, MultipartFile report,
			HttpServletRequest request) {
		Map<String, String> map = new HashedMap<String, String>();

		// 이미지 경로 저장위치에 따라 바꿔야함
		String path = "C:\\Users\\gusql\\Documents\\workspace-spring-tool-suite-4-4.7.0.RELEASE\\Library\\src\\main\\webapp\\resources\\Images";
		String alterpath = "resources\\Images\\";

		File file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		path += "\\" + report.getOriginalFilename();
		alterpath += report.getOriginalFilename();

		file = new File(path);

		try {
			report.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		map.put("book_name", book_name);
		map.put("image", alterpath);
		map.put("author", author);
		map.put("publisher", publisher);
		map.put("issueyear", issueyear);

		service.insertbook(map);

		return "insertbook";
	}

	// 도서 검색
	@RequestMapping(value = "/select.do", produces = "text/plain;charset=UTF-8")
	public String select(HttpServletRequest request,String select,String select1, String book_id, Map<String, String> map, Model m) {	
		System.out.println("검색타입--" + select1);
		System.out.println("검색값--" + select);
		
		map.put("book_id", book_id);
		map.put("select", select);
		map.put("select1", select1);
		
		List<DataDto> dto = service.select(map);

		m.addAttribute("list", dto);
				
		return "dataselect";
	}

	// 도서 대여
	@RequestMapping(value = "/loaninsert.do")
	public String insert(String select,String book_id,Model model, HttpSession session) {

		Map<String, String> m = new HashedMap<String, String>();
	
		String user_id = (String) session.getAttribute("user_id"); 
		
		m.put("user_id", user_id);
		m.put("book_id", book_id);
		m.put("select", select);
		
		System.out.println("대여 시작");
		service.loaninsert(m);
		
		List<DataDto> dto = service.select(m);
		model.addAttribute("list", dto);
		
		return "data";
	}
	
	// 도서 대여 ajax연습(수정중)
//	@RequestMapping(value = "/loaninsert1.do")
//	@ResponseBody
//	public String insert1(String select,String book_id,Model model, HttpSession session) {
//
//		Map<String, String> m = new HashedMap<String, String>();
//	
//		String user_id = (String) session.getAttribute("user_id"); 
//		
//		m.put("user_id", user_id);
//		m.put("book_id", book_id);
//		m.put("select", select);
//		
//		System.out.println("대여 시작");
//		service.loaninsert(m);
//		
//		Gson json = new Gson();
//		
//		List<DataDto> dto = service.select(m);
//		model.addAttribute("list", dto);
//		
//		return json.toJson(model);
//	}

	// 도서 예약
	@RequestMapping(value = "/BookReserve.do")
	public String insertreserve(String book_id,Model m, HttpSession session,
		HttpServletResponse response) throws IOException {
		Map<String, String> map = new HashedMap<String, String>();
		
		String user_id = (String) session.getAttribute("user_id"); 
		map.put("user_id", user_id);
		map.put("book_id", book_id);
		
		List<BookLoanDto> dto =  service.reservecheck(map);
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out_equals = response.getWriter();			
        
        if(dto.size() == 0){
				service.insertreserve(map);
				service.book_count(map);
				out_equals.println("<script>alert('예약 완료.');</script>");
		        out_equals.flush();
        }for (int i = 0; i < dto.size(); i++) {
        	if(dto.get(i).getBook_id().equals(book_id)) {
				out_equals.println("<script>alert('이미 대여하셨습니다.');</script>");
		        out_equals.flush(); 		        
			}	
		}
        			return "data";
	}
	
	//예약 도서 대여
	@RequestMapping(value = "/reserveRental.do")
	public String reserveRental(String select,String book_id,Model model, HttpSession session) {

		Map<String, String> m = new HashedMap<String, String>();
		
		String user_id = (String) session.getAttribute("user_id"); 
		
		m.put("user_id", user_id);
		m.put("book_id", book_id);
		m.put("select", select);
		
		loan.cancelreserve(m);
		
		System.out.println("대여 시작");
		service.loaninsert(m);
		
		List<DataDto> dto = service.select(m);
		model.addAttribute("list", dto);
		
		return "data";
	}
	
//	@RequestMapping("/Board.do")
//	   public String list(@RequestParam(value = "p", defaultValue = "1") int pageNum,
//	       @RequestParam(value = "per", defaultValue = "10") int per, Model m) {
//	      SelectCriteria list = service.dataselect(pageNum, per);
//	      //list == 아까불러온 10개의 리스트가 들어있고 // pagenum  1 , p.totalPageCount 10,
//	      //start 0 , count 12
//	      m.addAttribute("selectList", list);
//	      int number = list.getCount() - (pageNum - 1) * per;
//	      System.out.println(number);
//	      m.addAttribute("number", number);
//	      return "Board";
//	   }

}
