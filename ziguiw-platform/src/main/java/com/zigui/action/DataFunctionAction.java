package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.DataFunction;
import com.zigui.service.impl.DataFunctionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataFunctionAction extends ActionSupport {

	private static final long serialVersionUID = 3323773564662922021L;
	
	@Autowired
	private DataFunctionServiceImpl dataFunctionService;
	
	private DataFunction dataFunction;
	private List<DataFunction> dataFunctions;
	
	private long dataFunctionId;
	
	public void validateSaveOrUpdate(){
		if(dataFunction.getId() < 1L && dataFunctionService.getByName(dataFunction.getName()) != null){
			this.addFieldError("dataFunction.name", "函数名称不能重复");
			return;
		}
	}
	
	public String saveOrUpdate(){
		dataFunctionService.saveOrUpdate(dataFunction);
		
		return Action.SUCCESS;
	}
	
	public String getAll(){
		dataFunctions = dataFunctionService.getAll();
		
		return Action.SUCCESS;
	}

	public String getById(){
		dataFunction = dataFunctionService.getById(dataFunctionId);
		
		return Action.SUCCESS;
	}

	public DataFunction getDataFunction() {
		return dataFunction;
	}



	public void setDataFunction(DataFunction dataFunction) {
		this.dataFunction = dataFunction;
	}



	public List<DataFunction> getDataFunctions() {
		return dataFunctions;
	}



	public void setDataFunctions(List<DataFunction> dataFunctions) {
		this.dataFunctions = dataFunctions;
	}

	public long getDataFunctionId() {
		return dataFunctionId;
	}

	public void setDataFunctionId(long dataFunctionId) {
		this.dataFunctionId = dataFunctionId;
	}

	
}
