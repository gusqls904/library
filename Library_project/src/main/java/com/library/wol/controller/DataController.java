package com.library.wol.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	@RequestMapping(value = "/newinsert.do")
	public String insertBook(String book_name, String author, String publisher, 
			String issueyear, MultipartFile report,HttpServletRequest request) {
		Map<String, String> map = new HashedMap<String, String>();

		// 이미지 경로 저장위치에 따라 바꿔야함(이미지폴더 경로)
		String path = "C:\\Users\\gusql\\Documents\\workspace-spring-tool-suite-4-4.7.0.RELEASE"
				+ "\\Library\\src\\main\\webapp\\resources\\Images";
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
	@RequestMapping(value = "/book_search.do", produces = "text/plain;charset=UTF-8")
	public String search(HttpServletRequest request, String search, String keyword, String book_id,
			Map<String, String> map, Model m) {

		// System.out.println("검색타입--" + keyword);
		// System.out.println("검색값--" + search);
		

		map.put("book_id", book_id);
		map.put("search", search);
		map.put("keyword", keyword);

		List<DataDto> dto = service.search(map);
		// System.out.println("검색 도서 목록" + dto);
		//System.out.println("이미지 경로" + dto.get(0).getImage());
		// 모델 "list"를 jsp로 넘김
		m.addAttribute("list", dto);

		return "dataselect";
	}

	// 도서 대여
	@RequestMapping(value = "/loaninsert.do")
	public String insert(String search, String book_id, Model model, HttpSession session, HttpServletResponse response)
			throws IOException {
		Map<String, String> m = new HashedMap<String, String>();
		// 세션 아이디 값
		String user_id = (String) session.getAttribute("user_id");

		// 컨트롤러에서 유효성 검사 후 (알러트 출력)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out_equals = response.getWriter();
		// 아이디 값 null일 경우 로그인 페이지로 이동
		if (user_id == null) {
			out_equals.println("<script>alert('로그인 하세요.');</script>");
			out_equals.flush();
			return "minilogin";
		} else { // 아이디 값 null이 아닐 경우 DB저장
			out_equals.println("<script>alert('대여 완료.');</script>");
			out_equals.flush();

			m.put("user_id", user_id);
			m.put("book_id", book_id);
			m.put("search", search);

			// System.out.println("대여 시작");
			service.loaninsert(m);

			List<DataDto> dto = service.search(m);
			model.addAttribute("list", dto);

			return "data";
		}

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
	public String insertreserve(String book_id, Model m, HttpSession session, 
			HttpServletResponse response) throws IOException {
		Map<String, String> map = new HashedMap<String, String>();

		// 세션에 저장된 아이디값
		String user_id = (String) session.getAttribute("user_id");

		map.put("user_id", user_id);
		map.put("book_id", book_id);
		
		List<BookLoanDto> dto = service.reservecheck(map);

		// 컨트롤러에서 유효성 검사 후 (알러트 출력)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out_equals = response.getWriter();
		if (user_id == null) {
			out_equals.println("<script>alert('로그인 하세요.');</script>");
			out_equals.flush();
			return "minilogin";
		} else {
			if (dto.size() == 0) {
				service.insertreserve(map);
				service.book_count(map);
				out_equals.println("<script>alert('예약 완료.');</script>");
				out_equals.flush();
			}
			for (int i = 0; i < dto.size(); i++) {
				if (dto.get(i).getBook_id().equals(book_id)) {
					out_equals.println("<script>alert('이미 대여하셨습니다.');</script>");
					out_equals.flush();
				}
			}
			return "data";
		}
	}

	// 예약 도서 대여
	@RequestMapping(value = "/reserveRental.do")
	public String reserveRental(String search, String book_id, Model model, HttpSession session) {
		Map<String, String> m = new HashedMap<String, String>();

		// 세션에 저장된 아이디값
		String user_id = (String) session.getAttribute("user_id");

		m.put("user_id", user_id);
		m.put("book_id", book_id);
		m.put("search", search);

		// System.out.println("예약도서 대여 시작");
		// 저장된 예약값 삭제
		loan.cancelreserve(m);
		// 대여목록에 저장
		service.loaninsert(m);
		// System.out.println("예약 도서 대여 완료");

		List<DataDto> dto = service.search(m);
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
