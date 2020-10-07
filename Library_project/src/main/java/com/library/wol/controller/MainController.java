package com.library.wol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.wol.model.BoardCriteria;
import com.library.wol.model.BoardVO;
import com.library.wol.service.BoarderDao;
import com.library.wol.service.BoarderService;

@Controller
public class MainController {
	BoardVO boardvo;
	@Autowired
	BoarderService Bs;
	@Autowired
	BoarderDao Bdao;

	
	 
	
		@RequestMapping("/main.do")
	   public String list(@RequestParam(value = "p", defaultValue = "1") int pageNum,
	       @RequestParam(value = "per", defaultValue = "10") int per, Model m) {
	      BoardCriteria list = Bs.list(pageNum, per);
	      //list == �Ʊ�ҷ��� 10���� ����Ʈ�� ����ְ� // pagenum  1 , p.totalPageCount 10,
	      //start 0 , count 12
	      m.addAttribute("boardList", list);
	      int number = list.getCount() - (pageNum - 1) * per;
	      System.out.println(number);
	      m.addAttribute("number", number);
	      return "main";
	   }
		
		
		@RequestMapping("/main2.do")
		   public String list2(@RequestParam(value = "p", defaultValue = "1") int pageNum,
		       @RequestParam(value = "per", defaultValue = "10") int per, Model m,HttpSession Session) {
		      BoardCriteria list = Bs.list(pageNum, per);
		      m.addAttribute("boardList", list);
		      int number = list.getCount() - (pageNum - 1) * per;
		      System.out.println(number);
		      m.addAttribute("number", number);
		      if(Session.getAttribute("user_id") != null) {
		    	  return "main2";
		      }
		      else {
		      return "main";
		      }
		   }
	
	
	
	

	@RequestMapping(value = "loginpage.do")
	public String login() {
		return "loginpage";
	}
	
	

}
