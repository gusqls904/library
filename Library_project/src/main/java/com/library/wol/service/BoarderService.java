package com.library.wol.service;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.wol.model.BoardCriteria;
import com.library.wol.model.BoardVO;
import com.library.wol.model.Paging;


@Service
public class BoarderService {

	@Autowired
	BoarderDao dao;

	
	//게시판 불러오기 메인부분
	public List<BoardVO> BoardList() {
		return dao.BoardList();
	}
	
	//게시판 글쓰기
	public void BoardWrite(BoardVO boader) {
		dao.BoardWrite(boader);
	}
	
	//게시판 상세보기
	public BoardVO viewDetaill(int bno) {
		return dao.viewDetaill(bno);
	}
	
	//게시판 카운트
	public void viewcnt(int bno) {
		dao.viewcnt(bno);
	}
	
	//게시판 각각 검색부분
		public List<BoardVO> boardselect(HashMap<String, Object> map) {
		return dao.boardselect(map);
	}
		
	//게시판 삭제 부분
		public int delete(int bno) {
			return dao.delete(bno);
		}
	
		//글수정부분 부분
		public int update(BoardVO BoardVO) {
			return dao.update(BoardVO);
		}
		

		//글수정 폼가져오는 부분
		public BoardVO boardupdateform(int bno) {
			return dao.boardupdateform(bno);
		}
		


		
	
		
	//페이징

		   public List<BoardVO> selectList(BoardVO dto) throws Exception {
		      List<BoardVO> list = null;
		      list = dao.list(dto);
		      return list;
		   }
		   
		   //검색부분 ajax 페이징 내가 추가한것
		   public BoardCriteria searchlist(HashMap<String, Object>map){ //p=1 per=10
		    	  int count = dao.searchcount(map);
			      if(count == 0) {
			         return new BoardCriteria();
			      }
			    //return value=게시판글갯수 개
			      int pageNum = (int) map.get("pageNum");
			      int per = (int) map.get("per");
			      int start = (pageNum - 1) * per; 
			      System.out.println("pageNum::"+pageNum+" start::"+start);
			      		//start = 0;
			      List<BoardVO> list = dao.getList(start, per, map);
			     
			      
			      Paging p = new Paging().paging(pageNum, count, per);
			      
			      return new BoardCriteria(list,pageNum,p.totalPageCount,start,p,count);
			      
			      //list == 아까불러온 10개의 리스트가 들어있고 // pagenum  1 , p.totalPageCount 10,
			      //start 0 , count 12
			   }
			   
		  
		   
		   public BoardCriteria list(int pageNum, int per,String s1, String s2){ //p=1 per=10
			      int count = dao.count();
			      if(count == 0) {
			         return new BoardCriteria();
			      }
		

			      //return value=게시판글갯수 12개
			      int start = (pageNum - 1) * per;   
			      		//start = 0;
			      List<BoardVO> list = dao.getList(start, per);
			      			//list 10개를 받음
			      			//pagenum 1, count 12, per 10
			      Paging p = new Paging().paging(pageNum, count, per);
			      
			      return new BoardCriteria(list,pageNum,p.totalPageCount,start,p,count);
			      
			      //list == 아까불러온 10개의 리스트가 들어있고 // pagenum  1 , p.totalPageCount 10,
			      //start 0 , count 12
			   }
		   
		   public BoardCriteria list(int pageNum, int per){ //p=1 per=10
		      int count = dao.count();
		      if(count == 0) {
		         return new BoardCriteria();
		      }
	

		      //return value=게시판글갯수 12개
		      int start = (pageNum - 1) * per;   
		      		//start = 0;
		      List<BoardVO> list = dao.getList(start, per);
		      			//list 10개를 받음
		      			//pagenum 1, count 12, per 10
		      Paging p = new Paging().paging(pageNum, count, per);
		      
		      return new BoardCriteria(list,pageNum,p.totalPageCount,start,p,count);
		      
		      //list == 아까불러온 10개의 리스트가 들어있고 // pagenum  1 , p.totalPageCount 10,
		      //start 0 , count 12
		   }
		   
		   public void setDao(BoarderDao dao) {
		      this.dao = dao;
		   }
		
}
