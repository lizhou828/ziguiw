<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String path = request.getContextPath(); 
String currentUrl = request.getRequestURL().toString();
String queryString = request.getQueryString() == null ? "1=1" : request.getQueryString();
queryString = queryString.replace("currentPage=", "");
queryString = queryString.replace("orderField=", "");
queryString = queryString.replace("orderType=", "");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
<link href="${pageContext.request.contextPath}/css/admin.css" type="text/css" rel="stylesheet" media="all"/>
<style type="text/css">
        .pop-box {   
            z-index: 9999; /*这个数值要足够大，才能够显示在最上层*/  
            margin-bottom: 3px;   
            display: none;   
            position: absolute;   
            background: #FFF;   
            border:solid 1px #6e8bde;   
        }   
          
        .pop-box h4 {   
            color: #FFF;   
            cursor:default;   
            height: 18px;   
            font-size: 14px;   
            font-weight:bold;   
            text-align: left;   
            padding-left: 8px;   
            padding-top: 4px;   
            padding-bottom: 2px;   
            background: url("../images/header_bg.gif") repeat-x 0 0;   
        }   
          
        .pop-box-body {   
            clear: both;   
            margin: 4px;   
            padding: 2px;   
        } 
        
        
        .mask {   
            color:#C7EDCC;
            background-color:#C7EDCC;
            position:absolute;
            top:0px;
            left:0px;
            filter: Alpha(Opacity=60);
        } 
    </style>

<title>子贵网CMS</title>
</head>
<body>