package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.BoardDao;
import com.zigui.domain.Board;
import com.zigui.domain.NewsChannle;
import com.zigui.utils.Page;

public class BoardServiceImpl {
	
	@Autowired
	private BoardDao boardDao;
private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public long saveOrUpdate(Board board){
		boardDao.saveOrUpdate(board);	
		return board.getId();
	}
	
	public Page<Board> listByCondition(@SuppressWarnings("rawtypes") Map query, int pageNo, int pageSize, String orderBy, boolean isAsc,String queryString){
		List<Criterion> criterionsList = new ArrayList<Criterion>(query.size());
		if(StringUtils.isNumeric((String)query.get("creatorId")) && !"0".equals(query.get("creatorId"))){
			criterionsList.add(Restrictions.eq("creatorId", NumberUtils.toLong((String)query.get("creatorId"))));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("creatorNick"))){
			criterionsList.add(Restrictions.eq("creatorNick", (String)query.get("creatorNick")));
		}
		
		if(query.get("state") != null && NumberUtils.toInt((String)query.get("state")) != 0){
			criterionsList.add(Restrictions.eq("state", NumberUtils.toInt((String)query.get("state"))));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("startTime"))){			
			try {
				criterionsList.add(Restrictions.ge("createTime", dateFormat.parse((String)query.get("startTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)query.get("endTime"))){
			try {
				criterionsList.add(Restrictions.le("createTime", dateFormat.parse((String)query.get("endTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)query.get("boardname"))){
			criterionsList.add(Restrictions.like("boardname", "%" + query.get("boardname") + "%"));
		}
		
		if(StringUtils.isNumeric((String)query.get("parentBoardId")) && !"0".equals(query.get("parentBoardId"))){
			criterionsList.add(Restrictions.eq("parentBoard.id", NumberUtils.toLong((String)query.get("parentBoardId"))));
		}
		
		if(StringUtils.isNumeric((String)query.get("maxboardid")) && !"0".equals(query.get("maxboardid"))){
			criterionsList.add(Restrictions.le("id", NumberUtils.toLong((String)query.get("maxboardid"))));
		}
		
		if(StringUtils.isNumeric((String)query.get("id")) && !"0".equals(query.get("id"))){
			criterionsList.add(Restrictions.eq("id", NumberUtils.toLong((String)query.get("id"))));
		}
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return (Page<Board>)boardDao.pagedQuery(Board.class, pageNo, pageSize,orderBy,isAsc ,queryString, criterions);
	}
	
	public void fakeDeleteBoards(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Board> page = boardDao.pagedQuery(Board.class, 1, ids.length,
				inCodition);

		for (Board board : page.getList()) {
			board.setState(-1);
			boardDao.saveOrUpdate(board);
		}
	}
	
	public void restore(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Board> page = boardDao.pagedQuery(Board.class, 1, ids.length,
				inCodition);

		for (Board board : page.getList()) {
			board.setState(1);
			boardDao.saveOrUpdate(board);
		}
	}	
	
	public Board getById(long boardId){
		return boardDao.get(Board.class, boardId);
	}


	public int delete(Long[] ids){
		for(Long id : ids){
			boardDao.removeById(Board.class, id);
		}		
		return 0;
	}

}
