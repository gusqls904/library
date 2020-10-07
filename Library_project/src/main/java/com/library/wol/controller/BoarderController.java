package com.library.wol.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.library.wol.model.BoardCriteria;
import com.library.wol.model.BoardVO;
import com.library.wol.service.BoarderDao;
import com.library.wol.service.BoarderService;



@Controller
public class BoarderController {
	BoardVO boardvo;
	@Autowired
	BoarderService Bs;
	@Autowired
	BoarderDao Bdao;
	
	int viewcnt;

	
	@RequestMapping(value = "WriteForm.do")
	public String writeForm1(){
		return "witeForm";
	}
	
		
	@RequestMapping(value = "write.do")
	public String BoardWrite(BoardVO boader){
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		String data1 = date.format(today);
		System.out.println(date.format(today));
		boader.setRegdate(data1);
		System.out.println(boader);
		Bs.BoardWrite(boader);
		return "redirect:/main2.do"; 
	}
	
	
	@RequestMapping(value = "viewDetaill.do")
	public String viewDetaill(Model m,int bno){
		Bs.viewDetaill(bno);
		m.addAttribute("board",Bs.viewDetaill(bno));
		Bs.viewcnt(bno);
		return "viewDetail";
	}
	
	
	
	
	//게시판 검색 부분
	@RequestMapping(value = "boardselect.do" , produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardselect(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "per", defaultValue = "10") int per, String select,String search, Model m) {
			HashMap<String,Object> map = new HashMap<>();
			map.put("select",select);
			map.put("search", search);
			
			BoardCriteria lista = Bs.list(pageNum, per);
			m.addAttribute("boardList", lista);
		    int number = lista.getCount() - (pageNum - 1) * per;
		    m.addAttribute("number", number);
		    
		    
			System.out.println(map.get("search"));
			System.out.println(map.get("select123"));
			m.addAttribute(Bs.boardselect(map));
			Gson json = new Gson();
			return json.toJson(m);
	}
	
	
	
	//게시판 ajax 검색 부분
	@RequestMapping(value = "boardselectajax.do" , produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardselectajax(@RequestParam(value = "p", defaultValue = "1") int pageNum,
			@RequestParam(value = "per", defaultValue = "10") int per, String select,String search, Model m) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("per", per);
		map.put("select", select);
		map.put("search", search);
		map.put("pageNum", pageNum);
	
		
		BoardCriteria list = Bs.searchlist(map);
		m.addAttribute("boardList",list); // 페이징
		
		
		m.addAttribute("boardList1",list.getBoardInfoList()); // dto 값 가져오는 부분
		
		
		int number = list.getCount() - (pageNum - 1) * per;
	    m.addAttribute("number", number);
	    
		Gson json = new Gson();
		return json.toJson(m);
	}
	
	
	//게시판 검색부분
	@RequestMapping(value = "boardsearch.do" , produces="text/plain;charset=UTF-8")
	public String boardsearch(@RequestParam(value = "p", defaultValue = "1") int pageNum,
		@RequestParam(value = "per", defaultValue = "10") int per, String select,String search, Model m) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("per", per);
		map.put("select", select);
		map.put("search", search);
		map.put("pageNum", pageNum);
	
		
		BoardCriteria list = Bs.searchlist(map);
		m.addAttribute("boardList",list); // 페이징
		
		
		m.addAttribute("boardList1",list.getBoardInfoList()); // dto 값 가져오는 부분
		
		
		int number = list.getCount() - (pageNum - 1) * per;
	    m.addAttribute("number", number);
			return "Boardsearch";   
	}
	
	

	
	
	
//	@RequestMapping(value = "/Board.do")
//	public String Board(Model m) throws Exception {
//		List<BoardVO> list = Bs.BoardList();
//		System.out.println(list);
//		m.addAttribute("BoardList", list);
//		return "Board";
//	}
	
	
	// 페이징
		@RequestMapping("/Board.do")
	   public String list(@RequestParam(value = "p", defaultValue = "1") int pageNum,
	       @RequestParam(value = "per", defaultValue = "10") int per, Model m) {
	      BoardCriteria list = Bs.list(pageNum, per);
	      //list == 아까불러온 10개의 리스트가 들어있고 // pagenum  1 , p.totalPageCount 10,
	      //start 0 , count 12
	      m.addAttribute("boardList", list);
	      int number = list.getCount() - (pageNum - 1) * per;
	      System.out.println(number);
	      m.addAttribute("number", number);
	      return "Board";
	   }
	   
	   
	   
	   @GetMapping(value = "/pagingboardajax.do" , produces="text/plain;charset=UTF-8")
	   @ResponseBody
	   public String list1(@RequestParam(value = "p", defaultValue = "1") int pageNum,
	       @RequestParam(value = "per", defaultValue = "10") int per, Model m) {
	      BoardCriteria list = Bs.list(pageNum, per);
	      m.addAttribute("boardList", list);
	      int number = list.getCount() - (pageNum - 1) * per;
	      m.addAttribute("number", number);
	      Gson json = new Gson();
	      System.out.println(json.toJson(m));
	      return json.toJson(m);
	   }
	   
	   
	   
	   //게시글 삭제 기능
	   
	   @RequestMapping(value = "/boarddelete.do")
	   @ResponseBody
	   public int delete(int bno) {
		  return Bs.delete(bno);
	   }
	   
	   
	   
	   //선택한 게시물 불러오는 기능부분
	   @RequestMapping(value = "/boardupdateform.do")   
	   public String updateform(int bno,Model m){
		   System.out.println(bno);
		   Bs.boardupdateform(bno);
		   System.out.println(Bs.boardupdateform(bno));
		  m.addAttribute("boarderlist", Bs.boardupdateform(bno));
		  return "boardupdateform";
	   }
	   
	   
	   //게시글 수정 저장기능
	   @RequestMapping(value = "/boardupdate.do") 
	   @ResponseBody
	   public int boardupdate(int bno,String content, String title){
		   BoardVO BoardVO = new BoardVO();
		   BoardVO.setBno(bno);
		   BoardVO.setContent(content);
		   BoardVO.setTitle(title);
		   Bs.update(BoardVO);
		  return Bs.update(boardvo);
	   }
	   
	   
	   
}
	   


		
