package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.Board;
import com.zigui.service.impl.BoardServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 论坛板块相关action
 *
 */
public class BoardAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8037119463940397102L;
	
	private Board board;
	private Page<Board> boards;
	
	private Map<String, String> query = new HashMap<String, String>();
	
	private int pageNo = 1;
	private int pageSize = 10;
	private String orderBy = "createTime";
	private boolean isAsc = false;
	private long boardId;
	private int queryType;
	private Long[] opIds;
	private String countPerPage = "10";

	private String currentPage = "1";
	
	@Autowired
	private BoardServiceImpl boardService;
	
	public void validateSaveOrUpdate(){
		if(StringUtils.isEmpty(board.boardname)){
			this.addFieldError("board.boardname", "版块名称不能为空");
			return;
		}
		
		if(board.getParentBoard()==null||board.getParentBoard().id==0){
			this.addFieldError("board.boardname", "请选择父版区");
			return;
		}

//		if(StringUtils.isEmpty(debate.getPositiveOpinion())){
//			this.addFieldError("debate.positiveOpinion", "正方观点不能为空");
//			return;
//		}
//		
//		if(StringUtils.isEmpty(debate.getNegativeOpinion())){
//			this.addFieldError("debate.negativeOpinion", "反方观点不能为空");
//			return;
//		}
	}
	
	public String saveOrUpdate(){
		boardService.saveOrUpdate(board);
		return Action.SUCCESS;
	}
	
	public String listByCondition(){		
		boards = boardService.listByCondition(query,new Integer(currentPage).intValue(), new Integer(countPerPage).intValue(), orderBy, isAsc,this.queryString);		
		return Action.SUCCESS;
	}
	
	public String deleteAjax() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		board = boardService.getById(opIds[0]);
		if(board.getMainpostnum()>0 || !(board.getParentBoard()!=null && board.getParentBoard().id>0) ){//父版区或者版区下有主贴
			print(response,"fail");
		}else{
			boardService.delete(opIds);
			print(response,"success");
		}				
		return null;
	}
	
	public String getById(){		
		board = boardService.getById(boardId);
		return Action.SUCCESS;
	}
	
	protected void print(HttpServletResponse response, String info) throws IOException {
        try {
        	  response.setHeader("Cache-Control", "no-cache");
        	  response.setContentType("text/json;charset=utf-8");
              response.getWriter().print(info);
        } catch (IOException e) {
                e.printStackTrace();
                throw e;
        }
     }
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Page<Board> getBoards() {
		return boards;
	}
	public void setBoards(Page<Board> boards) {
		this.boards = boards;
	}
	public Map<String, String> getQuery() {
		return query;
	}
	public void setQuery(Map<String, String> query) {
		this.query = query;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public boolean isAsc() {
		return isAsc;
	}
	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	public int getQueryType() {
		return queryType;
	}
	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}
	public Long[] getOpIds() {
		return opIds;
	}
	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}

	public String getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	

}
