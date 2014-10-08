package com.zigui.utils;

import com.zigui.domain.DataFunction;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.service.impl.DataFunctionServiceImpl;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

public class DataFunctionDirective implements TemplateDirectiveModel {
	
	@Autowired
	private DataFunctionServiceImpl dataFunctionService;
	
	@Autowired
	private CommonServiceImpl commonService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		Writer out = env.getOut();
		
		String functionName = MapUtils.getString(params, "function");
		
		if(functionName.equals("getRelationNews")){
			
			int pageNo = NumberUtils.toInt(MapUtils.getString(params, "pageNo", "1"));
			int pageSize = NumberUtils.toInt(MapUtils.getString(params, "pageSize", "10"));
			String keyWords = MapUtils.getString(params, "keyWords", "");
			
			String firstKey = null;
			Object result = new Object();
			if(keyWords != null && StringUtils.isNotBlank(keyWords)){
				firstKey = StringUtils.split(keyWords, " ")[0];
			}
			if(StringUtils.isNotBlank(firstKey)){
				result = commonService.getByHql("from News where keywords like '%" + firstKey + "%'", pageNo, pageSize, new Object[0]);
			}
			
			env.setVariable("functionData" , DEFAULT_WRAPPER.wrap(result));                                    
		    body.render(out);
			
		}else{
		
			DataFunction function = dataFunctionService.getByName(functionName);
			
			String parameter = function.getParameter();
			String[] parameterArray = null;
			
			if(StringUtils.isNotBlank(parameter)){
				System.out.println("parameter" + parameter);
				System.out.println("params" + params);
				parameterArray = parameter.split("&");
			}
	
			Object[] objArray = new Object[parameterArray.length];
			
			for(int i = 0; i < parameterArray.length; i ++){
				if(parameterArray[i].startsWith("*L_")){
					System.out.println("parameterArray[i].substring(3)" + parameterArray[i].substring(3));
					System.out.println("MapUtils.getString(params, parameterArray[i].substring(3))" + MapUtils.getString(params, parameterArray[i].substring(3)));
					objArray[i] = NumberUtils.toLong(MapUtils.getString(params, parameterArray[i].substring(3)).replace(",", ""));
				}else if(parameterArray[i].startsWith("*I_")){
					objArray[i] = NumberUtils.toInt(MapUtils.getString(params, parameterArray[i].substring(3)).replace(",", ""));
				}else{
					objArray[i] = MapUtils.getObject(params, parameterArray[i]);
				}
			}
			
			int pageNo = NumberUtils.toInt(MapUtils.getString(params, "pageNo", "1"));
			int pageSize = NumberUtils.toInt(MapUtils.getString(params, "pageSize", "10"));
			
			System.out.println("function.getContent()" + function.getContent());
			System.out.println("objArray" + ArrayUtils.toString(objArray));
			
			Object result = commonService.getByHql(function.getContent(), pageNo, pageSize, objArray);
		   
			env.setVariable("functionData" , DEFAULT_WRAPPER.wrap(result));                                    
		    body.render(out);
		}
	    
	}
	

}
