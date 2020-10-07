package com.library.wol.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.library.wol.model.BoardVO;
import com.library.wol.model.Criteria;

public class BoarderDao extends SqlSessionDaoSupport {

	public List<BoardVO> BoardList() {
		return getSqlSession().selectList("Board.BoardList");
	}

	public void BoardWrite(BoardVO boader) {
		getSqlSession().insert("Board.BoardWrite", boader);
	}

	public BoardVO viewDetaill(int bno) {
		return getSqlSession().selectOne("Board.ViewDetaill", bno);
	}

	public void viewcnt(int bno) {
		getSqlSession().update("Board.Viewcnt", bno);
	}

	public List<BoardVO> boardselect(HashMap<String, Object> map) {
		return getSqlSession().selectList("Board.Selectone", map);
	}

	// 게시판 삭제 부분

	public int delete(int bno) {
		return getSqlSession().delete("Board.delete", bno);
	}

	// 게시글 수정 부분

	public int update(BoardVO BoardVO) {
		return getSqlSession().update("Board.update", BoardVO);
	}

	// 글수정 폼가져오는 부분
	public BoardVO boardupdateform(int bno) {
		return getSqlSession().selectOne("Board.boardupdateform", bno);
	}

	// 게시물 목록 조회
	public List<BoardVO> list(Criteria cri) throws Exception {
		return getSqlSession().selectList("boardMapper.listPage", cri);
	}

	// 페이징
	public List<BoardVO> list(BoardVO dto) throws Exception {
		return getSqlSession().selectList("Board.BoardList");
	}

	public int selectMax() {
		return getSqlSession().selectOne("Board.max");
	}

	public BoardVO getContent(int bno) {
		return getSqlSession().selectOne("Board.one", bno);
	}

	public int count() {
		return getSqlSession().selectOne("Board.count");
	}

	public int searchcount(HashMap<String, Object> map) {
		return getSqlSession().selectOne("Board.searchcount", map);
	}

	public int serachcount() {
		return getSqlSession().selectOne("Board.serachcount");
	}

	public List<BoardVO> getList(int start, int per) {// start 0 per 10
		Map<String, Integer> map = new HashMap<>();
		map.put("start", start);
		map.put("per", per);
		return getSqlSession().selectList("Board.list", map);
	}

	public List<BoardVO> getList(int start, int per, Map<String, Object> map) {// start 0 per 10
		map.put("start", start);
		map.put("per", per);
		List<BoardVO> list =  getSqlSession().selectList("Board.list", map);
		System.out.println("list.size()::"+list.size()+" "+ start+" " + per);
		return list;
	}

}
