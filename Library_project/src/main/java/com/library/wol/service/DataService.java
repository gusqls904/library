package com.library.wol.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.wol.model.BoardCriteria;
import com.library.wol.model.BoardVO;
import com.library.wol.model.BookLoanDto;
import com.library.wol.model.DataDto;
import com.library.wol.model.Paging;

@Service
public class DataService {

	@Autowired
	DataDao dao;

	public void setDao(DataDao dao) {
		this.dao = dao;
	}

	public void insertbook(Map<String, String> map) {
		dao.insertbook(map);
	}

	public List<DataDto> select(Map<String, String> map) {
		return dao.select(map);
	}


	public void loaninsert(Map<String, String> map) {
		dao.loaninsert(map);

	}

	public String inspectreserve(Map<String, String> map) {
		return dao.inspectreserve(map);
	}
	
	public void insertreserve(Map<String, String> map) {	
		dao.insertreserve(map);
	}
	
	public void book_count(Map<String, String> map) {	
		dao.book_count(map);
	}

	public List<BookLoanDto> reservecheck(Map<String, String> map) {
		
		return dao.reservecheck(map);
		
	}
	
	//페이징

//	   public List<BoardVO> selectList(BoardVO dto) throws Exception {
//	      List<BoardVO> list = null;
//	      list = dao.list(dto);
//	      return list;
//	   }
//
//	   public SelectCriteria list(int pageNum, int per){ //p=1 per=10
//	      int count = dao.count();
//	      if(count == 0) {
//	         return new SelectCriteria();
//	      }
//	      //return value=게시판글갯수 12개
//	      int start = (pageNum - 1) * per;   
//	      		//start = 0;
//	      List<SelectVO> list = dao.getList(start, per);
//	      			//list 10개를 받음
//	      			//pagenum 1, count 12, per 10
//	      Paging p = new Paging().paging(pageNum, count, per);
//	      
//	      return new BoardCriteria(list,pageNum,p.totalPageCount,start,p,count);
//	      
//	      //list == 아까불러온 10개의 리스트가 들어있고 // pagenum  1 , p.totalPageCount 10,
//	      //start 0 , count 12
//	   }
//	   
//	   public void setDao(BoarderDao dao) {
//	      this.dao = dao;
//	   }
//
//	public SelectCriteria dataselect(int pageNum, int per) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
