<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/common_tag.jsp" %>
 
        <script language="JavaScript">   
        	var msg = '';
        </script>
        
   		<s:if test="hasFieldErrors()">   
		<s:iterator value="fieldErrors">   
		     <s:iterator value="value">   
		         <script language="JavaScript">   
		         	msg += "<s:property escape="false"/>";  
		         	msg += "\n";
		         </script>      
		     </s:iterator>     
		</s:iterator>   
		</s:if>  
		
         <!-- actionerror  --> 
	     <s:if test="hasActionErrors()">     
	         <s:iterator value="actionErrors">     
	                <script   language="JavaScript">     
	                 msg += "<s:property escape="false"/>";  
		         	 msg += "\n";
	                </script>     
	             </s:iterator>     
	     </s:if>   

         <!-- actionmessage  -->     
	     <s:if test="hasActionMessages()">     
	         <s:iterator value="actionMessages">     
	             <script language="JavaScript">     
	                 msg += "<s:property escape="false"/>";  
		         	 msg += "\n";   
	             </script>     
	         </s:iterator>     
	     </s:if>   
      
         <!-- 打印转换好格式的fielderror信息    -->  
        <script language="JavaScript">  
        	if(msg != ''){ 
            	alert(msg); 
            }
        </script>   
		         
		         
         <!-- fielderror  -->    
     <s:if test="hasFieldErrors()">     
         <s:iterator value="fieldErrors">     
             <s:iterator value="value" status="statu">     
               <!-- field的错误消息是List 因此还需要迭代一次   -->            
                 <s:set name="index" value="#statu.index"/>     
                 <s:set name="msg" value="#msg==null?'':#msg+     
                 value.toString()+'\\\n'"/>
                
                 <!-- //关键代码   -->     
              </s:iterator>     
         </s:iterator>     
     </s:if>     
     
