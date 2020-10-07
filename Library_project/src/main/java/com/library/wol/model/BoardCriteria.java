package com.library.wol.model;

import java.util.ArrayList;
import java.util.List;

public class BoardCriteria {


	   private int count;
	   private List<BoardVO> boardInfoList;
	   private int requestPage;
	   private int totalPageCount;
	   private int startRow;
	   
	   public int getCount() {
	      return count;
	   }

	   public void setCount(int count) {
	      this.count = count;
	   }

	   public int getRequestPage() {
	      return requestPage;
	   }

	   public void setRequestPage(int requestPage) {
	      this.requestPage = requestPage;
	   }

	   public int getTotalPageCount() {
	      return totalPageCount;
	   }

	   public void setTotalPageCount(int totalPageCount) {
	      this.totalPageCount = totalPageCount;
	   }

	   public int getStartRow() {
	      return startRow;
	   }

	   public void setStartRow(int startRow) {
	      this.startRow = startRow;
	   }

	   public Paging getP() {
	      return p;
	   }

	   public void setP(Paging p) {
	      this.p = p;
	   }

	
	   public List<BoardVO> getBoardInfoList() {
		return boardInfoList;
	}

	public void setBoardInfoList(List<BoardVO> boardInfoList) {
		this.boardInfoList = boardInfoList;
	}


	private Paging p;
	   
	   public BoardCriteria() {
	      this(new ArrayList<BoardVO>(), 0, 0, 0, new Paging(),0);   
	   }
	   
	   public BoardCriteria(List<BoardVO> boardInfoList, int requestPage, int totalPageCount, int startRow,
	         Paging p, int count) {
	      super();
	      this.boardInfoList = boardInfoList;
	      this.requestPage = requestPage;
	      this.totalPageCount = totalPageCount;
	      this.startRow = startRow;
	      this.p = p;
	      this.count = count;

	   }
	}
