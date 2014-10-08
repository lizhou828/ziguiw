package com.zigui.utils;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlUtils {
	
	public static String getPureText(String html){
	    StringBuffer sb = new StringBuffer();  
	    try  
	    {  
	    	Parser parser = Parser.createParser(html, "UTF-8");
	    	NodeIterator its  = parser.elements();
	         
	        while(its.hasMoreNodes())  
	        {  
	           Node node = (Node) its.nextNode();
	           sb.append(node.toPlainTextString());  
	        }  
	        return sb.toString();  
        }catch(Exception ex)  
        {  
             ex.printStackTrace();  
             return html;  
        }  
	}
	
	public static String getFirstImage(String html){
		
		String firstImage = null;
		
		Parser parser = Parser.createParser(html, "UTF-8");
		NodeFilter filter = new NodeClassFilter(ImageTag.class);

        try {
			NodeList images = parser.extractAllNodesThatMatch(filter);
			
			for (Node node : images.toNodeArray()) {
		           ImageTag imageTag = (ImageTag)node;
		           firstImage = imageTag.getImageURL();
		           
		           break;
	        }
		} catch (ParserException e) {
//			log.error(e.getMessage(), e);
		}

        return firstImage;	
	}

}
