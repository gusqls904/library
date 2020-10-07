package com.library.wol.model;


public class Paging {

   private int beginPageNumber;
   private int endPageNumber;
   public int totalPageCount;
   
   public Paging() {}
   	//pagenum 1, count 12, per 10
   public Paging(int beginPageNumber, int endPageNumber, int totalPageCount) {
      super();
      this.beginPageNumber = beginPageNumber;
      this.endPageNumber = endPageNumber;
      this.totalPageCount = totalPageCount;
   }

   public Paging paging(int requestPage, int count, int per) {
      if (count == 0) {
         return new Paging(0,0,0);
      }
      int pageCount = count / per;
      if (count % per > 0) {
         pageCount++;
      }
      int beginNumber = (requestPage - 1) / 10 * 10 + 1;
      int endNumber = beginNumber + 9;
      if(endNumber > pageCount) {
         endNumber = pageCount;
      }
      return new Paging(beginNumber, endNumber, pageCount);
      
   }

   public int getBeginPageNumber() {
      return beginPageNumber;
   }

   public int getEndPageNumber() {
      return endPageNumber;
   }

   public int getTotalPageCount() {
      return totalPageCount;
   }
}