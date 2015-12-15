package com.hanains.guestbook.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.guestbook.vo.GuestBookVo;

@Repository
public class GuestBookDAO {

	@Autowired
	private SqlSession sqlSession;
	
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}

	public void delete(GuestBookVo vo) {	
		sqlSession.selectOne("guestbook.deleteGuestBook", vo);	
	}

	public void insert(GuestBookVo vo) {
		sqlSession.selectOne("guestbook.insertGuestBook", vo);	
	}
	
}
